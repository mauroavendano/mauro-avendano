package com.mauroave.whatsapp.mensaje;

import com.mauroave.whatsapp.utils.ObjectUtils;
import com.mauroave.whatsapp.utils.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MensajeService {
    @Autowired
    private MensajeRepository mensajeRepository;

    public PageResponse<List<Mensaje>> get(Integer pageNumber, Integer size){
        PageResponse<List<Mensaje>> page = new PageResponse();
        Pageable pageable = PageRequest.of(pageNumber, size);
        List<Mensaje> result = new ArrayList<Mensaje>();
        this.mensajeRepository.findAll(pageable).iterator().forEachRemaining(result::add);
        page.setElements(result);
        page.setLength(this.mensajeRepository.count());
        return page;
    }

    public Mensaje insert(MensajeConsume consume) {
        Mensaje entity = this.transformEntity(consume);
        this.mensajeRepository.save(entity);
        return this.transformResponse(entity);
    }

    private Mensaje transformEntity(MensajeConsume consume) {
        return ObjectUtils.convertValue(consume, Mensaje.class);
    }

    public Mensaje transformResponse(Mensaje entity) {
        return ObjectUtils.convertValue(entity, Mensaje.class);
    }

}
