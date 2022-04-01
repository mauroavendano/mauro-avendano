package com.mauroave.whatsapp.contacto;

import com.mauroave.whatsapp.persona.Persona;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints =
    @UniqueConstraint(columnNames = {
        "me_id",
        "contact_id"
    })
)
public class Contacto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "me_id")
    private Persona me;
    @ManyToOne
    @JoinColumn(name = "contact_id")
    private Persona contact;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Persona getMe() {
        return me;
    }

    public void setMe(Persona me) {
        this.me = me;
    }

    public Persona getContact() {
        return contact;
    }

    public void setContact(Persona contact) {
        this.contact = contact;
    }
}
