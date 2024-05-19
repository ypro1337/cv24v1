package fr.univrouen.cv24v1.model;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.*;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@NoArgsConstructor
@Table(name = "objectif")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "objectif", propOrder = {
        "value"
})
public class Objectif implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlTransient
    private Long id;

    @XmlValue
    @Column(length = 128,name = "\"value\"")
    private String value;

    @XmlAttribute(name = "statut", required = true)
    @Enumerated(EnumType.STRING)
    private Statut statut;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }
}
