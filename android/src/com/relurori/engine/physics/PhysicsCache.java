package com.relurori.engine.physics;

import java.io.InputStream;
import java.util.ArrayList;

import android.util.Log;

import com.relurori.breakout.PhysicsLocalhost;

public class PhysicsCache {

	private final static String TAG = PhysicsCache.class.getSimpleName();

	private static final boolean DEBUG = false;
	
	private ArrayList<Object> objects;
	private ArrayList<Long> msCtimes;
	
	public PhysicsCache() {
		objects = new ArrayList<Object>();
		msCtimes = new ArrayList<Long>();
	}
	
	public ArrayList<?> getLatestListOf(int index) {
		if (index < objects.size()) {
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
}
