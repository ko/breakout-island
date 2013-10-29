package com.relurori.engine.physics;

import java.io.InputStream;
import java.util.ArrayList;

import org.json.JSONObject;

import android.util.Log;

import com.relurori.breakout.PhysicsLocalhost;

public class PhysicsCache {

	private final static String TAG = PhysicsCache.class.getSimpleName();

	private static final boolean DEBUG = false;
	
	private String serializedState = null;
	private ArrayList<Object> statesFromServer = null;
	private ArrayList<Object> serializedStates;

	
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

		serializedState = new String();
		statesFromServer = new ArrayList<Object>();
		serializedStates = new ArrayList<Object>();
	}

	public ArrayList<Object> getStatesFromServer() {
		return statesFromServer;
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
	
	public String serialize() {
		Log.d(TAG,"serialze unimplemented");
		return null;
	}
	
	public void deserialize(String serialized) {
		Log.d(TAG,"deserialze unimplemented");
	}
	
	public Object serializeObject(int index) {
		return objects.get(index).toString();
	}
	
	private void initSerializeList(int index) {
		if (index < serializedStates.size()) {
			serializedStates.set(index, "");
		} else {
			serializedStates.add(index, "");
		}
	}
	
	@SuppressWarnings("unchecked")
	public Object serializeList(int index) {
		initSerializeList(index);
		for (int i = 0; i < ((ArrayList<Object>)(objects.get(index))).size(); i++) {
			serializedStates.set(index, serializedStates.get(index)
					+ String.valueOf(msCtimes.get(index)) + ","
					+ String.valueOf(i) + ","
					+ ((ArrayList<Object>) (objects.get(index))).get(i)
							.toString());
			serializedStates.set(index, serializedStates.get(index));
		}
		return serializedStates.get(index);
	}
	
	protected void initSerializedObjects() {
		serializedState = "";
	}
	
	protected String getSerializedObjects() {
		return serializedState;
	}
	
	protected void appendSerializedObject(Object serialObject) {
		serializedState += serialObject;
	}
	
	/**
	 * Convert this:
	 * 
	 * 		1382939829286,brick,93.4375150.0,50,50;
	 * 		1382939829286,brick,560.625300.0,50,50;
	 * 		1382939829286,paddle,721.575.0,26,150;
	 * 		1382939829286,paddle,0.075.0,26,150;
	 * 		1382939829287,ball,492.1875,525.0,10.0;
	 * 
	 * to an arraylist of strings		
	 * 
	 * @param asset
	 */
	public void updateStatesFromServer(Object asset) {
		deserializeToStrings(asset);
	}
	
	private void deserializeToStrings(Object asset) {
		for (int i = 0; i < ((String) asset).split(";").length; i++) {
			statesFromServer.add(i, ((String) asset).split(";")[i]);
		}
	}
	
	public void deserializeList(int index, ArrayList<Object> array) {
		synchronized(objects.get(index)) {
			updateObjectsWithServer(index, array);
		}
	}

	private void updateObjectsWithServer(int index, ArrayList<Object> array) {
		if (serverIsNewer(index, array)) {
			synchronized (objects) {
				objects.remove(index);
				objects.add(index, statesFromServer.get(index));
			}
		}
	}

	/**
	 * compare timestamps between local "objects.get(index)" and
	 * whatever "array" was passed in from caller.
	 * 
	 * @param index
	 * @param array
	 * @return
	 */
	private boolean serverIsNewer(int index, ArrayList<Object> array) {
		
		return false;
	}
	
	/**
	 * after going String to String[] to ArrayList, find the 
	 * actual string to 
	 * @param index
	 * @return
	 */
	public ArrayList<Object> getListStateFromServer(int index) {
		return (ArrayList<Object>) statesFromServer.get(index);
	}
	

	




}
