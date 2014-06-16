package com.example.terassiste.metier;

/**
 * Classe heritant de Personne, cette classe permet de gerer les clients (PMR)
 * @author Shinthujan, Jian, Walid, Wally, Youssef
 */
public class Client extends Personne {
	private String numClient;
	private String contactExterneClient;
	
	/**
	 * Constructeur Client
	 * @param nom
	 * @param prenom
	 * @param numClient
	 * @param contactExt
	 */
	public Client(String nom, String prenom, String numClient, String contactExt) {
		super(nom, prenom);
		this.numClient = numClient;
		this.contactExterneClient = contactExt;
	}

	/**
	 * Retourne le numero du client (contact)
	 * @return numClient
	 */
	public String getNumClient() {
		return numClient;
	}

	/**
	 * Affecte a la variable numClient le parametre numClient
	 * @param numClient
	 */
	public void setNumClient(String numClient) {
		this.numClient = numClient;
	}

	/**
	 * Retourne le contact externe du client
	 * @return contactExterneClient
	 */
	public String getContactExterneClient() {
		return contactExterneClient;
	}

	/**
	 * Affecte a la variable contactExterneClient le parametre contactExterneClient
	 * @param contactExterneClient
	 */
	public void setContactExterneClient(String contactExterneClient) {
		this.contactExterneClient = contactExterneClient;
	}

	
}
