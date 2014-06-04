package com.example.terassiste;

import java.util.concurrent.ExecutionException;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.terassiste.fragments.FragmentConnexion;
import com.example.terassiste.http.AsynJsonHttp;
import com.example.terassiste.views.ViewActionBar;
import com.example.terassiste.views.ViewMainActionBar;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

@TargetApi(Build.VERSION_CODES.KITKAT)
@SuppressLint("NewApi")
public class MainActivity extends FragmentActivity {

	private FragmentManager	_fm;
	private DrawerLayout	_drawerLayout;
	public static final int	FRAGMENT_MAIN			= 0;
	public static final int	FRAGMENT_CONNEXION		= 1;
	public static final int	FRAGMENT_EVT_LIST		= 2;
	public static final int	FRAGMENT_CREATE_EVT		= 3;
	 
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		this._drawerLayout = (DrawerLayout) findViewById(R.id.main_container);

		final ActionBar actionBar = getActionBar();
		View mainActionBar = new ViewMainActionBar(this);
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setDisplayShowCustomEnabled(true);

		actionBar.setCustomView(mainActionBar);

		this._fm = getSupportFragmentManager();
		this.switchFragment(new FragmentConnexion());
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
	
	public void switchActionBar(ViewActionBar actionBar){
		this.getActionBar().setCustomView(actionBar);
	}

	public void closeSlideMenu() {
		this._drawerLayout.closeDrawer(Gravity.LEFT);
		Log.i("LJ", "Close slide menu");
	}

	public void openSlideMenu() {
		this._drawerLayout.openDrawer(Gravity.LEFT);
		Log.i("LJ", "Open slide menu");
	}
	
    public void boutonConnexion(View v, String TAG, String URL) {
    	
    	TextView id = (TextView) findViewById(R.id.textIdentifiant);
    	TextView pass = (TextView) findViewById(R.id.textPass);
    	
    	JSONObject jsonObject= new JSONObject();
    	
		try {
			jsonObject.put("id", id.getText());
            jsonObject.put("pass", pass.getText());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		AsynJsonHttp thread = new AsynJsonHttp(URL);
		thread.execute(jsonObject);
		try {
			JSONObject jsonReturn = thread.get();
			Log.i(TAG, "test: "+jsonReturn.toString());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
    }
    
}
