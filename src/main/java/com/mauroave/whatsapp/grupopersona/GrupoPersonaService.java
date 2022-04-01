package com.mauroave.whatsapp.grupopersona;

import com.mauroave.whatsapp.utils.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GrupoPersonaService {
    @Autowired
    private GrupoPersonaRepository grupoPersonaRepository;

    public PageResponse<List<GrupoPersona>> get(Integer pageNumber, Integer size){
        PageResponse<List<GrupoPersona>> page = new PageResponse();
        Pageable pageable = PageRequest.of(pageNumber, size);
        List<GrupoPersona> result = new ArrayList<GrupoPersona>();
        this.grupoPersonaRepository.findAll(pageable).iterator().forEachRemaining(result::add);
        page.setElements(result);
        page.setLength(this.grupoPersonaRepository.count());
        return page;
    }

}
