package com.relurori.engine.physics;

import java.io.InputStream;
import java.util.ArrayList;

import com.relurori.breakout.PhysicsLocalhost;

public class PhysicsCache {

	private final static String TAG = PhysicsCache.class.getSimpleName();
	
	private ArrayList<Object> objects;
	private ArrayList<Long> msCtimes;
	
	public PhysicsCache() {
		objects = new ArrayList<Object>();
		msCtimes = new ArrayList<Long>();
	}
	
	public ArrayList<?> getLatestListOf(int index) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void addObject(Object o) {
		objects.add(o);
		msCtimes.add(System.currentTimeMillis());
	}
	
	public ArrayList<Object> getObjects() {
		return objects;
	}
	
	public ArrayList<Long> getMsCtimes() {
		return msCtimes;
	}

	public void update(InputStream content) {
		
	}

	public void updateStateOf(Object o) {
		
		
	}
}
