package com.example.terassiste.views;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.terassiste.R;
import com.example.terassiste.http.HttpClient;

public class Connexion extends Activity {
	private static final String TAG = "Connexion";
	private static final String URL = "http://terassiste.alwaysdata.net/connect";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connexion);
    }
    
    public void boutonConnexion(View v) {
    	TextView id = (TextView) findViewById(R.id.textIdentifiant);
    	TextView pass = (TextView) findViewById(R.id.textPass);
    	
    	JSONObject jsonObject= new JSONObject();
    	
		try {
			jsonObject.put("id", id.getText());
            jsonObject.put("pass", pass.getText());


			Log.i(TAG, jsonObject.toString(2));

		} catch (JSONException e) {
			e.printStackTrace();
		}

		JSONObject jsonRec = HttpClient.SendHttpPost(URL, jsonObject);
		Log.i("TAG", jsonRec.toString());
		//pass.setText(jsonRec.toString());
		
    
    }
    
}