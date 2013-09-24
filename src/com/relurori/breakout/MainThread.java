package com.relurori.breakout;

import android.util.Log;
import android.view.SurfaceHolder;

public class MainThread extends Thread {

	private static final String TAG = MainThread.class.getSimpleName();
	/** running state */
	private boolean running;
	
	/** surface holder for the main game panel is needed because we
	 * need to lock the surface when we draw*/
	private SurfaceHolder surfaceHolder;
	
	/** main game panel */
	private MainPanel panel;
	
	public MainThread(SurfaceHolder surfaceHolder, MainPanel panel) {
		super();
		this.surfaceHolder= surfaceHolder;  
		this.panel = panel;
	}
	
	/** change running state */
	public void setRunning(boolean running) {
		this.running = running;
	}
	
	/** game loop */
	@Override
	public void run() {
		long ticks = 0L;
		Log.d(TAG, "Starting game loop");
		while (running) {
			ticks++;
			
			//updateGameState();
			//renderGameState();
		}
		Log.d(TAG, "Game loop ran " + ticks + "times");
	}
}
