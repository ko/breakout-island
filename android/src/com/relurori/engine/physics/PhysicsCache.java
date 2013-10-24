package com.relurori.engine.physics;

import java.io.InputStream;
import java.util.ArrayList;

import org.json.JSONObject;

import android.util.Log;

import com.relurori.breakout.PhysicsLocalhost;

public class PhysicsCache {

	private final static String TAG = PhysicsCache.class.getSimpleName();

	private static final boolean DEBUG = false;
	
	protected JSONObject jsonStateToSend = null;
	private JSONObject jsonStateToRecv = null;
	
	/**
	 * serverObjects is purgatory for object updates received
	 * over the wire. If it is more recent than what's locally
	 * stored, replace the local copy at objects.get(index) with
	 * whatever's at serverObjects.get(index).
	 * 
	 * The network receive thread will update this.
	 */
	private ArrayList<Object> serverObjects;
	private ArrayList<Long> serverMsCtimes;
	
	/**
	 * objects is The Master List for object states. 
	 * 
	 * The network send thread will be sending from this.
	 */
	private ArrayList<Object> objects;
	private ArrayList<Long> msCtimes;
	
	public PhysicsCache() {
		objects = new ArrayList<Object>();
		msCtimes = new ArrayList<Long>();
		serverObjects = new ArrayList<Object>();
		serverMsCtimes = new ArrayList<Long>();
		
		jsonStateToSend = new JSONObject();
		jsonStateToRecv = new JSONObject();
	}
	
	public ArrayList<?> getLatestListOf(int index) {
		if (index < objects.size()) {
			if (index < serverObjects.size()) {
				if (serverMsCtimes.get(index) > msCtimes.get(index)) {
					objects.set(index, serverObjects.get(index));
				}
			}
			return (ArrayList<?>) objects.get(index);
		} else {
			return null;
		}
	}
	
	public void setObject(int index, Object o) {
		objects.set(index, o);
		msCtimes.set(index, System.currentTimeMillis());
	}

	public ArrayList<Long> getMsCtimes() {
		return msCtimes;
	}

	public void update(InputStream content) {
		
	}

	public void updateStateOf(int index, Object o) {
		if (index < objects.size()) {
			objects.set(index,  o);
			msCtimes.set(index, System.currentTimeMillis());
		} else {
			objects.add(index, o);
			msCtimes.add(index, System.currentTimeMillis());
		}
	}
	
	public JSONObject toJson() {
		return null;
	}
	public void fromJson(JSONObject json) {
	}
	
	public void objectToJson(int index) {
		
	}
}
