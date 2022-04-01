package com.mauroave.whatsapp.persona;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.Base64;

@Service
public class PersonaService {
    @Autowired
    private PersonaRepository personaRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostConstruct
    public void initialize(){
        if(personaRepository.findOneByUsername("mauroave") == null){
            save(new Persona("mauroave", "mauroave", "ADMIN"));
        }
        if(personaRepository.findOneByUsername("user") == null){
            save(new Persona("user", "user", "USER"));
        }
    }

    @Transactional
    private Persona save(Persona persona) {
        persona.setPassword(Base64.getEncoder().encodeToString(persona.getPassword().getBytes()));
        return personaRepository.save(persona);
    }

    public Boolean logout(HttpServletRequest request, Authentication authentication) {
        return true;
    }

    public Persona signup(Persona user, HttpServletRequest request, Authentication authentication) {
        if(personaRepository.findOneByUsername(user.getUsername()) == null){
            return save(new Persona(user.getUsername(), user.getPassword(), "USER"));
        }else{
            throw new RuntimeException("El nombre de usuario ya existe");
        }
    }
}
