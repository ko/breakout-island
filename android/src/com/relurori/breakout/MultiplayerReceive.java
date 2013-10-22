package com.relurori.breakout;

import java.io.IOException;
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;

import com.relurori.engine.network.NetworkThread;

public class MultiplayerReceive extends NetworkThread {
	
	private static final String TAG = MultiplayerReceive.class.getSimpleName();
	
	private PhysicsLocalhost physicsCache = null;
	
	public MultiplayerReceive(PhysicsLocalhost physicsCache) {
		// TODO Auto-generated constructor stub
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
				Log.d(TAG, "http: 401");
				break;
			case 200:
				Log.d(TAG, "http: 200");
				physicsCache.update(res.getEntity().getContent());
				break;
			default:
				Log.d(TAG, "http: default");
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (running) {
			synchronized (physicsCache) {

			}
		}

		Log.d(TAG, "Multiplayer network thread ran");
	}
}
