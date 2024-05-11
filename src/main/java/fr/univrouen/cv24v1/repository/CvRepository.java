package fr.univrouen.cv24v1.repository;

import fr.univrouen.cv24v1.model.Cv;
import fr.univrouen.cv24v1.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CvRepository extends JpaRepository<Cv, Long> {

    Optional<Cv> findByIdentite_GenreAndIdentite_NomAndIdentite_PrenomAndIdentite_Tel(
            Genre genre, String nom, String prenom, String tel);
}

