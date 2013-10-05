package com.relurori.breakout;

import java.util.ArrayList;

import com.relurori.engine.graphics.shapes.Rectangle;

import android.graphics.Bitmap;

public class Brick extends Rectangle {

	public Brick(Bitmap bitmap) {
		super(bitmap);

	}

	/**
	 * ballHit - returns whether the ball has "hit" this brick.
	 * 
	 * TODO.
	 * 
	 * @param ball
	 * @return
	 */
	public boolean ballHit(Ball ball) {
		boolean hit = false;
		


		return hit;
	}


	private boolean isBetween(float x, float lower, float upper) {
		return (x >= lower && x <= upper);
	}
}
