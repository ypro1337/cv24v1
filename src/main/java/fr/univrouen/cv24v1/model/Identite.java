package fr.univrouen.cv24v1.model;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.*;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@NoArgsConstructor
@Table(name = "identite")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "identite", propOrder = {
        "genre",
        "nom",
        "prenom",
        "tel",
        "mel"
})
public class Identite implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlTransient
    private Long id;

    @XmlElement(required = true)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Genre genre;

    @XmlElement(required = true)
    @Column(nullable = false, length = 32)
    private String nom;

    @XmlElement(required = true)
    @Column(nullable = false, length = 32)
    private String prenom;

    @Column(length = 15)
    private String tel;

    @Column(length = 128)
    private String mel;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMel() {
        return mel;
    }

    public void setMel(String mel) {
        this.mel = mel;
    }
}
