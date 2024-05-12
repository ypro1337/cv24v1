package fr.univrouen.cv24v1.model;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.*;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@NoArgsConstructor
@Table(name = "competences")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "competences", propOrder = {
        "diplome",
        "certif"
})
public class Competences implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlTransient
    @Column(name = "id")
    private Long id;

    @XmlElement(required = true)
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Diplome> diplome;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Certif> certif;



    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Diplome> getDiplomes() {
        if (diplome == null) {
            diplome = new ArrayList<>();
        }
        return this.diplome;
    }

    public void setDiplomes(List<Diplome> diplome) {
        this.diplome = diplome;
    }

    public List<Certif> getCertifs() {
        if (certif == null) {
            certif = new ArrayList<>();
        }
        return this.certif;
    }

    public void setCertifs(List<Certif> certif) {
        this.certif = certif;
    }
}