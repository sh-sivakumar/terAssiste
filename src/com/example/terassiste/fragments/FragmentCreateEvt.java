package com.example.terassiste.fragments;

import com.example.terassiste.R;
import com.example.terassiste.MainActivity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;

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
		this._view = inflater.inflate(R.layout.fragment_create_evt, container, false);
		this._view.findViewById(R.id.suivant).setOnClickListener(this);
		return this._view;
	}
	
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.suivant:
			//Fragment ajout de la place (avec le plan)
			//this._parentActivity.switchFragment(new FragmentCreateEvtTwo());
			break;
		}
		
	}
	
}

