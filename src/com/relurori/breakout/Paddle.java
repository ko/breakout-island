package com.relurori.breakout;

import com.relurori.engine.graphics.shapes.Collision;
import com.relurori.engine.graphics.shapes.Intersection;
import com.relurori.engine.graphics.shapes.Rectangle;

import android.graphics.Bitmap;
import android.util.Log;

public class Paddle extends Rectangle {

	private static final String TAG = Paddle.class.getSimpleName();
	
	public Paddle(Bitmap bitmap, int maxY) {
		super(bitmap, 0, maxY);
		Log.d(TAG, "maxY=" + maxY);
		Log.d(TAG, "Paddle: " + this.coordinates.toString());
	}

	public Paddle(Bitmap bitmap) {
		super(bitmap);
	}
	
	public int getWidth() {
		// TODO Auto-generated method stub
		return bitmap.getWidth();
	}
	
	public int getHeight() {
		return bitmap.getHeight();
	}

	public boolean ballHit(Ball ball) {
		boolean hit = false;
		
		Intersection intersect = Collision.between(ball,this);
		hit = intersect.getIntersect();
		
		Log.d(TAG, "NW=" + getCoordinates().getX() + "," + getCoordinates().getY());
		return hit;
	}

}
