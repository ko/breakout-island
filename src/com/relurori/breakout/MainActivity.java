package com.relurori.breakout;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.util.Log;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {

	private static final String TAG = MainActivity.class.getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		
		Log.d(TAG, "Requesting no title");
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		Log.d(TAG, "Setting full screen");
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		Log.d(TAG, "Setting content view");
		setContentView(new MainPanel(this, MainActivity.this));

		Log.d(TAG, "Main game panel view added");		
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onDestroy() {
		Log.d(TAG, "Destroy");
		super.onDestroy();
	}

	@Override
	protected void onStop() {
		Log.d(TAG, "Stop");
		super.onStop();
	}

	@Override
	protected void onPause() {
		Log.d(TAG, "Pause");
		super.onPause();
	}
}
