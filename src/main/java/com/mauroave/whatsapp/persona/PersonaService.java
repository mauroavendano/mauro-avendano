package com.mauroave.whatsapp.persona;

import com.mauroave.whatsapp.utils.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonaService {
    @Autowired
    private PersonaRepository personaRepository;

    public PageResponse<List<Persona>> get(Integer pageNumber, Integer size){
        PageResponse<List<Persona>> page = new PageResponse();
        Pageable pageable = PageRequest.of(pageNumber, size);
        List<Persona> result = new ArrayList<Persona>();
        this.personaRepository.findAll(pageable).iterator().forEachRemaining(result::add);
        page.setElements(result);
        page.setLength(this.personaRepository.count());
        return page;
    }

    public Persona insert(Persona consume) {
        Persona entity = this.personaRepository.save(consume);
        return entity;
    }

}
