package com.example.terassiste.views;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.terassiste.R;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageButton;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class ViewMainActionBar extends ViewActionBar {
	LayoutInflater	_inflater;
	TextView _title;
	Spinner _tranporteurs;
	public ViewMainActionBar(Context context) {
		super(context);
		this._inflater = ((Activity) getContext()).getLayoutInflater();
	}
}
