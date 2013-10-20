package com.relurori.breakout;

import java.util.ArrayList;

import com.relurori.engine.graphics.generic.Graphic;

public class PhysicsCache {

	private final static String TAG = PhysicsCache.class.getSimpleName();
	
	private ArrayList<Object> objects;
	private ArrayList<Long> msCtimes;
	
	public PhysicsCache() {
		objects = new ArrayList<Object>();
		msCtimes = new ArrayList<Long>();
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
}