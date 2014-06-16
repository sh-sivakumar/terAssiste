package com.example.terassiste.metier;

/**
 * Classe permettant de gerer une personne
 * @author Shinthujan, Jian, Walid, Wally, Youssef
 */
public class Personne {
	private String nom;
	private String prenom;
	
	/**
	 * Constructeur Personne
	 * @param nom
	 * @param prenom
	 */
	public Personne(String nom, String prenom) {
		this.nom = nom;
		this.prenom = prenom;
	}

	/**
	 * Retourne le nom de la personne
	 * @return nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Affecte a la variable nom le parametre nom
	 * @param nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * Retourne le prenom de la personne
	 * @return personne
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * Affecte a la variable prenom le parametre prenom
	 * @param prenom
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
}
