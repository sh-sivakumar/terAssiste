package com.example.terassiste.fragments;

import java.util.concurrent.ExecutionException;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.terassiste.MainActivity;
import com.example.terassiste.R;
import com.example.terassiste.http.AsynJsonHttp;
import com.example.terassiste.http.OnDataReturnListener;
import com.example.terassiste.metier.Agent;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.TextView;

/**
 * Classe permettant de gerer le systeme de connection (fragment)
 * @author Shinthujan, Jian, Walid, Wally, Youssef
 */
public class FragmentConnexion extends Fragment implements OnClickListener {
	private static final String TAG = "FragmentConnexion";
	private static final String URL = "http://terassistee.netai.net/connect.php";
	
	private MainActivity	_mainActivity;
	private View 			_view;
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		Log.i("LG", "FragmentConnexion attach");
		this._mainActivity = (MainActivity) activity;
	}
	
	@Override
	/**
	 * Methode se declenchant a la creation de la vue.
	 */
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		this._mainActivity.actionBar.setDisplayHomeAsUpEnabled(false);
		this._mainActivity.menuOk = false;
		this._view = inflater.inflate(R.layout.fragment_connexion, container, false);
		this._view.findViewById(R.id.connecter).setOnClickListener(this);
		
		return this._view;
	}

	@Override
	/**
	 * Methode permettant de gerer l'evenement onclick sur un bouton.
	 */
	public void onClick(View v) {
		switch(v.getId()) {
			//bouton "se connecter"
			case R.id.connecter:
				try {
					boutonConnexion(v);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				break;
		}
		
	}
	
	/**
	 * Methode qui permet de gerer la connexion.
	 * Elle recupere l'identifiant et le mot de passe saisit par l'utilisateur et envoi ces informations
	 * au format JSON, au serveur qui se charge de retourner la reponse au format JSON.
	 * @param v
	 * @throws JSONException
	 */
	public void boutonConnexion(View v) throws JSONException {
    	
    	TextView id = (TextView) this._view.findViewById(R.id.textIdentifiant);
    	TextView pass = (TextView) this._view.findViewById(R.id.textPass);
    	
    	JSONObject jsonObject= new JSONObject();
    	
		try {
			jsonObject.put("login", id.getText());
            jsonObject.put("password", pass.getText());
		} catch (JSONException e) {
			e.printStackTrace();
		}

		AsynJsonHttp thread = new AsynJsonHttp(URL, this._mainActivity);
		thread.setDataListener(new OnDataReturnListener(){

			@Override
			public void OnDataReturn(JSONObject jsonReturn) {
				try {
					Log.i(TAG, "test: "+jsonReturn.toString());
					if(jsonReturn.getBoolean("result")) {
						//this._mainActivity.setLogin(id.getText().toString());
						
						String nom = jsonReturn.getString("nom");;
						String prenom = jsonReturn.getString("prenom");
						TextView id =(TextView) FragmentConnexion.this._view.findViewById(R.id.textIdentifiant);
						String login = id.getText().toString();
						Agent utilisateur = new Agent(nom, prenom, login);
						FragmentConnexion.this._mainActivity.setUtilisateur(utilisateur);
						
						FragmentConnexion.this._mainActivity.switchFragment(new FragmentListeEvt());
					}

					Log.i(TAG, "test: "+jsonReturn.toString());
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		thread.execute(jsonObject);
    }
}
