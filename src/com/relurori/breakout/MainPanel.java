package com.relurori.breakout;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MainPanel extends SurfaceView implements SurfaceHolder.Callback {

	private static final String TAG = MainPanel.class.getSimpleName();
	
	private MainThread thread;
	
	public MainPanel(Context context) {
		super(context);
		getHolder().addCallback(this);
		
		// instantiate game loop thread
		thread = new MainThread(getHolder(), this);
		
		setFocusable(true);
	}

	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder arg0) {
		thread.setRunning(true);
		thread.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		boolean retry = true;
		while (retry) {
			try {
				thread.join();
				retry = false;
			} catch (InterruptedException e) {
				// try again
			}
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			if (event.getY() < 50) {
				/* stop game loop if touching the top 50 pixels of the screen */
				thread.setRunning(false);
				((Activity)getContext()).finish();
			} else {
				Log.d(TAG, "Coords(x,y)=(" + event.getX() + "," + event.getY() + ")");
			}
		}
		
		return super.onTouchEvent(event);
	}
	
	@Override
	public void onDraw(Canvas canvas) {
		
	}
}
