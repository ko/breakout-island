package com.relurori.breakout;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

public class MainPanelActivity extends Activity {

	private static final String TAG = MainPanelActivity.class.getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Intent i = getIntent();
		String key = i.getStringExtra("genericKey");
		
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		
		Log.d(TAG, "Requesting no title");
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		Log.d(TAG, "Setting full screen");
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(new MainPanel(this, MainPanelActivity.this));
	}
}
