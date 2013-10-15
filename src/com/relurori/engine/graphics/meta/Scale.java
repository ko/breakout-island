package com.relurori.engine.graphics.meta;

import android.util.Log;

public class Scale {

	private static final String TAG = Scale.class.getSimpleName();
	
	private float xMultiplier;
	private float yMultiplier;
	private float scaledWidth;
	private float scaledHeight;
	
	public Scale(float width, float height) {
		xMultiplier = width / 1280;
		yMultiplier = height / 720;
		
		scaledWidth = xMultiplier * width;
		scaledHeight = yMultiplier * height;
		
		Log.d(TAG,"multpliers=" + xMultiplier + "," + yMultiplier);
	}
	
	public float getScaledY(float y) {
		return yMultiplier * y;
	}
	

	public float getScaledX(float x) {
		return xMultiplier * x;
	}
	
	public float getWidth() {
		return scaledWidth;
	}
	
	public float getHeight() {
		return scaledHeight;
	}
}
