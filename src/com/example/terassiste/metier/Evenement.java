package com.example.terassiste.metier;

import java.util.Date;

import android.graphics.Point;

public class Evenement {
	public String nomPmr;
	public String prenomPmr;
	public String numTrain;
	public String depart;
	public String arrivee;
	public String agent;
	public Date heureDepart;
	public Date heureArrivee;
	public Point pmrPosition;
	
	public Evenement(String pmrN, String pmrP, String numT, String dep, String arr, String ag, Date heureD, Date heureA, Point pmrPos){
		this.nomPmr = pmrN;
		this.prenomPmr = pmrP;
		this.numTrain = numT;
		this.depart = dep;
		this.arrivee = arr;
		this.heureDepart = heureD;
		this.heureArrivee = heureA;
		this.pmrPosition = pmrPos;
		this.agent = ag;
	}
}
