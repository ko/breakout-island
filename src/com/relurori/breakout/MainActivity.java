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
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

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
		

		setContentView(R.layout.activity_main);
		
		onCreateSetupButton();
	}



	private void onCreateSetupButton() {
		
		Button b = (Button) findViewById(R.id.singleplayer);
		b.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {

				Log.d(TAG, "Setting content view");
				setContentView(new MainPanel(getApplicationContext(), MainActivity.this));

				Log.d(TAG, "Main game panel view added");	
			}
		});
		
		b = (Button) findViewById(R.id.multiplayer);
		b.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(),
						"Multiplayer...yeah...", Toast.LENGTH_LONG).show();
			}
		});
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
