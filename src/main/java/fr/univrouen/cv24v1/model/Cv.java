package fr.univrouen.cv24v1.model;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.*;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Entity
@NoArgsConstructor
@Table(name = "cv")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "cv", propOrder = {
        "identite",
        "objectif",
        "prof",
        "competences",
        "divers"
})

@XmlRootElement(name = "cv24", namespace = "http://univrouen.fr/cv24v1/model")
public class Cv implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlTransient
    private Long id;

    @OneToOne(cascade = CascadeType.ALL ,fetch = FetchType.EAGER)
    @JoinColumn(name = "identite_id")
    @XmlElement(required = true)
    private Identite identite;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "objectif_id")
    @XmlElement(required = true)
    private Objectif objectif;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "prof_id")
    @XmlElement
    private Prof prof;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "competences_id")
    @XmlElement(required = true)
    private Competences competences;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "divers_id")
    @XmlElement()
    private Divers divers;


    /**
     * Gets the value of the id property.
     *
     * @return the id
     *
     */
    public Long getId() {
        return id;
    }
    /**
     * Gets the value of the identite property.
     * 
     * @return
     *     possible object is
     *     {@link Identite }
     *     
     */
    public Identite getIdentite() {
        return identite;
    }

    /**
     * Sets the value of the identite property.
     * 
     * @param value
     *     allowed object is
     *     {@link Identite }
     *     
     */
    public void setIdentite(Identite value) {
        this.identite = value;
    }

    /**
     * Gets the value of the objectif property.
     * 
     * @return
     *     possible object is
     *     {@link Objectif }
     *     
     */
    public Objectif getObjectif() {
        return objectif;
    }

    /**
     * Sets the value of the objectif property.
     * 
     * @param value
     *     allowed object is
     *     {@link Objectif }
     *     
     */
    public void setObjectif(Objectif value) {
        this.objectif = value;
    }

    /**
     * Gets the value of the prof property.
     * 
     * @return
     *     possible object is
     *     {@link Prof }
     *     
     */
    public Prof getProf() {
        return prof;
    }

    /**
     * Sets the value of the prof property.
     * 
     * @param value
     *     allowed object is
     *     {@link Prof }
     *     
     */
    public void setProf(Prof value) {
        this.prof = value;
    }

    /**
     * Gets the value of the competences property.
     * 
     * @return
     *     possible object is
     *     {@link Competences }
     *     
     */
    public Competences getCompetences() {
        return competences;
    }

    /**
     * Sets the value of the competences property.
     * 
     * @param value
     *     allowed object is
     *     {@link Competences }
     *     
     */
    public void setCompetences(Competences value) {
        this.competences = value;
    }

    /**
     * Gets the value of the divers property.
     * 
     * @return
     *     possible object is
     *     {@link Divers }
     *     
     */
    public Divers getDivers() {
        return divers;
    }

    /**
     * Sets the value of the divers property.
     * 
     * @param value
     *     allowed object is
     *     {@link Divers }
     *     
     */
    public void setDivers(Divers value) {
        this.divers = value;
    }

}
