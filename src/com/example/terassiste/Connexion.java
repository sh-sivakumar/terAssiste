package com.example.terassiste;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Connexion extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connexion);
    }
    
    public String boutonConnexion(View v) {
    	TextView id = (TextView) findViewById(R.id.textIdentifiant);
    	TextView pass = (TextView) findViewById(R.id.textPass);
    	
    	JSONObject jsonObject= new JSONObject();
        try {
            jsonObject.put("id", id.getText());
            jsonObject.put("pass", pass.getText());

            return jsonObject.toString();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "";
        }
    
    }
    
}