package com.example.terassiste.fragments;

import java.util.concurrent.ExecutionException;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.terassiste.R;
import com.example.terassiste.MainActivity;
import com.example.terassiste.http.AsynJsonHttp;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FragmentModifEvt extends FragmentCreateEvt {
	private int idEvenement;
	
	private static final String TAG = "FragmentDetailEvt";
	private static final String URL = "http://terassistee.netai.net/detailevent.php";

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
				this._view = inflater.inflate(R.layout.fragment_create_evt, container, false);
				this._view.findViewById(R.id.suivant).setOnClickListener(this);
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
	
	public void fillForm(JSONObject jsonReturn) {
		try {
			
			EditText nom = (EditText) this._view.findViewById(R.id.textNom);
			nom.setText(jsonReturn.getString("nom"));
			
			EditText prenom = (EditText) this._view.findViewById(R.id.textPrenom);
			prenom.setText(jsonReturn.getString("prenom"));
			
			EditText train = (EditText) this._view.findViewById(R.id.textNumTrain);
			train.setText(jsonReturn.getString("train"));
			
			EditText gareDep = (EditText) this._view.findViewById(R.id.gareDep);
			gareDep.setText(jsonReturn.getString("gareDep"));
			
			EditText heureDep = (EditText) this._view.findViewById(R.id.heureDep);
			heureDep.setText(jsonReturn.getString("heureDep"));
			
			EditText gareArr = (EditText) this._view.findViewById(R.id.gareArr);
			gareArr.setText(jsonReturn.getString("gareArr"));
			
			EditText heureArr = (EditText) this._view.findViewById(R.id.heureArr);
			heureArr.setText(jsonReturn.getString("heureArr"));

		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
}

