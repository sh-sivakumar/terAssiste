package com.example.terassiste.metier;

public class Client extends Personne {
	private String numClient;
	private String typeHandicap;
	private String contactExterneClient;
	
	public Client(String nom, String prenom, String contact, String numClient, String typeHandicap, String contactExt) {
		super(nom, prenom, contact);
		this.numClient = numClient;
		this.typeHandicap = typeHandicap;
		this.contactExterneClient = contactExt;
	}

}
