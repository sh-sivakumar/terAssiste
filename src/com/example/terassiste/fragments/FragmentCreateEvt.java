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
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

/**
 * Classe permettant de gerer le systeme de creation d'evenement (fragment)
 * @author Shinthujan, Jian, Walid, Wally, Youssef
 */
public class FragmentCreateEvt extends Fragment implements OnClickListener {
	
	protected View 			_view;
	protected MainActivity 	_parentActivity;
	protected Point _oldPosition = null;
	protected int new_x = -1;
	protected int new_y = -1;
	
	private Evenement evt;
	protected Drawable defaultTextViewBackground;
	
	protected AutoCompleteTextView gareDep;
	protected AutoCompleteTextView gareArr;
	protected AutoCompleteTextView train;
	private static final String TAG 				= "FragmentCreateEvt";
	private static final String URL 				= "http://terassistee.netai.net/createevent.php";
	
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
		this._view = inflater.inflate(R.layout.fragment_create_evt, container, false);
		this._view.findViewById(R.id.suivant).setOnClickListener(this);
		this._view.findViewById(R.id.enregistrer).setOnClickListener(this);
		
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
	
		return this._view;
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
				    		Log.i("LG", "Select back point on the map:"+x+";"+y);
							Evenement newEvent = FragmentCreateEvt.this.genereEvenement(x, y);
							//et ensuite on enregistre cet evenement sur le serveur
				    		
						}}, this._oldPosition, false);
				}
				break;
			case R.id.enregistrer:
				suite = checkForm();
				if(suite){
					addEvt();
					this._parentActivity.switchFragment(new FragmentListeEvt());
				}
				break;
		}
		
	}
	
	/**
	 * Methode qui permet de gerer l'ajout d'un evenement.
	 * Elle recupere les informations saisit par l'utilisateur et les envoi
	 * au format JSON, au serveur qui se charge de retourner la reponse au format JSON.
	 */
	public void addEvt() {
		TextView name = (TextView) this._view.findViewById(R.id.textNom);
		TextView prenom = (TextView) this._view.findViewById(R.id.textPrenom);
		AutoCompleteTextView gareDep = (AutoCompleteTextView) this._view.findViewById(R.id.gareDep);
		TextView heureDep = (TextView) this._view.findViewById(R.id.heureDep);
		AutoCompleteTextView gareArr = (AutoCompleteTextView) this._view.findViewById(R.id.gareArr);
		TextView heureArr = (TextView) this._view.findViewById(R.id.heureArr);
		TextView contact = (TextView) this._view.findViewById(R.id.contact);
		TextView contactExterne = (TextView) this._view.findViewById(R.id.contactExt);
		
		Client client = new Client(name.getText().toString(), prenom.getText().toString(), 
				contact.getText().toString(), contactExterne.getText().toString());
		
		Assistance assistance = new Assistance(gareDep.getText().toString(), gareArr.getText().toString(), 
				heureDep.getText().toString(), heureArr.getText().toString());
		
		this.evt = new Evenement(client, train.getText().toString(), 
				this._parentActivity.getUtilisateur().getLogin(), assistance);
		
		JSONObject jsonObject= new JSONObject();
		try {
			jsonObject.put("nom", this.evt.getClient().getNom());
			jsonObject.put("prenom", this.evt.getClient().getPrenom());
			jsonObject.put("train", this.evt.getNumTrain());
			jsonObject.put("gareDep", this.evt.getAssistance().getDepart());
			jsonObject.put("heureDep", this.evt.getAssistance().getHeureDebarquement());
			jsonObject.put("gareArr", this.evt.getAssistance().getArrivee());
			jsonObject.put("heureArr", this.evt.getAssistance().getHeureArriveeEstime());
			jsonObject.put("x", this.new_x);
			jsonObject.put("y", this.new_y);
			jsonObject.put("agent", this._parentActivity.getUtilisateur().getLogin());
			jsonObject.put("contact", this.evt.getClient().getNumClient());
			jsonObject.put("contactExt", this.evt.getClient().getContactExterneClient());
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
	}
	
	public Evenement genereEvenement(int x, int y) {
		this.new_x = x;
		this.new_y = y;
		this._view.findViewById(R.id.enregistrer).setVisibility(View.VISIBLE);
		return null;
	}
	
	/**
	 * Fonction qui verifie si les informations saisit par l'utilisateur sont valides.
	 * Retourne true si les informations sont correctes sinon false.
	 * @return check
	 */
	public boolean checkForm() {
		boolean check = true;
    	TextView heureDep = (TextView) this._view.findViewById(R.id.heureDep);
    	TextView heureArr = (TextView) this._view.findViewById(R.id.heureArr);

    	if(!checkHours(heureDep.getText().toString()) && heureDep.getText().toString().length()>0) {
    		heureDep.setTextColor(Color.RED);
    		check = false;
    	} else {
    		heureDep.setTextColor(Color.BLACK);
    	}
    	
    	if(!checkHours(heureArr.getText().toString()) && heureArr.getText().toString().length()>0) {
    		heureArr.setTextColor(Color.RED);
    		check = false;
    	} else {
    		heureArr.setTextColor(Color.BLACK);
    	}
    	
    	TextView nomPmr = (TextView)this._view.findViewById(R.id.textNom);
    	TextView prenomPmr = (TextView)this._view.findViewById(R.id.textPrenom);
    	TextView numTain = (TextView)this._view.findViewById(R.id.textNumTrain);
    	TextView gareDep = (TextView)this._view.findViewById(R.id.gareDep);
    	TextView gareArr = (TextView) this._view.findViewById(R.id.gareArr);
    	TextView[] textviews = new TextView[]{
    			nomPmr, prenomPmr, numTain, gareDep, gareArr
    	};
    	for(int i=0;i<textviews.length;++i){
    		TextView tv = textviews[i];
    		if(tv.getText().length() == 0){
    			tv.setBackgroundColor(0xAAFF0000);
    			check = false;
    		}else{
    			tv.setBackground(defaultTextViewBackground);
    		}
    	}
        
		return check;
	}
	
	/**
	 * Fonction qui prend en parametre un String d'heure et verifie si sont format est correcte.
	 * Format hh:mm
	 * @param heure
	 * @return true, si correcte sinon false
	 */
	public boolean checkHours(String heure) {
		boolean trouve = (heure.indexOf(":") != -1);
		
		if(trouve) {
			String[] sep = heure.split(":");
			if(sep.length == 2) {
				if(Integer.parseInt(sep[0]) <= 24) {
					if(Integer.parseInt(sep[1]) <= 60) {
						return true;
					}
				}
			}
		}
		
		return false;
	}
}

