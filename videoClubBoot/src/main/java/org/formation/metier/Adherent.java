package org.formation.metier;

import java.util.Date;
import java.util.List;

import javax.persistence.*;



@Entity
@Table(name = "adherent")
@NamedQueries({ @NamedQuery(name = "Adherent.findAll", query = "select a from Adherent a"),
		@NamedQuery(name = "Adherent.findByVille", query = "select a from Adherent a where lower(a.adresse.ville) like :ville"),
		@NamedQuery(name = "Adherent.findAllWithArticles", query = "select distinct a from Adherent a left join fetch a.articlesEmpruntes "),
		@NamedQuery(name = "Adherent.findByKeyWithArticles", query = "select distinct a from Adherent a left join fetch a.articlesEmpruntes ae left join fetch ae.film where a.numero=:numero") })
public class Adherent {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqAdherent")
	@SequenceGenerator(name = "seqAdherent", sequenceName = "seq_adherent", initialValue = 100, allocationSize = 1)
	@Column(name = "no_adherent")
	private Integer numero;
	@Column(name = "prenom_adherent", length = 150)
	private String prenom;
	@Column(name = "nom_adherent", length = 100, nullable = false)
	private String nom;
	@Enumerated(EnumType.STRING)
	@Column(name = "civilite", length = 4)
	private Titre civilite;
	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "numero", column = @Column(name = "numero_rue_adherent")),
			@AttributeOverride(name = "rue", column = @Column(name = "rue_adherent", length = 150)),
			@AttributeOverride(name = "codePostal", column = @Column(name = "code_postal_adherent", length = 5)),
			@AttributeOverride(name = "ville", column = @Column(name = "ville_adherent", length = 150)) })
	private Adresse adresse;
	@OneToMany(mappedBy = "emprunteur", fetch = FetchType.LAZY)
	private List<Article> articlesEmpruntes;
	@Version
	private int version;
	@Temporal(TemporalType.DATE)
	private Date dtNaiss;

	public Adherent() {

	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Titre getCivilite() {
		return civilite;
	}

	public void setCivilite(Titre civilite) {
		this.civilite = civilite;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public List<Article> getArticlesEmpruntes() {
		return articlesEmpruntes;
	}

	public void setArticlesEmpruntes(List<Article> articlesEmpruntes) {
		this.articlesEmpruntes = articlesEmpruntes;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public Date getDtNaiss() {
		return dtNaiss;
	}

	public void setDtNaiss(Date dtNaiss) {
		this.dtNaiss = dtNaiss;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
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
		Adherent other = (Adherent) obj;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		return true;
	}

}
