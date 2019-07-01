package org.formation.metier;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.formation.metier.view.JsonViews;

import com.fasterxml.jackson.annotation.JsonView;

@Embeddable
public class Adresse {
	@Column(name = "numero")
	@JsonView(JsonViews.Common.class)
	private Integer numero;
	@Column(name = "rue", length = 200)
	@JsonView(JsonViews.Common.class)
	private String rue;
	@Column(name = "code_postal", length = 5)
	@JsonView(JsonViews.Common.class)
	private String codePostal;
	@Column(name = "ville", length = 150)
	@JsonView(JsonViews.Common.class)
	private String ville;

	public Adresse() {

	}

	public Adresse(Integer numero, String rue, String codePostal, String ville) {
		this.numero = numero;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

}
