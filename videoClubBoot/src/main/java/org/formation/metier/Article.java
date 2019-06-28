package org.formation.metier;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "article")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING, length = 7, name = "type")
@NamedQueries({ @NamedQuery(name = "Article.findAllDvd", query = "select a from Dvd a"),
		@NamedQuery(name = "Article.findAllBluRay", query = "select b from BluRay b"),
		@NamedQuery(name = "Article.findAllWithEmprunteurAndFilmAndRealisateur", query = "select distinct a from Article as a left join fetch a.emprunteur left join fetch a.film as f left join fetch f.realisateurs as r left join fetch r.key.realisateur left join fetch r.key.film ") })
public abstract class Article {
	@Id
	@SequenceGenerator(name = "seqArticle", sequenceName = "seq_article", initialValue = 200, allocationSize = 1)
	@GeneratedValue(generator = "seqArticle", strategy = GenerationType.SEQUENCE)
	@Column(name = "numero_article")
	private Integer numeroArticle;
	@Column(name = "nb_disques")
	private Integer nbDisques;
	@ManyToOne
	@JoinColumn(name = "film_id")
	private Film film;
	@ManyToOne
	@JoinColumn(name = "adherent_id")
	private Adherent emprunteur;
	@Version
	private int version;

	public Article() {

	}

	public Integer getNumeroArticle() {
		return numeroArticle;
	}

	public void setNumeroArticle(Integer numeroArticle) {
		this.numeroArticle = numeroArticle;
	}

	public Integer getNbDisques() {
		return nbDisques;
	}

	public void setNbDisques(Integer nbDisques) {
		this.nbDisques = nbDisques;
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public Adherent getEmprunteur() {
		return emprunteur;
	}

	public void setEmprunteur(Adherent emprunteur) {
		this.emprunteur = emprunteur;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numeroArticle == null) ? 0 : numeroArticle.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Article other = (Article) obj;
		if (numeroArticle == null) {
			if (other.numeroArticle != null)
				return false;
		} else if (!numeroArticle.equals(other.numeroArticle))
			return false;
		return true;
	}

}
