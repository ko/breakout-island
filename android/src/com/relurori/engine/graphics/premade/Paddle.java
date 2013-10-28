package com.relurori.engine.graphics.premade;

import com.relurori.engine.graphics.shapes.Rectangle;
import com.relurori.engine.graphics.shapes.meta.Collision;
import com.relurori.engine.graphics.shapes.meta.Corner;
import com.relurori.engine.graphics.shapes.meta.Intersection;

import android.graphics.Bitmap;
import android.util.Log;

public class Paddle extends Rectangle {

	private static final String TAG = Paddle.class.getSimpleName();
	
	private String thisString;
	
	public Paddle(Bitmap bitmap, int maxY, int mvmtPercentage) {
		super(bitmap, 0, maxY);
		thisString = new String();
	}
	
	public Paddle(Bitmap bitmap, int maxY) {
		super(bitmap, 0, maxY);
		thisString = new String();
	}

	public Paddle(Bitmap bitmap) {
		super(bitmap);
		thisString = new String();
	}

	public boolean ballHit(Ball ball) {
		return Collision.between(ball,this).getIntersect();
	}
	
	/**
	 * example: paddle,x_nw,y_nw,w,h;
	 */
	public String toString() {
		thisString = "paddle" + ",";
		thisString += super.toString();
		thisString += ";";
		return thisString;
	}
}
