package com.mauroave.whatsapp.personamensaje;

import com.mauroave.whatsapp.persona.Persona;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PersonaMensajeRepository extends PagingAndSortingRepository<PersonaMensaje, Long> {
    List<PersonaMensaje> findAllByRecieverAndLeido(Persona reciever, Boolean leido);

}
