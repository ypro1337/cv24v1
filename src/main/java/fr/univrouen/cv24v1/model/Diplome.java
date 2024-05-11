package fr.univrouen.cv24v1.model;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.*;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@NoArgsConstructor
@Table(name = "diplome")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "diplome", propOrder = {
        "date",
        "institut",
        "titre"
})
public class Diplome implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlTransient
    private Long id;

    @XmlElement(required = true)
    private String date;

    @XmlElement
    private String institut;

    @XmlElement(required = true)
    @ElementCollection(fetch = FetchType.LAZY) // Ensure collection fetch type is lazy
    @CollectionTable(name = "diplometitre", joinColumns = @JoinColumn(name = "diplome_id"))
    @Column(name = "titre")
    private List<String> titre;

    @XmlAttribute(name = "niveau", required = true)
    private int niveau;
    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getInstitut() {
        return institut;
    }

    public void setInstitut(String institut) {
        this.institut = institut;
    }

    public List<String> getTitre() {
        if (titre == null) {
            titre = new ArrayList<>();
        }
        return this.titre;
    }

    public void setTitre(List<String> titre) {
        this.titre = titre;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }
}
