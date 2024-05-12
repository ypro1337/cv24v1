package fr.univrouen.cv24v1.model;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.*;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Table(name = "prof")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "prof", propOrder = {
        "detail"
})
public class Prof implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlTransient
    private Long id;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @XmlElement(required = true)
    private List<Detail> detail;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Detail> getDetails() {
        if (detail == null) {
            detail = new ArrayList<>();
        }
        return this.detail;
    }

    public void setDetails(List<Detail> detail) {
        this.detail = detail;
    }
}