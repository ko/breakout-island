package com.relurori.breakout;

import android.graphics.Canvas;
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
	
	public SurfaceHolder getSurfaceHolder() {
		return surfaceHolder;
	}
	
	/** game loop */
	@Override
	public void run() {
		Canvas c = null;
		long ticks = 0L;
		Log.d(TAG, "Starting game loop");
		while (running) {
			try {
				c = surfaceHolder.lockCanvas();
				synchronized(surfaceHolder) {
					panel.updatePhysics();
					panel.draw(c);
					
					if (panel.getWinStatus() == true) {
						break;
					}
				}
			} finally {
				if (c != null) {
					surfaceHolder.unlockCanvasAndPost(c);
				}
			}
			ticks++;
		}
		Log.d(TAG, "Game loop ran " + ticks + "times");
	}
}
