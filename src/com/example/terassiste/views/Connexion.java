package com.example.terassiste.views;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.terassiste.R;
import com.example.terassiste.R.id;
import com.example.terassiste.R.layout;
import com.example.terassiste.http.HttpClient;

public class Connexion extends Activity {
	private static final String TAG = "Connexion";
	private static final String URL = "http://www.yourdomain.com:80";
	
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

			JSONObject header = new JSONObject();
			header.put("deviceType","Android"); // Device type
			header.put("deviceVersion","4.4"); // Device OS version
			jsonObject.put("header", header);

			Log.i(TAG, jsonObject.toString(2));

		} catch (JSONException e) {
			e.printStackTrace();
		}

		JSONObject jsonRec = HttpClient.SendHttpPost(URL, jsonObject);

    
    }
    
}