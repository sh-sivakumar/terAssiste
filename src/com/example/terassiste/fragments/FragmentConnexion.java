package com.example.terassiste.fragments;

import java.util.concurrent.ExecutionException;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.terassiste.MainActivity;
import com.example.terassiste.R;
import com.example.terassiste.http.AsynJsonHttp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class FragmentConnexion extends Fragment implements OnClickListener {
	private static final String TAG = "ViewConnexion";
	private static final String URL = "http://terassistee.netai.net/test.php";
	
	private MainActivity	_mainActivity;
	private View 			_view;
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		Log.i("LG", "FragmentConnexion attach");
		this._mainActivity = (MainActivity) activity;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		this._mainActivity.actionBar.setDisplayHomeAsUpEnabled(false);
		this._mainActivity.menuOk = false;
		this._view = inflater.inflate(R.layout.fragment_connexion, container, false);
		this._view.findViewById(R.id.connecter).setOnClickListener(this);
		
		return this._view;
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.connecter:
			try {
				boutonConnexion(v);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			break;
		}
		
	}
	
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
		
		AsynJsonHttp thread = new AsynJsonHttp(URL);
		thread.execute(jsonObject);
		try {
			JSONObject jsonReturn = thread.get();

			if(jsonReturn.getBoolean("result")) {
				this._mainActivity.switchFragment(new FragmentListeEvt());
			}

			Log.i(TAG, "test: "+jsonReturn.toString());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
    }
}
