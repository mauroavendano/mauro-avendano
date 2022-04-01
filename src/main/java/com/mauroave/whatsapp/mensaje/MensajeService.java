package com.mauroave.whatsapp.mensaje;

import com.mauroave.whatsapp.grupo.GrupoRepository;
import com.mauroave.whatsapp.grupopersona.GrupoPersona;
import com.mauroave.whatsapp.grupopersona.GrupoPersonaRepository;
import com.mauroave.whatsapp.notificacion.Notificacion;
import com.mauroave.whatsapp.notificacion.NotificacionRepository;
import com.mauroave.whatsapp.notificacion.NotificacionResponse;
import com.mauroave.whatsapp.notificacion.NotificacionService;
import com.mauroave.whatsapp.notificacionpersona.NotificacionPersona;
import com.mauroave.whatsapp.notificacionpersona.NotificacionPersonaService;
import com.mauroave.whatsapp.persona.PersonaRepository;
import com.mauroave.whatsapp.personamensaje.PersonaMensaje;
import com.mauroave.whatsapp.personamensaje.PersonaMensajeRepository;
import com.mauroave.whatsapp.personamensaje.PersonaMensajeService;
import com.mauroave.whatsapp.tiponotificacion.TipoNotificacionRepository;
import com.mauroave.whatsapp.utils.ObjectUtils;
import com.mauroave.whatsapp.utils.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class MensajeService {
    @Autowired
    private MensajeRepository mensajeRepository;
    @Autowired
    private TipoNotificacionRepository tipoNotificacionRepository;
    @Autowired
    private NotificacionService notificacionService;
    @Autowired
    private NotificacionPersonaService notificacionPersonaService;
    @Autowired
    private GrupoPersonaRepository grupoPersonaRepository;
    @Autowired
    private GrupoRepository grupoRepository;
    @Autowired
    private NotificacionRepository notificacionRepository;
    @Autowired
    private PersonaRepository personaRepository;
    @Autowired
    private PersonaMensajeService personaMensajeService;
    @Autowired
    private PersonaMensajeRepository personaMensajeRepository;

    /**
     * Obtengo todos los mensajes paginados
     * @param pageNumber
     * @param size
     * @return
     */
    public PageResponse<List<Mensaje>> get(Integer pageNumber, Integer size){
        PageResponse<List<Mensaje>> page = new PageResponse();
        Pageable pageable = PageRequest.of(pageNumber, size);
        List<Mensaje> result = new ArrayList<Mensaje>();
        this.mensajeRepository.findAll(pageable).iterator().forEachRemaining(result::add);
        page.setElements(result);
        page.setLength(this.mensajeRepository.count());
        return page;
    }

    /**
     * Obtengo los mensajes no leidos de una persona y los marco leidos
     * @param persona_id
     * @return
     */
    public List<Mensaje> verNoLeidos(Long persona_id){
        PageResponse<List<Mensaje>> page = new PageResponse();
        List<Mensaje> result = new ArrayList<Mensaje>();
        List<PersonaMensaje> noLeidos = new ArrayList<PersonaMensaje>();
        noLeidos = this.personaMensajeRepository.findAllByRecieverAndLeido(this.personaRepository.findById(persona_id).orElse(null), false);
        if(noLeidos!=null && noLeidos.size()>0){
            Iterator var1 = noLeidos.iterator();

            while(var1.hasNext()) {
                PersonaMensaje noLeido = (PersonaMensaje) var1.next();
                result.add(noLeido.getMensaje());
                noLeido.setLeido(true);
                this.personaMensajeRepository.save(noLeido);
            }
        }
        return result;
    }

    public MensajeResponse insert(MensajeConsume consume) {
        Mensaje entity = this.transformEntity(consume);
        Mensaje entitySaved = this.mensajeRepository.save(entity);
        //Una vez guardado el mensaje, debo enviar la notificacion a quien corresponda
        Notificacion notificacion = new Notificacion();
        //Si el id de grupo viene, es un tipo de notificacion de grupo. Caso contrario es individual
        notificacion.setTipoNotificacion(consume.getGroup_reciever_id()!=null?this.tipoNotificacionRepository.findById(1L).orElse(null):this.tipoNotificacionRepository.findById(2L).orElse(null));
        Notificacion notificacionSaved = this.notificacionRepository.save(notificacion);
        //Si es grupal, debo enviar una notificacion masiva a cada miembro del grupo
        if(consume.getGroup_reciever_id()!=null){
            List<GrupoPersona> grupoPersonas = this.grupoPersonaRepository.findAllByGrupo(this.grupoRepository.findById(consume.getGroup_reciever_id()).orElse(null));
            if(grupoPersonas != null && grupoPersonas.size()>0){
                Iterator var1 = grupoPersonas.iterator();
                List<NotificacionPersona> lista = new ArrayList<>();
                List<PersonaMensaje> listaPersonaMensaje = new ArrayList<>();
                while(var1.hasNext()) {
                    GrupoPersona v = (GrupoPersona) var1.next();

                    if(!v.getPersona().getId().equals(consume.getSender_id())){
                        NotificacionPersona notificacionPersona = new NotificacionPersona();
                        notificacionPersona.setNotificacion(notificacionSaved);
                        notificacionPersona.setReciever(this.personaRepository.findById(v.getPersona().getId()).orElse(null));
                        notificacionPersona.setSender(this.personaRepository.findById(consume.getSender_id()).orElse(null));
                        lista.add(notificacionPersona);

                        //Guardo ademas el mensaje como no leido para cada receptor
                        PersonaMensaje personaMensaje = new PersonaMensaje();
                        personaMensaje.setMensaje(entitySaved);
                        personaMensaje.setLeido(false);
                        personaMensaje.setReciever(this.personaRepository.findById(v.getPersona().getId()).orElse(null));
                        listaPersonaMensaje.add(personaMensaje);
                    }
                }
                this.notificacionPersonaService.insertAll(lista);
                this.personaMensajeService.insertAll(listaPersonaMensaje);
            }
        }else{
            NotificacionPersona notificacionPersona2 = new NotificacionPersona();
            notificacionPersona2.setNotificacion(notificacionSaved);
            notificacionPersona2.setReciever(this.personaRepository.findById(consume.getReciever_id()).orElse(null));
            notificacionPersona2.setSender(this.personaRepository.findById(consume.getSender_id()).orElse(null));
            this.notificacionPersonaService.insert(notificacionPersona2);

            PersonaMensaje personaMensaje2 = new PersonaMensaje();
            personaMensaje2.setMensaje(entitySaved);
            personaMensaje2.setLeido(false);
            personaMensaje2.setReciever(this.personaRepository.findById(consume.getReciever_id()).orElse(null));
            this.personaMensajeRepository.save(personaMensaje2);
        }
        return this.transformResponse(entitySaved);
    }

    private Mensaje transformEntity(MensajeConsume consume) {
        return ObjectUtils.convertValue(consume, Mensaje.class);
    }

    public MensajeResponse transformResponse(Mensaje entity) {
        return ObjectUtils.convertValue(entity, MensajeResponse.class);
    }

}
