package com.example.terassiste.http;

import org.json.JSONObject;
import android.os.AsyncTask;

/**
 * Classe qui cree un thread en parallele du thread principal et qui appelle la classe permettant de gerer
 * la connection a un serveur.
 * @author Shinthujan, Jian, Walid, Wally, Youssef
 */
public class AsynJsonHttp extends AsyncTask<JSONObject, Integer, JSONObject>{
	
	private String url;
	
	public AsynJsonHttp(String URL){
		this.url = URL;
	}
	
	
	@Override
	protected JSONObject doInBackground(JSONObject... params) {
		JSONObject jsonRec = HttpClients.SendHttpPost(url, params[0]);
		
		return jsonRec;
	}
	
	protected void onProgressUpdate(Integer... progress) {
    }

    protected void onPostExecute(Long result) {
    }
}
