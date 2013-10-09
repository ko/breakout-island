package com.relurori.engine.graphics.shapes.meta;


import com.relurori.engine.graphics.Graphic;

import android.graphics.Bitmap;

public class Corner {
	
    public static final int NW = 0;
    public static final int NE = 1;
    public static final int SE = 2;
    public static final int SW = 3;
    
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
