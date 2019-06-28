package org.formation.repository;

import org.formation.metier.Article;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ArticleRepository extends JpaRepository<Article, Integer> {

}
