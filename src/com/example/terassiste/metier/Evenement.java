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
	
	public Evenement(Client client, String numT, String ag, Assistance assistance){
		this.client = client;
		this.numTrain = numT;
		this.assistance = assistance;
		this.loginAgent = ag;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public String getNumTrain() {
		return numTrain;
	}

	public void setNumTrain(String numTrain) {
		this.numTrain = numTrain;
	}

	public String getAgent() {
		return loginAgent;
	}

	public void setAgent(String agent) {
		this.loginAgent = agent;
	}

	public Assistance getAssistance() {
		return assistance;
	}

	public void setAssistance(Assistance assistance) {
		this.assistance = assistance;
	}
	
}
