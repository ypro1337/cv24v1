package fr.univrouen.cv24v1.repository;

import fr.univrouen.cv24v1.model.Cv;
import fr.univrouen.cv24v1.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CvRepository extends JpaRepository<Cv, Long> {

    Optional<Cv> findByIdentite_GenreAndIdentite_NomAndIdentite_PrenomAndIdentite_Tel(
            Genre genre, String nom, String prenom, String tel);

    @Query("SELECT cv FROM Cv cv WHERE " +
            "cv.identite.nom LIKE %:nom% OR " +
            "cv.identite.prenom LIKE %:prenom% OR " +
            "cv.objectif.value LIKE %:objectifValue%")
    List<Cv> findByNomOrPrenomOrObjectifValueContaining(String nom, String prenom, String objectifValue);
}

