package com.example.terassiste.fragments;

import java.util.concurrent.ExecutionException;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.terassiste.R;
import com.example.terassiste.MainActivity;
import com.example.terassiste.PlaceSelect.OnPositionSelectOneShotListener;
import com.example.terassiste.http.AsynJsonHttp;
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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FragmentModifEvt extends FragmentCreateEvt {
	private int idEvenement;
	
	private static final String TAG = "FragmentDetailEvt";
	private static final String URL = "http://terassistee.netai.net/detailevent.php";
	private static final String URL_update = "http://terassistee.netai.net/updateevent.php";

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
				this._view.findViewById(R.id.enregistrer).setOnClickListener(this);
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
	public void onClick(View v) {
		switch(v.getId()){
			case R.id.suivant:
				boolean suite = checkForm();
				if(suite){
					this._parentActivity.ViewPlaceOnTheMap(new OnPositionSelectOneShotListener(){
	
						@Override
						public void OnPositionSelect(Point position) {
							int x = position.x;
							int y = position.y;
				    		Log.i("LG", "Select back point on the map:"+x+";"+y);
							Evenement newEvent = FragmentModifEvt.this.genereEvenement(x, y);
							//et ensuite on enregistre cet evenement sur le serveur
				    		
						}}, this._oldPosition, false);
				}
				break;
			case R.id.enregistrer:
				updateEvt();
				this._parentActivity.switchFragment(new FragmentListeEvt());
				break;
		}
	}
	
	public void updateEvt() {
		TextView name = (TextView) this._view.findViewById(R.id.textNom);
		TextView prenom = (TextView) this._view.findViewById(R.id.textPrenom);
		TextView train = (TextView) this._view.findViewById(R.id.textNumTrain);
		TextView gareDep = (TextView) this._view.findViewById(R.id.gareDep);
		TextView heureDep = (TextView) this._view.findViewById(R.id.heureDep);
		TextView gareArr = (TextView) this._view.findViewById(R.id.gareArr);
		TextView heureArr = (TextView) this._view.findViewById(R.id.heureArr);
		JSONObject jsonObject= new JSONObject();
		try {
			jsonObject.put("idEvent", this.idEvenement);
			jsonObject.put("nom", name.getText().toString());
			jsonObject.put("prenom", prenom.getText().toString());
			jsonObject.put("train", train.getText().toString());
			jsonObject.put("gareDep", gareDep.getText().toString());
			jsonObject.put("heureDep", heureDep.getText().toString());
			jsonObject.put("gareArr", gareArr.getText().toString());
			jsonObject.put("heureArr", heureArr.getText().toString());
			jsonObject.put("x", this.new_x);
			jsonObject.put("y", this.new_y);
			jsonObject.put("agent", this._parentActivity.getLogin());
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		AsynJsonHttp thread = new AsynJsonHttp(URL_update);
		thread.execute(jsonObject);
		JSONObject jsonReturn = null;
		try {
			jsonReturn = thread.get();
			Log.i(TAG, "test - reception: "+jsonObject.toString());
			Log.i(TAG, "test - modif: "+jsonReturn.toString());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
	
}

