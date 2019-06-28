package org.formation.metier;

public enum Titre {
	M("Monsieur"), MME("Madame"), MLLE("Mademoiselle");
	private String titre;

	private Titre(String titre) {
		this.titre = titre;
	}

	public String getTitre() {
		return titre;
	}
}
