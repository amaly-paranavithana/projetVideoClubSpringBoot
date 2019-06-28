package org.formation.metier;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("bluray")
public class BluRay extends Article {
	@Column(name = "trois_d")
	private Boolean troisD;

	public BluRay() {

	}

	public Boolean getTroisD() {
		return troisD;
	}

	public void setTroisD(Boolean troisD) {
		this.troisD = troisD;
	}

}
