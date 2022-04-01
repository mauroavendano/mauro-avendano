package com.mauroave.whatsapp.personamensaje;

import com.mauroave.whatsapp.mensaje.Mensaje;
import com.mauroave.whatsapp.notificacion.Notificacion;
import com.mauroave.whatsapp.persona.Persona;

import javax.persistence.*;

@Entity
public class PersonaMensaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "mensaje_id")
    private Mensaje mensaje;
    @ManyToOne
    @JoinColumn(name = "reciever_id")
    private Persona reciever;
    private Boolean leido;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Mensaje getMensaje() {
        return mensaje;
    }

    public void setMensaje(Mensaje mensaje) {
        this.mensaje = mensaje;
    }

    public Persona getReciever() {
        return reciever;
    }

    public void setReciever(Persona reciever) {
        this.reciever = reciever;
    }

    public Boolean getLeido() {
        return leido;
    }

    public void setLeido(Boolean leido) {
        this.leido = leido;
    }
}
