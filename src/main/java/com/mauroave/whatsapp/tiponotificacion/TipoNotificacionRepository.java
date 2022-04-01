package com.mauroave.whatsapp.tiponotificacion;

import com.mauroave.whatsapp.notificacion.Notificacion;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TipoNotificacionRepository extends PagingAndSortingRepository<TipoNotificacion, Long> {
}
