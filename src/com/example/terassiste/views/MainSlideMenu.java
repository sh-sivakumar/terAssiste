package com.example.terassiste.views;

import com.example.terassiste.MainActivity;
import com.example.terassiste.R;
import com.example.terassiste.REQUEST_CODE;
import com.example.terassiste.SelectPlaceActivity;
import com.example.terassiste.fragments.FragmentConnexion;
import com.example.terassiste.fragments.FragmentCreateEvt;
import com.example.terassiste.fragments.FragmentListeEvt;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.view.View.OnClickListener;

public class MainSlideMenu extends LinearLayout implements OnClickListener{
	
	private MainActivity	_mainActivity;
	
	public MainSlideMenu(Context context, AttributeSet paramAttributeSet) {
		this(context);
	}

	public MainSlideMenu(Context context) {
		super(context);
		this._mainActivity = (MainActivity) context;
		_mainActivity.getLayoutInflater().inflate(R.layout.view_slide_menu, this, true);
		
		this.findViewById(R.id.slide_menu_deco).setOnClickListener(this);
		this.findViewById(R.id.slide_menu_quit).setOnClickListener(this);
		this.findViewById(R.id.slide_menu_consult_evt).setOnClickListener(this);
		this.findViewById(R.id.slide_menu_create_evt).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Log.i("LJ", "Click on the slide menu");
		switch(v.getId()){
			case R.id.slide_menu_deco:
				this._mainActivity.switchFragment(new FragmentConnexion());
				break;
			case R.id.slide_menu_consult_evt:
				this._mainActivity.switchFragment(new FragmentListeEvt());
				break;	
			case R.id.slide_menu_create_evt:
				this._mainActivity.switchFragment(new FragmentCreateEvt());
				break;	
			case R.id.slide_menu_quit:
				System.exit(0);
				break;
		}
		this._mainActivity.closeSlideMenu();
	}
	
}
