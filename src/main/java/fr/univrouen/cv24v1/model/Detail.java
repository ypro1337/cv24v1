package fr.univrouen.cv24v1.model;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.*;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@NoArgsConstructor
@Table(name = "detail")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "detail", propOrder = {
        "datedeb",
        "datefin",
        "titre"
})
public class Detail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlTransient
    private Long id;


    @XmlElement(required = true)
    private String datedeb;

    private String datefin;

    @XmlElement(required = true)
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