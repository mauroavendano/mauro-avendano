package com.mauroave.whatsapp.grupopersona;

import com.mauroave.whatsapp.grupo.Grupo;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface GrupoPersonaRepository extends PagingAndSortingRepository<GrupoPersona, Long> {
    List<GrupoPersona> findAllByGrupo(Grupo grupo);
}
