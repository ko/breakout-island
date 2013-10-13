package com.relurori.engine.graphics.premade;

import com.relurori.engine.graphics.shapes.Circle;

import android.graphics.Bitmap;

public class Ball extends Circle {

	private boolean visible = false;
	
	public Ball(Bitmap bitmap) {
		super(bitmap);
		getSpeed().setX(5);
		getSpeed().setY(5);
		visible = true;
	}
	
	public boolean getVisible() {
		return visible;
	}
	
	public void setVisible(boolean b) {
		visible = b;
	}

	public String toString() {
		String s = "(x,y)=" + getCenter().getX() + "," + getCenter().getY() + ")";
		s += " r=" + getRadius();
		return s;
	}
}
