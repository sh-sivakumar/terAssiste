package com.example.terassiste.fragments;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.terassiste.R;
import com.example.terassiste.MainActivity;

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
import android.widget.TextView;

public class FragmentModifEvt extends FragmentCreateEvt {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		this._parentActivity.actionBar.setDisplayHomeAsUpEnabled(true);
		this._parentActivity.menuOk = true;
		this._view = inflater.inflate(R.layout.fragment_create_evt, container, false);
		this._view.findViewById(R.id.suivant).setOnClickListener(this);
		
		/* ----- Debut : Test JSON ----- */
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
		/* ----- Fin : Test JSON ----- */
		
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
	
		return this._view;
	}
	
}

