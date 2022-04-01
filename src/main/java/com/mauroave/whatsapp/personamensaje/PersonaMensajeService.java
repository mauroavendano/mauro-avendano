package com.mauroave.whatsapp.personamensaje;

import com.mauroave.whatsapp.notificacion.Notificacion;
import com.mauroave.whatsapp.notificacion.NotificacionRepository;
import com.mauroave.whatsapp.notificacion.NotificacionResponse;
import com.mauroave.whatsapp.notificacionpersona.NotificacionPersona;
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
public class PersonaMensajeService {
    @Autowired
    private PersonaMensajeRepository personaMensajeRepository;

    public PersonaMensaje insert(PersonaMensaje consume) {
        PersonaMensaje entitySaved = this.personaMensajeRepository.save(consume);
        return entitySaved;
    }

    public void insertAll(List<PersonaMensaje> consumes) {
        if (consumes != null) {
            Iterator var3 = consumes.iterator();

            while(var3.hasNext()) {
                PersonaMensaje cons = (PersonaMensaje) var3.next();
                this.insert(cons);
            }
        }
    }

}
