package com.example.terassiste.metier;

public class Agent extends Personne {
	private String numAgent;
	private String login;
	
	public Agent(String nom, String prenom, String contact, String numAgent, String login) {
		super(nom, prenom, contact);
		this.numAgent = numAgent;
		this.login = login;
	}

}
