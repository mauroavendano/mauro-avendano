package com.mauroave.whatsapp.mensaje;

import com.mauroave.whatsapp.grupo.Grupo;
import com.mauroave.whatsapp.persona.Persona;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Mensaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Persona sender;
    private Persona reciever;
    private Grupo groupReciever;
    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Grupo getGroupReciever() {
        return groupReciever;
    }

    public void setGroupReciever(Grupo groupReciever) {
        this.groupReciever = groupReciever;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
