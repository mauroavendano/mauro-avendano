package com.mauroave.whatsapp.mensaje;

import com.mauroave.whatsapp.grupo.Grupo;
import com.mauroave.whatsapp.persona.Persona;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Mensaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    @Column(name = "fecha_creacion")
    private Date fechaCreacion;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private Persona sender;

    @ManyToOne
    @JoinColumn(name = "reciever_id")
    private Persona reciever;
    @ManyToOne
    @JoinColumn(name = "group_reciever_id")
    private Grupo groupReciever;
    @Lob
    @Type(type = "org.hibernate.type.TextType")
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
