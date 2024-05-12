package fr.univrouen.cv24v1.model;

import jakarta.xml.bind.annotation.*;
import lombok.NoArgsConstructor;

import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "cvs", propOrder = {
        "cvResumes"
})
@XmlSeeAlso({CvResume.class})
@NoArgsConstructor
@XmlRootElement(name = "cvs", namespace = "http://univrouen.fr/cv24v1/model")
public class CvResumeWrapper {
    @XmlElement(name = "cvResume", required = true)
    private List<CvResume> cvResumes;

    // Getters and Setters
    public List<CvResume> getcvResumes() {
        return cvResumes;
    }

    public void setCvResumes(List<CvResume> CvResumes) {
        this.cvResumes = CvResumes;
    }
}
