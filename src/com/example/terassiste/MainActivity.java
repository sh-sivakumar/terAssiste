package com.example.terassiste;

import java.util.ArrayList;
import java.util.List;

import com.example.terassiste.PlaceSelect.OnPositionSelectOneShotListener;
import com.example.terassiste.fragments.FragmentConnexion;
import com.example.terassiste.metier.Agent;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;

@TargetApi(Build.VERSION_CODES.KITKAT)
@SuppressLint("NewApi")
public class MainActivity extends FragmentActivity {

	public ActionBar 		actionBar;
	public boolean 			menuOk 					= false;
	
	private FragmentManager	_fm;
	private DrawerLayout	_drawerLayout;
	public static final int	FRAGMENT_MAIN			= 0;
	public static final int	FRAGMENT_CONNEXION		= 1;
	public static final int	FRAGMENT_EVT_LIST		= 2;
	public static final int	FRAGMENT_CREATE_EVT		= 3;
	
	private Agent utilisateur;
	//String login;
	private List<String> _liste_numTrain = new ArrayList<String>();
	
    OnPositionSelectOneShotListener positionSelectListener = null;
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		this._drawerLayout = (DrawerLayout) findViewById(R.id.main_container);

		this.actionBar = getActionBar();
		
		this.actionBar.setDisplayShowTitleEnabled(false);
		this.actionBar.setDisplayHomeAsUpEnabled(false);
		this.actionBar.setDisplayShowCustomEnabled(true);

		this._fm = getSupportFragmentManager();
		this.switchFragment(new FragmentConnexion());
	}
	
	public void setListNumTrain(List<String> listeNumTrain){
		this._liste_numTrain = listeNumTrain;
	}
	
	public List<String> getListNumTrain(){
		return _liste_numTrain;
	}

    @Override  
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {  
    	if(resultCode == RESULT_OK){
    		int x = data.getIntExtra("x", Integer.MIN_VALUE);
    		int y = data.getIntExtra("y", Integer.MIN_VALUE);
    		if(this.positionSelectListener != null){
    			this.positionSelectListener.OnPositionSelect(new Point(x,y));
    		}
    		this.positionSelectListener = null;
    	}
    }
	
	/**
	 * Backward-compatible version of {@link ActionBar#getThemedContext()} that simply returns the
	 * {@link android.app.Activity} if <code>getThemedContext</code> is unavailable.
	 */
	@TargetApi(Build.VERSION_CODES.KITKAT)
	private Context getActionBarThemedContextCompat() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			return getActionBar().getThemedContext();
		} else {
			return this;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Log.i("LJ", "Click On:" + item.getTitle() + "-" + item.getItemId());
		if(menuOk) { 
			switch (item.getItemId()) {
				case android.R.id.home:
					if (this._drawerLayout.isDrawerOpen(Gravity.LEFT)) {
						this._drawerLayout.closeDrawer(Gravity.LEFT);
					} else
						this._drawerLayout.openDrawer(Gravity.LEFT);
					break;
	
				default:
					break;
			}
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void switchFragment(Fragment fragment) {
		android.support.v4.app.FragmentTransaction transaction = this._fm.beginTransaction();
		transaction.replace(R.id.fragment_main, fragment);
		transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
		transaction.commit();
	}

	public void closeSlideMenu() {
		this._drawerLayout.closeDrawer(Gravity.LEFT);
		Log.i("LJ", "Close slide menu");
	}

	public void openSlideMenu() {
		this._drawerLayout.openDrawer(Gravity.LEFT);
		Log.i("LJ", "Open slide menu");
	}
	

    public void ViewPlaceOnTheMap(OnPositionSelectOneShotListener listener, Point oldPoint, boolean readOnly){
		Intent switchActivityIntent = new Intent(this, SelectPlaceActivity.class);
		int requestCode;
    	if(oldPoint == null){
    		requestCode = REQUEST_CODE.GET_POINT_ON_MAP;
		}else{
			requestCode = REQUEST_CODE.MODIFIE_POINT_ON_MAP;
			switchActivityIntent.putExtra("x", oldPoint.x);
			switchActivityIntent.putExtra("y", oldPoint.y);
		}
    	switchActivityIntent.putExtra("readOnly", readOnly);
    	switchActivityIntent.putExtra("request_code", requestCode);
    	this.positionSelectListener = listener;
    	
		this.startActivityForResult(switchActivityIntent, requestCode);
    }

	public Agent getUtilisateur() {
		return this.utilisateur;
	}

	public void setUtilisateur(Agent utilisateur) {
		this.utilisateur = utilisateur;
	}
    
    
    /*
    public void setLogin(String login) {
    	this.login = login;
    }
    
    public String getLogin() {
    	return this.login;
    }
    */
    	   
}
