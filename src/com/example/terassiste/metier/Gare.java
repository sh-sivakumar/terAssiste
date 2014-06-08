package com.example.terassiste.metier;

public class Gare {
	private String numGare;
	private String nomGare;
	private String villeGare;
	private String adresseGare;
	private boolean retournement;
	
	public Gare(String numGare, String nomGare, String villeGare, String adresseGare, boolean retournement) {
		this.numGare = numGare;
		this.nomGare = nomGare;
		this.villeGare = villeGare;
		this.adresseGare = adresseGare;
		this.retournement = retournement;
	}
}
