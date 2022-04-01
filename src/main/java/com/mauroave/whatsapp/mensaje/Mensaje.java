package com.mauroave.whatsapp.mensaje;

import com.mauroave.whatsapp.grupo.Grupo;
import com.mauroave.whatsapp.persona.Persona;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Mensaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "El sender no puede ser nulo")
    @ManyToOne
    @JoinColumn(name = "sender_id")
    private Persona sender;
    @NotNull(message = "El reciever no puede ser nulo")
    @ManyToOne
    @JoinColumn(name = "reciever_id")
    private Persona reciever;
    @ManyToOne
    @JoinColumn(name = "group_reciever_id")
    private Grupo groupReciever;
    @NotNull(message = "El contenido del mensaje no puede ser nulo")
    @NotEmpty(message = "El contenido del mensaje no puede ser vacio.")
    @Lob
    @Type(type = "org.hibernate.type.TextType")
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
