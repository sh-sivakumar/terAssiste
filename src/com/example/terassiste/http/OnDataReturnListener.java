package com.example.terassiste.http;

import org.json.JSONObject;

/**
 * Interface a passer a la fin de traitement HTTP
 * @author Shinthujan, Jian, Walid, Wally, Youssef
 *
 */
public interface OnDataReturnListener {
	public void OnDataReturn(JSONObject jobj);
}