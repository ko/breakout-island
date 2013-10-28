package com.relurori.engine.graphics.premade;

import com.relurori.engine.graphics.shapes.Circle;

import android.graphics.Bitmap;

public class Ball extends Circle {

	private String thisString;
	private boolean visible = false;
	
	public Ball(Bitmap bitmap) {
		super(bitmap);
		getSpeed().setX(5);
		getSpeed().setY(5);
		visible = true;
		thisString = new String();
	}
	
	public boolean getVisible() {
		return visible;
	}
	
	public void setVisible(boolean b) {
		visible = b;
	}
	
	/**
	 * example: ball,x,y,r;
	 */
	public String toString() {
		thisString = "ball" + ",";
		thisString += super.toString();
		thisString += ";";
		return thisString;
	}
}
