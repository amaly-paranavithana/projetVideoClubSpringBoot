package org.formation.metier;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class FilmRealisateurPK implements Serializable {
	@ManyToOne
	@JoinColumn(name = "film_id")
	private Film film;
	@ManyToOne
	@JoinColumn(name = "realisateur_id")
	private Realisateur realisateur;

	public FilmRealisateurPK() {

	}

	public FilmRealisateurPK(Film film, Realisateur realisateur) {
		this.film = film;
		this.realisateur = realisateur;
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public Realisateur getRealisateur() {
		return realisateur;
	}

	public void setRealisateur(Realisateur realisateur) {
		this.realisateur = realisateur;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((film == null) ? 0 : film.hashCode());
		result = prime * result + ((realisateur == null) ? 0 : realisateur.hashCode());
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
		FilmRealisateurPK other = (FilmRealisateurPK) obj;
		if (film == null) {
			if (other.film != null)
				return false;
		} else if (!film.equals(other.film))
			return false;
		if (realisateur == null) {
			if (other.realisateur != null)
				return false;
		} else if (!realisateur.equals(other.realisateur))
			return false;
		return true;
	}

}
