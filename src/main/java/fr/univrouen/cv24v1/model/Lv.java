package fr.univrouen.cv24v1.model;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.*;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@NoArgsConstructor
@Table(name = "lv")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "lv")
public class Lv implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlTransient
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "divers_id")
    @XmlTransient
    private Divers divers;

    @XmlAttribute(name = "lang", required = true)
    @Column(nullable = false)
    private String lang;

    @XmlAttribute(name = "cert", required = true)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Cert cert;

    @XmlAttribute(name = "nivs")
    @Enumerated(EnumType.STRING)
    private Nivs nivs;

    @XmlAttribute(name = "nivi")
    private Integer nivi;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Divers getDivers() {
        return divers;
    }

    public void setDivers(Divers divers) {
        this.divers = divers;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public Cert getCert() {
        return cert;
    }

    public void setCert(Cert cert) {
        this.cert = cert;
    }

    public Nivs getNivs() {
        return nivs;
    }

    public void setNivs(Nivs nivs) {
        this.nivs = nivs;
    }

    public Integer getNivi() {
        return nivi;
    }

    public void setNivi(Integer nivi) {
        this.nivi = nivi;
    }
}
