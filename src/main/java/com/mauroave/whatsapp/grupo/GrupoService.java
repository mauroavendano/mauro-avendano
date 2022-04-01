package com.mauroave.whatsapp.grupo;

import com.mauroave.whatsapp.utils.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GrupoService {
    @Autowired
    private GrupoRepository grupoRepository;

    public PageResponse<List<Grupo>> get(Integer pageNumber, Integer size){
        PageResponse<List<Grupo>> page = new PageResponse();
        Pageable pageable = PageRequest.of(pageNumber, size);
        List<Grupo> result = new ArrayList<Grupo>();
        this.grupoRepository.findAll(pageable).iterator().forEachRemaining(result::add);
        page.setElements(result);
        page.setLength(this.grupoRepository.count());
        return page;
    }

}
