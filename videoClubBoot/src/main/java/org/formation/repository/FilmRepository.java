package org.formation.repository;

import org.formation.metier.Film;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FilmRepository extends JpaRepository<Film, Integer> {

}
