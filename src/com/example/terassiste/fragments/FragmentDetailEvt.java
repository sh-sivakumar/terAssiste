package com.example.terassiste.fragments;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.terassiste.R;
import com.example.terassiste.MainActivity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentDetailEvt extends Fragment {
	private String nom;
	private String prenom;
	private String train;
	
	private View 			_view;
	private MainActivity	_parentActivity;
	
	public FragmentDetailEvt(String nom, String prenom, String train) {
		this.nom = nom;
		this.prenom = prenom;
		this.train = train;
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
		this._view = inflater.inflate(R.layout.fragment_evt_detail, container, false);
		
		/* ----- Debut : Test JSON ----- */
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
		/* ----- Fin : Test JSON ----- */
		
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
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return this._view;
	}
	
}

