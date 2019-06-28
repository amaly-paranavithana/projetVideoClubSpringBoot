package org.formation.metier;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "film_realisateur")
public class FilmRealisateur {
	@EmbeddedId
	private FilmRealisateurPK key;
	// donnees supplementaire

	public FilmRealisateur() {

	}

	public FilmRealisateur(FilmRealisateurPK key) {
		this.key = key;
	}

	public FilmRealisateurPK getKey() {
		return key;
	}

	public void setKey(FilmRealisateurPK key) {
		this.key = key;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
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
		FilmRealisateur other = (FilmRealisateur) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		return true;
	}

}
