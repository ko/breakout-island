package com.relurori.breakout;

import java.io.IOException;
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

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
	
	// TODO UDP this with our own protocol
	private void fetchUpdate() {
		HttpResponse res;
		DefaultHttpClient client;
		HttpGet get;
		String url;
		
		url = "http://yaksok.net";
		res = null;
		client = new DefaultHttpClient();
		get = new HttpGet(URI.create(url));
		try {
			res = client.execute(get);
			switch (res.getStatusLine().getStatusCode()) {
			case 401:
				Log.d(TAG,"http: 401");
				break;
			case 200:
				Log.d(TAG,"http: 200");
				physicsCache.update(res.getEntity().getContent());
				break;
			default:
				Log.d(TAG,"http: default");
			}
		}  catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		while (running) {
			synchronized(physicsCache) {
				
			}
		}

		Log.d(TAG, "Multiplayer network thread ran");
	}
}
