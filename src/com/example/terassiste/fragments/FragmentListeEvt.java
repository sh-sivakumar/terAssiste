package com.example.terassiste.fragments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.example.terassiste.MainActivity;
import com.example.terassiste.R;

public class FragmentListeEvt extends Fragment {

	
	private MainActivity	_mainActivity;
	private View 			_view;
	
	private ExpandableListView expandableList = null;
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		Log.i("LG", "FragmentConnexion attach");
		this._mainActivity = (MainActivity) activity;
	}
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        View _view = inflater.inflate(R.layout.fragment_liste_evt, container, false);
        this.expandableList = (ExpandableListView) _view.findViewById(R.id.list);
        
        List<Map<String, Object>> dataCache = null;
		try {
			dataCache = fetchAllData();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		if(dataCache != null) {
			ELVAdapter adapter = new ELVAdapter(inflater, dataCache);
			expandableList.setAdapter(adapter);
		} else {
			LinearLayout layout = (LinearLayout) _view.findViewById(R.id.layout_background);
			layout.setBackground(getResources().getDrawable(R.drawable.aucun_evt));
		}
		
        return _view;
    }
    
	public List<Map<String, Object>> fetchAllData() throws JSONException {
		
		JSONObject object = new JSONObject();
		/* ----- Debut : Test JSON ----- */
		
		JSONArray jsonPMR = new JSONArray();
		JSONObject pmr1 = new JSONObject();
		try {
			pmr1.put("nom", "Xavier");
			pmr1.put("prenom", "XXX");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		jsonPMR.put(pmr1);
		JSONObject pmr2 = new JSONObject();
		try {
			pmr2.put("nom", "XXX");
			pmr2.put("prenom", "Xavier");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		jsonPMR.put(pmr2);
		
		JSONArray jsonPMR2 = new JSONArray();
		JSONObject pmr3 = new JSONObject();
		try {
			pmr3.put("nom", "Okman");
			pmr3.put("prenom", "Oui");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		jsonPMR2.put(pmr3);

		
		JSONArray jsonArr = new JSONArray();
		JSONObject obj1 = new JSONObject();
		try {
			obj1.put("train", "7567");
			obj1.put("nbPMR", 2);
			obj1.put("pmr", jsonPMR);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		jsonArr.put(obj1);
		JSONObject obj2 = new JSONObject();
		try {
			obj2.put("train", "1001");
			obj2.put("nbPMR", 1);
			obj2.put("pmr", jsonPMR2);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		jsonArr.put(obj2);
		JSONObject obj3 = new JSONObject();
		try {
			obj3.put("train", "2014DW");
			obj3.put("nbPMR", 2);
			obj3.put("pmr", jsonPMR);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		jsonArr.put(obj3);
		
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
				oneRow.put("pmr", obj.getJSONArray("pmr"));
				data.add(oneRow);
			}
		}
				
		return data;
	}
	
	private class OnMainItemClickListener implements OnClickListener {
		String train;
		String nom;
		String prenom;
		
		public OnMainItemClickListener(String train, String nom, String prenom) {
			this.train = train;
			this.nom = nom;
			this.prenom = prenom;
		}

		@Override
		public void onClick(View v) {
			Log.i("LG", "TEST = Bouton clique ->" + nom + " " + prenom + " - train: "+ train);
			_mainActivity.switchFragment(new FragmentDetailEvt(nom, prenom, train));
		}
	}
    
    public class ELVAdapter extends BaseExpandableListAdapter {
    	 
        private List<Map<String, Object>> groupes;
        private LayoutInflater inflater;
     
        public ELVAdapter(LayoutInflater inflater, List<Map<String, Object>> groupes) {
            this.groupes = groupes;
            this.inflater = inflater;
        }
     
        @Override
        public boolean areAllItemsEnabled() {
            return true;
        }
     
        public long getChildId(int gPosition, int cPosition) {
            return cPosition;
        }
     
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            final Object name = (Object) getChild(groupPosition, childPosition);
     
            ChildViewHolder childViewHolder;
            if (convertView == null) {
                childViewHolder = new ChildViewHolder();
                convertView = inflater.inflate(R.layout.fragment_liste_evt_pmr, null);
    
                childViewHolder.textPmrName = (TextView) convertView.findViewById(R.id.pmr_name);
                
            } else {
                childViewHolder = (ChildViewHolder) convertView.getTag();
            }
            
            clickBoutonElmt(groupPosition, childPosition, convertView);
            convertView.setTag(childViewHolder);
     
            childViewHolder.textPmrName.setText(name.toString());
     
            return convertView;
        }
        
        public void clickBoutonElmt(int groupPosition, int childPosition, View convertView) {
        	Map<String, Object> map = (Map<String, Object>) groupes.get(groupPosition);
        	String train = map.get("train").toString();
        	String nom = getNomPMR(groupPosition, childPosition);
			String prenom = getPrenomPMR(groupPosition, childPosition);
			
            LinearLayout layout = (LinearLayout) convertView.findViewById(R.id.btPMR);
            layout.setOnClickListener(new OnMainItemClickListener(train, nom, prenom));
        	
        }
     
        public Object getGroup(int gPosition) {
            return groupes.get(gPosition);
        }
     
        public int getGroupCount() {
            return groupes.size();
        }
     
        public long getGroupId(int gPosition) {
            return gPosition;
        }
     
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            GroupViewHolder gholder;
     
            Map<String, Object> group = (Map<String, Object>) getGroup(groupPosition);
     
            if (convertView == null) {
                gholder = new GroupViewHolder();
     
                convertView = inflater.inflate(R.layout.fragment_single_liste_evt, null);
     
                gholder.textTrain = (TextView) convertView.findViewById(R.id.fragment_main_content_train);
                gholder.textNb = (TextView) convertView.findViewById(R.id.fragment_main_content_nbpmr);
            
                convertView.setTag(gholder);
            } else {
                gholder = (GroupViewHolder) convertView.getTag();
            }
     
            gholder.textTrain.setText(group.get("train").toString());
            gholder.textNb.setText(group.get("nbPMR").toString());
     
            return convertView;
        }
     
        public boolean hasStableIds() {
            return true;
        }
     
        public boolean isChildSelectable(int arg0, int arg1) {
            return true;
        }
     
        class GroupViewHolder {
            public TextView textTrain;
            public TextView textNb;
        }
     
        class ChildViewHolder {
            public TextView textPmrName;
        }
        
		@Override
		public Object getChild(int groupPosition, int childPosition) {
			String nom = getNomPMR(groupPosition, childPosition);
			String prenom = getPrenomPMR(groupPosition, childPosition);
			return nom + " " + prenom;
		}
		
		public String getNomPMR(int groupPosition, int childPosition) {
			JSONArray pmr = (JSONArray) groupes.get(groupPosition).get("pmr");
			JSONObject obj = null;
			try {
				obj = pmr.getJSONObject(childPosition);
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
			try {
				String nom = obj.getString("nom");
				return nom;
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return null;
		}
		
		public String getPrenomPMR(int groupPosition, int childPosition) {
			JSONArray pmr = (JSONArray) groupes.get(groupPosition).get("pmr");
			JSONObject obj = null;
			try {
				obj = pmr.getJSONObject(childPosition);
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
			try {
				String prenom = obj.getString("prenom");
				return prenom;
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		public int getChildrenCount(int groupPosition) {
			JSONArray pmr = (JSONArray) groupes.get(groupPosition).get("pmr");
			return pmr.length();
		}
     
    }

}
