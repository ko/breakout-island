package com.relurori.breakout;

import java.io.IOException;
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

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
public class MultiplayerSend extends NetworkThread {
	
	private static final String TAG = MultiplayerSend.class.getSimpleName();
	
	private PhysicsLocalhost physicsCache = null;
	
	public MultiplayerSend(PhysicsLocalhost pCache) {
		this.physicsCache = pCache;
	}
	
	private void sendState() {
		sendSerialized(physicsCache.serialize());
	}
	
	private void sendSerialized(String serialized) {
		// TODO Auto-generated method stub
		
	}

	private boolean stateChanged() {
		return false;
	}
	
	@Override
	public void run() {
		while (running) {
			synchronized(physicsCache) {
				if (stateChanged()) {
					sendState();
				}
			}
		}

		Log.d(TAG, "Multiplayer network thread ran");
	}
}
