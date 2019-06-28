package org.formation.metier;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@Entity
@Table(name = "film")
public class Film {
	@Id
	@SequenceGenerator(name = "seqFilm", sequenceName = "seq_film", initialValue = 100, allocationSize = 1)
	@GeneratedValue(generator = "seqFilm", strategy = GenerationType.SEQUENCE)
	@Column(name = "id_film")
	private Integer id;
	@Column(name = "titre", length = 150)
	private String titre;
	@Column(name = "date_sortie")
	@Temporal(TemporalType.DATE)
	private Date dateSortie;
	@OneToMany(mappedBy = "film")
	private Set<Article> articles;
	@Version
	private int version;
	@OneToMany(mappedBy="key.film")
	private Set<FilmRealisateur> realisateurs;
	
	public Film() {

	}

	
	
	public Set<FilmRealisateur> getRealisateurs() {
		return realisateurs;
	}



	public void setRealisateurs(Set<FilmRealisateur> realisateurs) {
		this.realisateurs = realisateurs;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public Date getDateSortie() {
		return dateSortie;
	}

	public void setDateSortie(Date dateSortie) {
		this.dateSortie = dateSortie;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public Set<Article> getArticles() {
		return articles;
	}

	public void setArticles(Set<Article> articles) {
		this.articles = articles;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Film other = (Film) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
