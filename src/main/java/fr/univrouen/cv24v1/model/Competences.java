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
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "competences_id")
    @XmlElement(required = true)
    private List<Diplome> diplome;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "competences")
    private List<Certif> certif;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Diplome> getDiplome() {
        if (diplome == null) {
            diplome = new ArrayList<>();
        }
        return this.diplome;
    }

    public void setDiplome(List<Diplome> diplome) {
        this.diplome = diplome;
    }

    public List<Certif> getCertif() {
        if (certif == null) {
            certif = new ArrayList<>();
        }
        return this.certif;
    }

    public void setCertif(List<Certif> certif) {
        this.certif = certif;
    }
}
