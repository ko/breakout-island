package com.relurori.engine.graphics.shapes;

import com.relurori.engine.graphics.Graphic;
import com.relurori.engine.graphics.shapes.generic.Polygon;

import android.graphics.Bitmap;

public class Circle extends Polygon {

	private float radius;
	private float cx;
	private float cy;
	
	public Circle(Bitmap bitmap) {
		super(bitmap);
	
		radius = bitmap.getWidth()/2;
	}
	
	public float getRadius() {
		return radius;
	}

	public Graphic.Coordinates getCenter() {
		Graphic.Coordinates center = new Graphic.Coordinates();
		
		center.setX(getCoordinates().getX() + bitmap.getWidth() / 2);
		center.setY(getCoordinates().getY() + bitmap.getHeight() / 2);
		
		return center;
	}
}
