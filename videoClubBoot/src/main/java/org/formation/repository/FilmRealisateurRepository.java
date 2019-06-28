package org.formation.repository;

import org.formation.metier.FilmRealisateur;
import org.formation.metier.FilmRealisateurPK;
import org.springframework.data.jpa.repository.JpaRepository;



public interface FilmRealisateurRepository extends JpaRepository<FilmRealisateur, FilmRealisateurPK> {

}
