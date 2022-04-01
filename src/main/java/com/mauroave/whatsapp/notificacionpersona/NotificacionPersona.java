package com.mauroave.whatsapp.notificacionpersona;

import com.mauroave.whatsapp.notificacion.Notificacion;
import com.mauroave.whatsapp.persona.Persona;

import javax.persistence.*;

@Entity
public class NotificacionPersona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "notificacion_id")
    private Notificacion notificacion;
    @ManyToOne
    @JoinColumn(name = "sender_id")
    private Persona sender;
    @ManyToOne
    @JoinColumn(name = "reciever_id")
    private Persona reciever;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Notificacion getNotificacion() {
        return notificacion;
    }

    public void setNotificacion(Notificacion notificacion) {
        this.notificacion = notificacion;
    }

    public Persona getSender() {
        return sender;
    }

    public void setSender(Persona sender) {
        this.sender = sender;
    }

    public Persona getReciever() {
        return reciever;
    }

    public void setReciever(Persona reciever) {
        this.reciever = reciever;
    }
}
