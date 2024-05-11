package fr.univrouen.cv24v1.model;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.*;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Table(name = "divers")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "divers", propOrder = {
        "lv",
        "autre"
})
public class Divers implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlTransient
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "divers")
    @XmlElement(required = true)
    private List<Lv> lv;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "divers")
    private List<Autre> autre;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Lv> getLv() {
        if (lv == null) {
            lv = new ArrayList<>();
        }
        return this.lv;
    }

    public void setLv(List<Lv> lv) {
        this.lv = lv;
    }

    public List<Autre> getAutre() {
        if (autre == null) {
            autre = new ArrayList<>();
        }
        return this.autre;
    }

    public void setAutre(List<Autre> autre) {
        this.autre = autre;
    }
}
