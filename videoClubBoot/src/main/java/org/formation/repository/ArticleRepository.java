package org.formation.repository;

import java.util.Optional;

import org.formation.metier.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ArticleRepository extends JpaRepository<Article, Integer> {

	@Query("select a from Dvd a")
	Optional<Article> findAllDvd(@Param("film")String film);
	
	@Query("select b from BluRay b")
	Optional<Article> findAllBluRay(@Param("film")String film);
	
	@Query("select distinct a from Article as a left join fetch a.emprunteur left join fetch a.film as f left join fetch f.realisateurs as r left join fetch r.key.realisateur left join fetch r.key.film")
	Optional<Article> findAllWithEmprunteurAndFilmAndRealisateur(@Param("film")String film);
}
