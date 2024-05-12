package fr.univrouen.cv24v1.model;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Table(name = "certif")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "certif", propOrder = {
        "datedeb",
        "datefin",
        "titre"
})
public class Certif implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlTransient
    @Column(name = "id")
    private Long id;

    @XmlElement(required = true)
    @Column(nullable = false)
    private String datedeb;

    @Column(name = "datefin")
    private String datefin;

    @XmlElement(required = true)
    @Column(nullable = false)
    private String titre;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDatedeb() {
        return datedeb;
    }

    public void setDatedeb(String datedeb) {
        this.datedeb = datedeb;
    }

    public String getDatefin() {
        return datefin;
    }

    public void setDatefin(String datefin) {
        this.datefin = datefin;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }
}
