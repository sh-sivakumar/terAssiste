package com.example.terassiste.fragments;

import org.json.JSONException;

import com.example.terassiste.MainActivity;
import com.example.terassiste.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;

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
		this._view = inflater.inflate(R.layout.fragment_connexion, container, false);
		this._view.findViewById(R.id.connecter).setOnClickListener(this);
		
		return this._view;
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.connecter:
			try {
				this._mainActivity.boutonConnexion(v, TAG, URL);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			break;
		}
		
	}
}
