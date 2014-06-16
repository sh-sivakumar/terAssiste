package com.example.terassiste.metier;

/**
 * Classe heritant de Personne, cette classe permet de gerer les clients (PMR)
 * @author Shinthujan, Jian, Walid, Wally, Youssef
 */
public class Client extends Personne {
	private String numClient;
	private String contactExterneClient;
	
	public Client(String nom, String prenom, String numClient, String contactExt) {
		super(nom, prenom);
		this.numClient = numClient;
		this.contactExterneClient = contactExt;
	}

	public String getNumClient() {
		return numClient;
	}

	public void setNumClient(String numClient) {
		this.numClient = numClient;
	}

	public String getContactExterneClient() {
		return contactExterneClient;
	}

	public void setContactExterneClient(String contactExterneClient) {
		this.contactExterneClient = contactExterneClient;
	}

	
}
