package org.formation.metier;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("dvd")
public class Dvd extends Article {
	private Boolean bonus;

	public Dvd() {

	}

	public Boolean getBonus() {
		return bonus;
	}

	public void setBonus(Boolean bonus) {
		this.bonus = bonus;
	}

}
