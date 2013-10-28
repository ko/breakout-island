package com.relurori.engine.graphics.shapes;

import com.relurori.engine.graphics.generic.Graphic;
import com.relurori.engine.graphics.shapes.generic.Polygon;

import android.graphics.Bitmap;

public class Circle extends Polygon {

	private String thisString;
	
	private Graphic.Coordinates center;
	private float radius;
	
	public Circle(Bitmap bitmap) {
		super(bitmap);
		center = new Graphic.Coordinates();
		radius = bitmap.getWidth()/2;
		
		thisString = new String();
	}
	
	public float getRadius() {
		return radius;
	}

	public Graphic.Coordinates getCenter() {
		center.setX(getCoordinates().getX() + bitmap.getWidth() / 2);
		center.setY(getCoordinates().getY() + bitmap.getHeight() / 2);
		
		return center;
	}
	

	/**
	 * example
	 * 		"x,y,r"
	 */
	public String toString() {
		thisString = "";
		thisString += getCenter().getX() + "," + getCenter().getY() + ",";
		thisString += getRadius();
		return thisString;
	}
}
