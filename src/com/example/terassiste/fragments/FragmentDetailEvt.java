package com.example.terassiste.fragments;

import com.example.terassiste.R;
import com.example.terassiste.MainActivity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentDetailEvt extends Fragment {
	
	private View _view;
	private MainActivity				_parentActivity;
	
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
		this._view = inflater.inflate(R.layout.fragment_evt_detail, container, false);

		return this._view;
	}
	
}

