package com.relurori.engine.graphics.shapes;

import com.relurori.engine.graphics.shapes.generic.Polygon;
import com.relurori.engine.graphics.shapes.meta.Corner;

import android.graphics.Bitmap;

public class Rectangle extends Polygon {

	private String thisString;
	
	public static final int DESERIAL_X = 3;
	public static final int DESERIAL_Y = 4;
	public static final int DESERIAL_WIDTH = 5;
	public static final int DESERIAL_HEIGHT = 6;
	
	public Rectangle(Bitmap bitmap) {
		super(bitmap);
		thisString = new String();
	}

	public Rectangle(Bitmap bitmap, int i, int j) {
		super(bitmap,i,j);
		thisString = new String();
	}
	
	
	public int getWidth() {
		// TODO Auto-generated method stub
		return bitmap.getWidth();
	}
	
	public int getHeight() {
		return bitmap.getHeight();
	}
	
	/**
	 * example: x_nw,y_nw,w,h
	 */
	public String toString() {
		thisString = "";
		thisString += getMeta().getCorners(false).get(Corner.NW).get(Corner.X);
		thisString += getMeta().getCorners(false).get(Corner.NW).get(Corner.Y);
		thisString += ",";
		thisString += getWidth() + ",";
		thisString += getHeight();
		return thisString;
	}
}
