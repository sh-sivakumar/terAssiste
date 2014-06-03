package com.example.terassiste.fragments;


import com.example.terassiste.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentConnexion extends Fragment{
	private View _view;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		this._view = inflater.inflate(R.layout.view_connexion, container, false);
		return this._view;
	}
}
