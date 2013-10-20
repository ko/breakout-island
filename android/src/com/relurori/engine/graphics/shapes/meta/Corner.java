package com.relurori.engine.graphics.shapes.meta;


import com.relurori.engine.graphics.generic.Graphic;

import android.graphics.Bitmap;

public class Corner {
	
    public static final int NW = 0;
    public static final int NE = 1;
    public static final int SE = 2;
    public static final int SW = 3;
    
    public static final int X = 0;
    public static final int Y = 1;
    
    public static class getSW {
    	public static float X(Bitmap bitmap, Graphic.Coordinates coordinates) {
    		return coordinates.getX();
    	}
    	
    	public static float Y(Bitmap bitmap, Graphic.Coordinates coordinates) {
    		return coordinates.getY() + bitmap.getHeight();
    	}
    }

    public static class getSE {
    	public static float X(Bitmap bitmap, Graphic.Coordinates coordinates) {
    		return coordinates.getX() + bitmap.getWidth();
    	}
    	
    	public static float Y(Bitmap bitmap, Graphic.Coordinates coordinates) {
    		return coordinates.getY() + bitmap.getHeight();
    	}
    }

    public static class getNW {
    	public static float X(Bitmap bitmap, Graphic.Coordinates coordinates) {
    		return coordinates.getX();
    	}
    	
    	public static float Y(Bitmap bitmap, Graphic.Coordinates coordinates) {
    		return coordinates.getY();
    	}
    }

    public static class getNE {
    	public static float X(Bitmap bitmap, Graphic.Coordinates coordinates) {
    		return coordinates.getX() + bitmap.getWidth();
    	}
    	
    	public static float Y(Bitmap bitmap, Graphic.Coordinates coordinates) {
    		return coordinates.getY();
    	}
	}
}
