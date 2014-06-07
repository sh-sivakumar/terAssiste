package com.example.terassiste.fragments;

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
import android.widget.TextView;

public class FragmentCreateEvt extends Fragment implements OnClickListener {
	
	private View 			_view;
	private MainActivity 	_parentActivity;
	
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
		this._view = inflater.inflate(R.layout.fragment_create_evt, container, false);
		this._view.findViewById(R.id.suivant).setOnClickListener(this);
	
		return this._view;
	}
	
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.suivant:
			boolean suite = checkForm();
			//Fragment ajout de la place (avec le plan)
			//this._parentActivity.switchFragment(new FragmentCreateEvtTwo());
			break;
		}
		
	}
	
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
        
		return check;
	}
	
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

