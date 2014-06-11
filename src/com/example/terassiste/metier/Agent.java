package com.example.terassiste.metier;

public class Agent extends Personne {
	private String login;
	
	public Agent(String nom, String prenom, String login) {
		super(nom, prenom);
		this.login = login;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
}
