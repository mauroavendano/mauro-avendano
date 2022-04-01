package com.mauroave.whatsapp.persona;

import com.mauroave.whatsapp.utils.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class PersonaService {
    @Autowired
    private PersonaRepository personaRepository;

    /*@PostConstruct
    public void initialize(){
        if(personaRepository.findOneByUsername("mauroave") == null){
            Persona persona = new Persona();
            persona.setNombre("Mauro");
            persona.setN
            save(new Persona("mauroave", "mauroave", "ADMIN"));
        }
        if(personaRepository.findOneByUsername("user") == null){
            save(new Persona("user", "user", "USER"));
        }
    }*/

    public PageResponse<List<Persona>> get(Integer pageNumber, Integer size){
        PageResponse<List<Persona>> page = new PageResponse();
        Pageable pageable = PageRequest.of(pageNumber, size);
        List<Persona> result = new ArrayList<Persona>();
        this.personaRepository.findAll(pageable).iterator().forEachRemaining(result::add);
        page.setElements(result);
        page.setLength(this.personaRepository.count());
        return page;
    }

}
