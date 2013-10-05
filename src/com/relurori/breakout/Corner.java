package com.relurori.breakout;

import android.graphics.Bitmap;

public class Corner {
	
	public static float[] getCornerSW(Bitmap bitmap, Graphic.Coordinates coordinates) {
		float llx = coordinates.getX();
		float lly = coordinates.getY() + bitmap.getHeight();
		float[] xy = new float[2];
		
		xy[0] = llx;
		xy[1] = lly;
	
		return xy;
	}

	public static float[] getCornerSE(Bitmap bitmap, Graphic.Coordinates coordinates) {
		float llx = coordinates.getX() + bitmap.getWidth();
		float lly = coordinates.getY() + bitmap.getHeight();
		float[] xy = new float[2];
		
		xy[0] = llx;
		xy[1] = lly;
	
		return xy;
	}
	
	public static float[] getCornerNW(Bitmap bitmap, Graphic.Coordinates coordinates) {
		float llx = coordinates.getX();
		float lly = coordinates.getY();
		float[] xy = new float[2];
		
		xy[0] = llx;
		xy[1] = lly;
	
		return xy;
	}
	
	public static float[] getCornerNE(Bitmap bitmap, Graphic.Coordinates coordinates) {
		float llx = coordinates.getX() + bitmap.getWidth();
		float lly = coordinates.getY();
		float[] xy = new float[2];
		
		xy[0] = llx;
		xy[1] = lly;
	
		return xy;
	}
}
