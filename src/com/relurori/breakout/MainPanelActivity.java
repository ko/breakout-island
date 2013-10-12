package com.relurori.breakout;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.OrientationEventListener;
import android.view.OrientationListener;
import android.view.Window;
import android.view.WindowManager;

public class MainPanelActivity extends Activity {

	private static final String TAG = MainPanelActivity.class.getSimpleName();

	OrientationEventListener listener;
	
	boolean portrait = true;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Intent i = getIntent();
		String key = i.getStringExtra("genericKey");
		
		onCreateSetupListeners();
		
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

		Log.d(TAG, "Requesting no title");
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		Log.d(TAG, "Setting full screen");
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		while (portrait) {
			if (Configuration.ORIENTATION_LANDSCAPE == getResources()
					.getConfiguration().orientation) {
				Log.d(TAG, "now is landscape");
				portrait = false;
				setContentView(new MainPanel(getBaseContext(),
						MainPanelActivity.this));
			}
		}
	}

	private void onCreateSetupListeners() {
		listener = new OrientationEventListener(getBaseContext(),
				SensorManager.SENSOR_DELAY_GAME) {
			public void onOrientationChanged(int orientation) {
				if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
					Log.d(TAG, "changed to landscape");
				}
			}
		};
		listener.enable();
	}
	
}
