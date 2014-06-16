package com.example.terassiste.fragments;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.terassiste.R;
import com.example.terassiste.MainActivity;
import com.example.terassiste.PlaceSelect.OnPositionSelectOneShotListener;
import com.example.terassiste.http.AsynJsonHttp;
import com.example.terassiste.metier.Assistance;
import com.example.terassiste.metier.Client;
import com.example.terassiste.metier.Evenement;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Classe qui herite de FragmentCreateEvt et qui permet de gerer le systeme de modification
 * d'un evenement (fragment)
 * @author Shinthujan, Jian, Walid, Wally, Youssef
 */
public class FragmentModifEvt extends FragmentCreateEvt {
	private int idEvenement;
	private Evenement oldEvt;
	private Evenement newEvt;
	
	private static final String TAG = "FragmentDetailEvt";
	private static final String URL = "http://terassistee.netai.net/detailevent.php";
	private static final String URL_update = "http://terassistee.netai.net/updateevent.php";

	protected AutoCompleteTextView gareDep;
	protected AutoCompleteTextView gareArr;
	protected AutoCompleteTextView train;
	
	public FragmentModifEvt(int id) {
		this.idEvenement = id;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		this._parentActivity.actionBar.setDisplayHomeAsUpEnabled(true);
		this._parentActivity.menuOk = true;
		
		/* ----- Debut : Test JSON ----- */
		/*
		JSONObject jsonReturn = new JSONObject();
		try {
			jsonReturn.put("nom", "Xavier");
			jsonReturn.put("prenom", "XXX");
			jsonReturn.put("agent", "A.Agent");
			jsonReturn.put("train", "X2T56");
			jsonReturn.put("gareDep", "Paris");
			jsonReturn.put("heureDep", "15:55");
			jsonReturn.put("gareArr", "Saint Julien");
			jsonReturn.put("heureArr", "17:05");
			this._oldPosition = new Point(50, 350);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		*/
		
		/* ----- Fin : Test JSON ----- */
		
		recupEvt(inflater, container);
		return this._view;
	}
	
	/**
	 * Methode qui recupere les informations sur l'evenement a modifier
	 * @param inflater
	 * @param container
	 */
	public void recupEvt(LayoutInflater inflater, ViewGroup container) {
		JSONObject jsonObject= new JSONObject();
		try {
			jsonObject.put("idEvent", this.idEvenement);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		AsynJsonHttp thread = new AsynJsonHttp(URL, this._parentActivity);
		thread.execute(jsonObject);
		JSONObject jsonReturn = null;
		try {
			jsonReturn = thread.get();
			Log.i(TAG, "test: "+jsonReturn.toString());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		try {
			if(jsonReturn.getBoolean("result")) {	
				this._view = inflater.inflate(R.layout.fragment_create_evt, container, false);
				this._view.findViewById(R.id.suivant).setOnClickListener(this);
				this._view.findViewById(R.id.enregistrer).setOnClickListener(this);
				this._view.findViewById(R.id.enregistrer).setVisibility(View.VISIBLE);
				
				String[] listeGares = getResources().getStringArray(R.array.liste_gares);
				gareDep = (AutoCompleteTextView) this._view.findViewById(R.id.gareDep);
				gareArr = (AutoCompleteTextView) this._view.findViewById(R.id.gareArr);
				ArrayAdapter adapter = new ArrayAdapter(this._parentActivity, android.R.layout.simple_list_item_1, listeGares);
				gareDep.setAdapter(adapter);
				gareArr.setAdapter(adapter);

				this.defaultTextViewBackground = gareDep.getBackground();
				
				train = (AutoCompleteTextView) this._view.findViewById(R.id.textNumTrain);
				List<String> listeNumTrain = this._parentActivity.getListNumTrain();
				ArrayAdapter numTrainAdapter = new ArrayAdapter(this._parentActivity, android.R.layout.simple_list_item_1, listeNumTrain);
				train.setAdapter(numTrainAdapter);
				
				fillForm(jsonReturn);
			} else {
				this._view = inflater.inflate(R.layout.empty_fragment, container, false);
				LinearLayout layout = (LinearLayout) _view.findViewById(R.id.layout_background_empty);
				layout.setBackground(getResources().getDrawable(R.drawable.evt_existe_pas));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Methode qui remplit le formulaire avec un JSON recu en parametre
	 * @param jsonReturn
	 */
	public void fillForm(JSONObject jsonReturn) {
		try {
			
			EditText nom = (EditText) this._view.findViewById(R.id.textNom);
			EditText prenom = (EditText) this._view.findViewById(R.id.textPrenom);
			AutoCompleteTextView train = (AutoCompleteTextView) this._view.findViewById(R.id.textNumTrain);			
			AutoCompleteTextView gareDep = (AutoCompleteTextView) this._view.findViewById(R.id.gareDep);
			EditText heureDep = (EditText) this._view.findViewById(R.id.heureDep);
			AutoCompleteTextView gareArr = (AutoCompleteTextView) this._view.findViewById(R.id.gareArr);
			EditText heureArr = (EditText) this._view.findViewById(R.id.heureArr);
			EditText contact = (EditText) this._view.findViewById(R.id.contact);
			EditText contactExterne = (EditText) this._view.findViewById(R.id.contactExt);
			
			Client client = new Client(jsonReturn.getString("nom"), jsonReturn.getString("prenom"), 
					jsonReturn.getString("contactPMR"), jsonReturn.getString("contactPMRExterne"));
			
			Assistance assistance = new Assistance(jsonReturn.getString("gareDep"), jsonReturn.getString("gareArr"), 
					jsonReturn.getString("heureDep"), jsonReturn.getString("heureArr"));
			
			this.oldEvt = new Evenement(client, jsonReturn.getString("train"), jsonReturn.getString("agent"), assistance);
			
			nom.setText(this.oldEvt.getClient().getNom());
			prenom.setText(this.oldEvt.getClient().getPrenom());
			train.setText(this.oldEvt.getNumTrain());
			gareDep.setText(this.oldEvt.getAssistance().getDepart());
			heureDep.setText(this.oldEvt.getAssistance().getHeureDebarquement());
			gareArr.setText(this.oldEvt.getAssistance().getArrivee());
			heureArr.setText(this.oldEvt.getAssistance().getHeureArriveeEstime());
			contact.setText(this.oldEvt.getClient().getNumClient());
			contactExterne.setText(this.oldEvt.getClient().getContactExterneClient());
			
			int x = jsonReturn.getInt("x");
			int y = jsonReturn.getInt("y");
			
			this._oldPosition = new Point(x, y);

		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public Evenement genereEvenement(int x, int y) {
		this.new_x = x;
		this.new_y = y;
		return null;
	}
	
	@Override
	/**
	 * Methode permettant de gerer l'evenement onclick sur un bouton.
	 */
	public void onClick(View v) {
		boolean suite;
		switch(v.getId()){
			case R.id.suivant:
				suite = checkForm();
				if(suite){
					this._parentActivity.ViewPlaceOnTheMap(new OnPositionSelectOneShotListener(){
	
						@Override
						public void OnPositionSelect(Point position) {
							int x = position.x;
							int y = position.y;
							Evenement newEvent = FragmentModifEvt.this.genereEvenement(x, y);
							//et ensuite on enregistre cet evenement sur le serveur
				    		
						}}, this._oldPosition, false);
				}
				break;
			case R.id.enregistrer:
				suite = checkForm();
				if(suite){
					updateEvt();
					this._parentActivity.switchFragment(new FragmentListeEvt());
				}
				break;
		}
	}
	
	/**
	 * Methode qui met a jour l'evenement, en recuperant les informations saisit par l'utilisateur
	 * et les envoyant au serveur au format JSON, qui retourne la reponse au format JSON. 
	 */
	public void updateEvt() {
		TextView name = (TextView) this._view.findViewById(R.id.textNom);
		TextView prenom = (TextView) this._view.findViewById(R.id.textPrenom);
		TextView train = (TextView) this._view.findViewById(R.id.textNumTrain);
		TextView gareDep = (TextView) this._view.findViewById(R.id.gareDep);
		TextView heureDep = (TextView) this._view.findViewById(R.id.heureDep);
		TextView gareArr = (TextView) this._view.findViewById(R.id.gareArr);
		TextView heureArr = (TextView) this._view.findViewById(R.id.heureArr);
		TextView contact = (TextView) this._view.findViewById(R.id.contact);
		TextView contactExterne = (TextView) this._view.findViewById(R.id.contactExt);
		
		Client client = new Client(name.getText().toString(), prenom.getText().toString(), 
				contact.getText().toString(), contactExterne.getText().toString());
		
		Assistance assistance = new Assistance(gareDep.getText().toString(), gareArr.getText().toString(), 
				heureDep.getText().toString(), heureArr.getText().toString());
		
		this.newEvt = new Evenement(client, train.getText().toString(), 
				this._parentActivity.getUtilisateur().getLogin(), assistance);
		
		JSONObject jsonObject= new JSONObject();
		try {
			jsonObject.put("idEvent", this.idEvenement);
			jsonObject.put("nom", this.newEvt.getClient().getNom());
			jsonObject.put("prenom", this.newEvt.getClient().getPrenom());
			jsonObject.put("train", this.newEvt.getNumTrain());
			jsonObject.put("gareDep", this.newEvt.getAssistance().getDepart());
			jsonObject.put("heureDep", this.newEvt.getAssistance().getHeureDebarquement());
			jsonObject.put("gareArr", this.newEvt.getAssistance().getArrivee());
			jsonObject.put("heureArr", this.newEvt.getAssistance().getHeureArriveeEstime());
			jsonObject.put("contact", this.newEvt.getClient().getNumClient());
			jsonObject.put("contactExt", this.newEvt.getClient().getContactExterneClient());

			jsonObject.put("x", this.new_x);
			jsonObject.put("y", this.new_y);

    		Log.i("LG", "Select back point on the map:"+this.new_x+";"+this.new_y);
			jsonObject.put("agent", this._parentActivity.getUtilisateur().getLogin());
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		AsynJsonHttp thread = new AsynJsonHttp(URL_update, this._parentActivity);
		thread.execute(jsonObject);
		JSONObject jsonReturn = null;
		try {
			jsonReturn = thread.get();
			Log.i(TAG, "test - envoi: "+jsonObject.toString());
			Log.i(TAG, "test - modif: "+jsonReturn.toString());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
	
}

