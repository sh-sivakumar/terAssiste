package com.example.terassiste.fragments;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		this._dataCache = fetchAllData();
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		this._mainContainerListView = (ListView) inflater.inflate(R.layout.fragment_liste_evt, container, false);
		this._mainContainerListView.setOnItemClickListener(new OnMainItemClickListener());

		this._adapter = new SimpleAdapter(this._parentActivity, this._dataCache, R.layout.fragment_main_content, new String[] { "numero", "date", "lastStatus" }, new int[] {
				R.id.fragment_main_content_number, R.id.fragment_main_content_date, R.id.fragment_main_content_statut });

		this._mainContainerListView.setAdapter(_adapter);
		return this._mainContainerListView;
	}
	
	
	public List<Map<String, Object>> fetchAllData() {
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		
		String number = "2";
		long date = 03062014;
		String status = "ceci est un test";
		int id = 2;

		Map<String, Object> oneRow = new HashMap<String, Object>();
		oneRow.put("_id", id);
		oneRow.put("numero", number);
		oneRow.put("date", new Date(date).toString());
		oneRow.put("lastStatus", status);
		data.add(oneRow);
		
		oneRow.put("_id", id);
		oneRow.put("numero", number);
		oneRow.put("date", new Date(date).toString());
		oneRow.put("lastStatus", status);
		data.add(oneRow);
		
		return data;
	}

	private class OnMainItemClickListener implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
			Log.i("LG", "Select main item position: " + position + " with ID:" + id + ", item info:");
			
		}

	}
	
}

