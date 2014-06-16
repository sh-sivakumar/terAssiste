package com.example.terassiste.metier;

/**
 * Classe permet de gerer un evenement
 * @author Shinthujan, Jian, Walid, Wally, Youssef
 */
public class Evenement {
	private Client client;
	private String numTrain;
	private String loginAgent;
	private Assistance assistance;
	
	/**
	 * Constructeur Evenement
	 * @param client
	 * @param numT
	 * @param ag
	 * @param assistance
	 */
	public Evenement(Client client, String numT, String ag, Assistance assistance){
		this.client = client;
		this.numTrain = numT;
		this.assistance = assistance;
		this.loginAgent = ag;
	}

	/**
	 * Retourne le client lie a l'evenement
	 * @return client
	 */
	public Client getClient() {
		return client;
	}

	/**
	 * Affecte a la variable client le parametre client
	 * @param client
	 */
	public void setClient(Client client) {
		this.client = client;
	}

	/**
	 * Retourne le numero du train
	 * @return train
	 */
	public String getNumTrain() {
		return numTrain;
	}

	/**
	 * Affecte a la variable numTrain le parametre numTrain
	 * @param numTrain
	 */
	public void setNumTrain(String numTrain) {
		this.numTrain = numTrain;
	}

	/**
	 * Retourne le login de l'agent
	 * @return loginAgent
	 */
	public String getAgent() {
		return loginAgent;
	}

	/**
	 * Affecte a la variable loginAgent le parametre agent
	 * @param agent
	 */
	public void setAgent(String agent) {
		this.loginAgent = agent;
	}

	/**
	 * Retourne l'assistance
	 * @return assistance
	 */
	public Assistance getAssistance() {
		return assistance;
	}

	/**
	 * Affecte a la variable assistance le parametre assistance
	 * @param assistance
	 */
	public void setAssistance(Assistance assistance) {
		this.assistance = assistance;
	}
	
}
