package com.relurori.engine.graphics.shapes;

import com.relurori.engine.graphics.generic.Graphic;
import com.relurori.engine.graphics.shapes.generic.Polygon;

import android.graphics.Bitmap;

public class Circle extends Polygon {

	private Graphic.Coordinates center;
	
	private float radius;
	private float cx;
	private float cy;
	
	public Circle(Bitmap bitmap) {
		super(bitmap);
		center = new Graphic.Coordinates();
	
		radius = bitmap.getWidth()/2;
	}
	
	public float getRadius() {
		return radius;
	}

	public Graphic.Coordinates getCenter() {
		center.setX(getCoordinates().getX() + bitmap.getWidth() / 2);
		center.setY(getCoordinates().getY() + bitmap.getHeight() / 2);
		
		return center;
	}
}
