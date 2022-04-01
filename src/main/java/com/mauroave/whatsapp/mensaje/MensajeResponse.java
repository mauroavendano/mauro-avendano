package com.mauroave.whatsapp.mensaje;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.mauroave.whatsapp.grupo.Grupo;
import com.mauroave.whatsapp.persona.Persona;

import java.util.Date;

public class MensajeResponse {
    private Long id;
    private Date fechaCreacion;
    private Long sender_id;
    private Long reciever_id;
    private Long group_reciever_id;
    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Long getSender_id() {
        return sender_id;
    }

    public void setSender_id(Long sender_id) {
        this.sender_id = sender_id;
    }

    public Long getReciever_id() {
        return reciever_id;
    }

    public void setReciever_id(Long reciever_id) {
        this.reciever_id = reciever_id;
    }

    public Long getGroup_reciever_id() {
        return group_reciever_id;
    }

    public void setGroup_reciever_id(Long group_reciever_id) {
        this.group_reciever_id = group_reciever_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @JsonSetter("sender")
    public void setSender(Persona persona) {
        if (persona != null) {
            this.setSender_id(persona.getId());
        } else {
            this.setSender_id(null);
        }
    }

    @JsonSetter("reciever")
    public void setReciever(Persona persona) {
        if (persona != null) {
            this.setReciever_id(persona.getId());
        } else {
            this.setReciever_id(null);
        }
    }

    @JsonSetter("groupReciever")
    public void setGroupReciever(Grupo grupo) {
        if (grupo != null) {
            this.setGroup_reciever_id(grupo.getId());
        } else {
            this.setGroup_reciever_id(null);
        }
    }
}
