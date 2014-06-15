package com.example.terassiste.metier;

/**
 * Classe heritant de Personne, cette classe correspond a l'agent utilisant l'application.
 * @author Shinthujan, Jian, Walid, Wally, Youssef
 */
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
