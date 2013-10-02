package com.relurori.breakout;

import android.graphics.Bitmap;

public class Ball extends Graphic {

	public Ball(Bitmap bitmap) {
		super(bitmap);
		speed.setX(5);
		speed.setY(5);
	}

}
