package com.mauroave.whatsapp.persona;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface PersonaRepository extends PagingAndSortingRepository<Persona, Long> {
    Persona findOneByUsername(String username);

}
