package com.example.terassiste.views;

import android.app.Activity;
import android.os.Bundle;

import com.example.terassiste.R;

public class ViewConnexion extends Activity {
	private static final String TAG = "ViewConnexion";
	//private static final String URL = "http://terassiste.alwaysdata.net/connect";
	private static final String URL = "http://terassistee.netai.net/test.php";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_connexion);
    }
}