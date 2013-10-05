package com.relurori.breakout;

import android.graphics.Bitmap;
import android.util.Log;

public class Paddle extends Graphic {

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

}
