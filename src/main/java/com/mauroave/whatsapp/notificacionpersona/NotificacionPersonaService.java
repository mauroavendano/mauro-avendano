package com.mauroave.whatsapp.notificacionpersona;

import com.mauroave.whatsapp.notificacion.Notificacion;
import com.mauroave.whatsapp.notificacion.NotificacionResponse;
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
public class NotificacionPersonaService {
    @Autowired
    private NotificacionPersonaRepository notificacionPersonaRepository;

    public PageResponse<List<NotificacionPersona>> get(Integer pageNumber, Integer size){
        PageResponse<List<NotificacionPersona>> page = new PageResponse();
        Pageable pageable = PageRequest.of(pageNumber, size);
        List<NotificacionPersona> result = new ArrayList<NotificacionPersona>();
        this.notificacionPersonaRepository.findAll(pageable).iterator().forEachRemaining(result::add);
        page.setElements(result);
        page.setLength(this.notificacionPersonaRepository.count());
        return page;
    }

    public NotificacionPersona insert(NotificacionPersona consume) {
        NotificacionPersona entitySaved = this.notificacionPersonaRepository.save(consume);
        return entitySaved;
    }

    public void insertAll(List<NotificacionPersona> consumes) {
        if (consumes != null) {
            Iterator var3 = consumes.iterator();

            while(var3.hasNext()) {
                NotificacionPersona cons = (NotificacionPersona) var3.next();
                this.insert(cons);
            }
        }
    }



    public NotificacionResponse transformResponse(Notificacion entity) {
        return ObjectUtils.convertValue(entity, NotificacionResponse.class);
    }

}
