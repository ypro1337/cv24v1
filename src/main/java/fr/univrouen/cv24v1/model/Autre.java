package fr.univrouen.cv24v1.model;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.*;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@NoArgsConstructor
@Table(name = "autre")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "autre")
public class Autre implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlTransient
    private Long id;


    @XmlAttribute(name = "titre", required = true)
    @Column(length = 32, nullable = false)
    private String titre;

    @XmlAttribute(name = "comment")
    @Column(length = 128)
    private String comment;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
