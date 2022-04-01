package com.mauroave.whatsapp.mensaje;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.mauroave.whatsapp.persona.Persona;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class MensajeConsume {
    @NotNull(message = "El sender no puede ser nulo")
    private Long sender_id;
    @NotNull(message = "El reciever no puede ser nulo")
    private Long reciever_id;
    private Long group_reciever_id;
    @NotNull(message = "El contenido del mensaje no puede ser nulo")
    @NotEmpty(message = "El contenido del mensaje no puede ser vacio.")
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private String content;

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

    @JsonGetter("sender")
    public Persona getSender() {
        Persona sender = null;
        if(getSender_id() != null){
            sender = new Persona();
            sender.setId(getSender_id());
        }
        return sender;
    }

    @JsonGetter("reciever")
    public Persona getReciever() {
        Persona reciever = null;
        if(getSender_id() != null){
            reciever = new Persona();
            reciever.setId(getReciever_id());
        }
        return reciever;
    }
}
