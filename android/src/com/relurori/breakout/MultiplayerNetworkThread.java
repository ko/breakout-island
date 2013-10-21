package com.relurori.breakout;

import android.util.Log;

import com.relurori.engine.main.MainThread;
import com.relurori.engine.network.NetworkThread;

/**
 * Handles client-server communication and population of the
 * physics cache to be used in the MainPanel.
 * 
 * @author ko
 *
 */
public class MultiplayerNetworkThread extends NetworkThread {
	
	private static final String TAG = MultiplayerNetworkThread.class.getSimpleName();
	
	private PhysicsCache physicsCache = null;
	
	public MultiplayerNetworkThread(PhysicsCache pCache) {
		this.physicsCache = pCache;
	}
	
	
	@Override
	public void run() {
		while (running) {
			
		}

		Log.d(TAG, "Multiplayer network thread ran");
	}
}
