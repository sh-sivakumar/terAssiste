package com.example.terassiste.metier;

import java.util.Date;

public class Assistance {
	private String depart;
	private String arrivee;
	private Date heureDebarquement;
	private Date heureArriveeEstime;
	
	public Assistance(String depart, String arrivee, Date heureDebarquement, Date heureArriveeEstime) {
		this.depart = depart;
		this.arrivee = arrivee;
		this.heureDebarquement = heureDebarquement;
		this.heureArriveeEstime = heureArriveeEstime;
	}
	
}
