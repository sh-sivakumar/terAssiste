package com.example.terassiste.metier;

/**
 * Cette classe permet de gerer un itineraire, avec une gare de depart et une gare d'arrivee
 * @author Shinthujan, Jian, Walid, Wally, Youssef
 */
public class Assistance {
	private String depart;
	private String arrivee;
	private String heureDebarquement;
	private String heureArriveeEstime;
	
	/**
	 * Constructeur Assistance
	 * @param depart
	 * @param arrivee
	 * @param heureDebarquement
	 * @param heureArriveeEstime
	 */
	public Assistance(String depart, String arrivee, String heureDebarquement, String heureArriveeEstime) {
		this.depart = depart;
		this.arrivee = arrivee;
		this.heureDebarquement = heureDebarquement;
		this.heureArriveeEstime = heureArriveeEstime;
	}

	/**
	 * Retourne la gare de depart
	 * @return depart
	 */
	public String getDepart() {
		return depart;
	}
	
	/**
	 * Affecte a la variable depart le parametre depart
	 * @param depart
	 */
	public void setDepart(String depart) {
		this.depart = depart;
	}

	/**
	 * Retourne la gare d'arrivee
	 * @return arrivee
	 */
	public String getArrivee() {
		return arrivee;
	}

	/**
	 * Affecte a la variable arrivee le parametre arrivee
	 * @param arrivee
	 */
	public void setArrivee(String arrivee) {
		this.arrivee = arrivee;
	}

	/**
	 * Retourne l'heure de depart
	 * @return heureDebarquement
	 */
	public String getHeureDebarquement() {
		return heureDebarquement;
	}

	/**
	 * Affecte a la variable heureDebarquement le parametre heureDebarquement
	 * @param heureDebarquement
	 */
	public void setHeureDebarquement(String heureDebarquement) {
		this.heureDebarquement = heureDebarquement;
	}

	/**
	 * Retourne l'heure d'arrivee
	 * @return heureArriveeEstime
	 */
	public String getHeureArriveeEstime() {
		return heureArriveeEstime;
	}

	/**
	 * Affecte a la variable heureArriveeEstime le parametre heureArriveeEstime
	 * @param heureArriveeEstime
	 */
	public void setHeureArriveeEstime(String heureArriveeEstime) {
		this.heureArriveeEstime = heureArriveeEstime;
	}
	
}
