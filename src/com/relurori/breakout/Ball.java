package com.relurori.breakout;

import com.relurori.engine.graphics.shapes.Circle;

import android.graphics.Bitmap;

public class Ball extends Circle {

	public Ball(Bitmap bitmap) {
		super(bitmap);
		getSpeed().setX(5);
		getSpeed().setY(5);
	}

}
