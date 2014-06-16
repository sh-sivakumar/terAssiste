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
	
	public Assistance(String depart, String arrivee, String heureDebarquement, String heureArriveeEstime) {
		this.depart = depart;
		this.arrivee = arrivee;
		this.heureDebarquement = heureDebarquement;
		this.heureArriveeEstime = heureArriveeEstime;
	}

	public String getDepart() {
		return depart;
	}

	public void setDepart(String depart) {
		this.depart = depart;
	}

	public String getArrivee() {
		return arrivee;
	}

	public void setArrivee(String arrivee) {
		this.arrivee = arrivee;
	}

	public String getHeureDebarquement() {
		return heureDebarquement;
	}

	public void setHeureDebarquement(String heureDebarquement) {
		this.heureDebarquement = heureDebarquement;
	}

	public String getHeureArriveeEstime() {
		return heureArriveeEstime;
	}

	public void setHeureArriveeEstime(String heureArriveeEstime) {
		this.heureArriveeEstime = heureArriveeEstime;
	}
	
}
