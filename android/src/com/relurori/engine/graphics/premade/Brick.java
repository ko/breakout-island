package com.relurori.engine.graphics.premade;

import java.util.ArrayList;

import com.relurori.engine.graphics.shapes.Rectangle;
import com.relurori.engine.graphics.shapes.meta.Collision;
import com.relurori.engine.graphics.shapes.meta.Corner;
import com.relurori.engine.graphics.shapes.meta.Intersection;

import android.graphics.Bitmap;

public class Brick extends Rectangle {
	
	private String thisString;
	
	public Brick(Bitmap bitmap) {
		super(bitmap);
		thisString = new String();
	}

	/**
	 * ballHit - returns whether the ball has "hit" this brick.
	 * 
	 * TODO.
	 * 
	 * @param ball
	 * @return
	 */
	public Intersection ballHit(Ball ball) {
		return Collision.between(ball, this);
	}

	private boolean isBetween(float x, float lower, float upper) {
		return (x >= lower && x <= upper);
	}
	
	/**
	 * example: brick,x_nw,y_nw,w,h;
	 */
	public String toString() {
		thisString = "brick" + ",";
		thisString += super.toString();
		thisString += ";";
		return thisString;
	}
}
