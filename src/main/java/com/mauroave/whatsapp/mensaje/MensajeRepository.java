package com.mauroave.whatsapp.mensaje;

import com.mauroave.whatsapp.persona.Persona;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface MensajeRepository extends PagingAndSortingRepository<Mensaje, Long> {
    List<Mensaje> findAllByReciever(Persona reciever);
}
