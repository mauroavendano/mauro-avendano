package com.mauroave.whatsapp.notificacion;

import com.mauroave.whatsapp.mensaje.Mensaje;
import com.mauroave.whatsapp.mensaje.MensajeConsume;
import com.mauroave.whatsapp.mensaje.MensajeResponse;
import com.mauroave.whatsapp.utils.ObjectUtils;
import com.mauroave.whatsapp.utils.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificacionService {
    @Autowired
    private NotificacionRepository notificacionRepository;

    public PageResponse<List<Notificacion>> get(Integer pageNumber, Integer size){
        PageResponse<List<Notificacion>> page = new PageResponse();
        Pageable pageable = PageRequest.of(pageNumber, size);
        List<Notificacion> result = new ArrayList<Notificacion>();
        this.notificacionRepository.findAll(pageable).iterator().forEachRemaining(result::add);
        page.setElements(result);
        page.setLength(this.notificacionRepository.count());
        return page;
    }

    public NotificacionResponse insert(Notificacion consume) {
        Notificacion entitySaved = this.notificacionRepository.save(consume);
        return this.transformResponse(entitySaved);
    }

    public NotificacionResponse transformResponse(Notificacion entity) {
        return ObjectUtils.convertValue(entity, NotificacionResponse.class);
    }

}
