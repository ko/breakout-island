package com.relurori.engine.network;

import java.io.IOException;
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;

public class NetworkThread extends Thread {

	private final String TAG = NetworkThread.class.getSimpleName();
	
	/** running state */
	protected boolean running;

	/** change running state */
	public void setRunning(boolean running) {
		this.running = running;
	}
}
