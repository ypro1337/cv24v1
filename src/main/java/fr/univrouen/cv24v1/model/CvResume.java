package fr.univrouen.cv24v1.model;

import jakarta.xml.bind.annotation.*;
import lombok.NoArgsConstructor;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "cvResume", propOrder = {
        "id",
        "identite",
        "objectif",
        "diplome"
})
@XmlSeeAlso({Identite.class, Objectif.class, Diplome.class})
@NoArgsConstructor
public class CvResume {
    @XmlElement(required = true)
    private Long id;

    @XmlElement(required = true)
    private Identite identite;

    @XmlElement(required = true)
    private Objectif objectif;

    @XmlElement(required = true)
    private Diplome diplome;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Identite getIdentite() {
        return identite;
    }

    public void setIdentite(Identite identite) {
        this.identite = identite;
    }

    public Objectif getObjectif() {
        return objectif;
    }

    public void setObjectif(Objectif objectif) {
        this.objectif = objectif;
    }

    public Diplome getDiplome() {
        return diplome;
    }

    public void setDiplome(Diplome diplome) {
        this.diplome = diplome;
    }
}
