package com.example.terassiste.fragments;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.terassiste.R;
import com.example.terassiste.MainActivity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class FragmentListeEvt extends Fragment {
	
	private ListView					_mainContainerListView;
	private SimpleAdapter				_adapter;
	private List<Map<String, Object>>	_dataCache;
	private MainActivity				_parentActivity;
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		this._parentActivity = (MainActivity) activity;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		try {
			this._dataCache = fetchAllData();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		this._mainContainerListView = (ListView) inflater.inflate(R.layout.fragment_liste_evt, container, false);
		this._mainContainerListView.setOnItemClickListener(new OnMainItemClickListener());

		this._adapter = new SimpleAdapter(this._parentActivity, this._dataCache, R.layout.fragment_single_liste_evt, new String[] { "train", "nbPMR" }, new int[] {
				R.id.fragment_main_content_train, R.id.fragment_main_content_nbpmr });

		this._mainContainerListView.setAdapter(_adapter);
		return this._mainContainerListView;
	}
	
	
	public List<Map<String, Object>> fetchAllData() throws JSONException {
		
		/* ----- Debut : Test JSON ----- */
		
		JSONArray jsonArr = new JSONArray();
		JSONObject obj1 = new JSONObject();
		try {
			obj1.put("train", "7567");
			obj1.put("nbPMR", 2);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		jsonArr.put(obj1);
		JSONObject obj2 = new JSONObject();
		try {
			obj2.put("train", "1001");
			obj2.put("nbPMR", 1);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		jsonArr.put(obj2);
		JSONObject obj3 = new JSONObject();
		try {
			obj3.put("train", "2014DW");
			obj3.put("nbPMR", 1);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		jsonArr.put(obj3);
		JSONObject object = new JSONObject();
		try {
			object.put("result", true);
		    object.put("events", jsonArr);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		/* ----- Fin : Test JSON ----- */
		
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		
		if(object.getBoolean("result") == true) {
			JSONArray jArr = object.getJSONArray("events");
			for (int i=0; i < jArr.length(); i++) {
			    JSONObject obj = jArr.getJSONObject(i);

				Map<String, Object> oneRow = new HashMap<String, Object>();
				oneRow.put("train", "Train n: " + obj.getString("train"));
				oneRow.put("nbPMR", obj.getInt("nbPMR") + " PMR a bord.");
				data.add(oneRow);
			}
		}
				
		return data;
	}

	private class OnMainItemClickListener implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
			Log.i("LG", "Select main item position: " + position + " with ID:" + id + ", item info:");
			
			FragmentDetailEvt detail = new FragmentDetailEvt();
			FragmentListeEvt.this._parentActivity.switchFragment(detail);
			
		}

	}
	
}

