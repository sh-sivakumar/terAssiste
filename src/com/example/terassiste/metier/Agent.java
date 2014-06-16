package com.example.terassiste.metier;

/**
 * Classe heritant de Personne, cette classe correspond a l'agent utilisant l'application.
 * @author Shinthujan, Jian, Walid, Wally, Youssef
 */
public class Agent extends Personne {
	private String login;
	
	/**
	 * Constructeur Agent
	 * @param nom
	 * @param prenom
	 * @param login
	 */
	public Agent(String nom, String prenom, String login) {
		super(nom, prenom);
		this.login = login;
	}

	/**
	 * Recupere le login
	 * @return
	 */
	public String getLogin() {
		return this.login;
	}

	/**
	 * Affecte le parametre login a la variable login
	 * @param login
	 */
	public void setLogin(String login) {
		this.login = login;
	}
}
