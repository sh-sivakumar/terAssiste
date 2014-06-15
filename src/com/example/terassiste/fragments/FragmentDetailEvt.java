package com.example.terassiste.fragments;

import java.util.concurrent.ExecutionException;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.terassiste.R;
import com.example.terassiste.MainActivity;
import com.example.terassiste.http.AsynJsonHttp;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Classe permettant de gerer le systeme de detail d'un evenement (fragment)
 * @author Shinthujan, Jian, Walid, Wally, Youssef
 */
public class FragmentDetailEvt extends Fragment implements OnClickListener {
	private int idEvenement;
	private String nom;
	private String prenom;
	private String train;
	private Point position;
	
	private View 				_view;
	private MainActivity		_parentActivity;
	
	private static final String TAG 				= "FragmentDetailEvt";
	private static final String URL 				= "http://terassistee.netai.net/detailevent.php";
	private static final String URL_delete 			= "http://terassistee.netai.net/deleteEvent.php";
	
	public FragmentDetailEvt(int id, String nom, String prenom, String train, Point pos) {
		this.idEvenement = id;
		this.nom = nom;
		this.prenom = prenom;
		this.train = train;
		this.position = pos;
		
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		this._parentActivity = (MainActivity) activity;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
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
			jsonReturn.put("heureDep", "15h55");
			jsonReturn.put("gareArr", "Saint Julien du Sault");
			jsonReturn.put("heureArr", "17h05");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		*/
		/* ----- Fin : Test JSON ----- */
		
		JSONObject jsonObject= new JSONObject();
		try {
			jsonObject.put("idEvent", this.idEvenement);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		AsynJsonHttp thread = new AsynJsonHttp(URL);
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
				this._view = inflater.inflate(R.layout.fragment_evt_detail, container, false);
				this._view.findViewById(R.id.evt_modif).setOnClickListener(this);
				this._view.findViewById(R.id.evt_end).setOnClickListener(this);
				fillForm(jsonReturn);
			} else {
				this._view = inflater.inflate(R.layout.empty_fragment, container, false);
				LinearLayout layout = (LinearLayout) _view.findViewById(R.id.layout_background_empty);
				layout.setBackground(getResources().getDrawable(R.drawable.evt_existe_pas));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return this._view;
	}
	
	/**
	 * Methode qui remplit les informations sur l'evenement, avec le JSON recut en parametre.
	 * @param jsonReturn
	 */
	public void fillForm(JSONObject jsonReturn) {
		try {
			TextView name = (TextView) this._view.findViewById(R.id.name_pmr);
			name.setText(jsonReturn.getString("nom") + " " + jsonReturn.getString("prenom"));
			
			TextView agent = (TextView) this._view.findViewById(R.id.name_agent);
			agent.setText("Ajoute par " + jsonReturn.getString("agent"));
			
			TextView train = (TextView) this._view.findViewById(R.id.num_train);
			train.setText("Train numero: " + jsonReturn.getString("train"));
			
			TextView gareDep = (TextView) this._view.findViewById(R.id.gare_dep);
			gareDep.setText(jsonReturn.getString("gareDep"));
			
			TextView heureDep = (TextView) this._view.findViewById(R.id.heure_dep);
			heureDep.setText(jsonReturn.getString("heureDep"));
			
			TextView gareArr = (TextView) this._view.findViewById(R.id.gare_arr);
			gareArr.setText(jsonReturn.getString("gareArr"));
			
			TextView heureArr = (TextView) this._view.findViewById(R.id.heure_arr);
			heureArr.setText(jsonReturn.getString("heureArr"));
			
			String contactPmrStr="Non defini";
			if(!jsonReturn.getString("contactPMR").isEmpty()) {
				contactPmrStr = jsonReturn.getString("contactPMR");
			}
			TextView contactPMR = (TextView) this._view.findViewById(R.id.contact_pmr);
			contactPMR.setText("Contact PMR :" + contactPmrStr);
			
			String contactExternePmrStr="Non defini";
			if(!jsonReturn.getString("contactPMRExterne").isEmpty()) {
				contactExternePmrStr = jsonReturn.getString("contactPMRExterne");
			}
			TextView contactPMRExterne = (TextView) this._view.findViewById(R.id.contact_pmr_externe);
			contactPMRExterne.setText("Contact Externe PMR :" + contactExternePmrStr);
			
			String contactAgentStr="Non defini";
			if(!jsonReturn.getString("contactAgent").isEmpty()) {
				contactAgentStr = jsonReturn.getString("contactAgent");
			}
			TextView contactAgent = (TextView) this._view.findViewById(R.id.contact_agent);
			contactAgent.setText("Contact agent :" + contactAgentStr);
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		Button buttonConsult = (Button) this._view.findViewById(R.id.buttonViewPosition);
		buttonConsult.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				FragmentDetailEvt.this._parentActivity.ViewPlaceOnTheMap(
						null, 
						FragmentDetailEvt.this.position,
						true);
			}
			
		});
	}

	@Override
	/**
	 * Methode permettant de gerer l'evenement onclick sur un bouton.
	 */
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.evt_modif:
			this._parentActivity.switchFragment(new FragmentModifEvt(this.idEvenement));
			break;
		case R.id.evt_end:
			deleteEvt();
			this._parentActivity.switchFragment(new FragmentListeEvt());
			break;
		}
		
	}
	
	/**
	 * Methode qui permet de supprimer l'evenement, via un appel au serveur.
	 */
	public void deleteEvt() {
		JSONObject jsonObject= new JSONObject();
		try {
			jsonObject.put("idEvent", this.idEvenement);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		AsynJsonHttp thread = new AsynJsonHttp(URL_delete);
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
	}
	
}

