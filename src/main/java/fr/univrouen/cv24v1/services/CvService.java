package fr.univrouen.cv24v1.services;

import fr.univrouen.cv24v1.model.CvResume;
import fr.univrouen.cv24v1.model.Diplome;
import fr.univrouen.cv24v1.model.Identite;
import fr.univrouen.cv24v1.model.Cv;

import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CvService {

    public List<CvResume> transformCvsToResumes(List<Cv> cvs) {
        return cvs.stream().map(cv -> {
            CvResume resume = new CvResume();
            resume.setId(cv.getId());

            Identite identite = new Identite();
            identite.setGenre(cv.getIdentite().getGenre());
            identite.setNom(cv.getIdentite().getNom());
            identite.setPrenom(cv.getIdentite().getPrenom());
            resume.setIdentite(identite);

            resume.setObjectif(cv.getObjectif());

            List<Diplome> diplomes = cv.getCompetences().getDiplomes();
            Optional<Diplome> highestNiveauDiplome = diplomes.stream()
                    .max(Comparator.comparingInt(Diplome::getNiveau));
            Optional<Diplome> lastAddedDiplome = diplomes.stream()
                    .max(Comparator.comparing(Diplome::getId));

            resume.setDiplome(highestNiveauDiplome.orElse(lastAddedDiplome.orElse(null)));

            return resume;
        }).collect(Collectors.toList());
    }
}
