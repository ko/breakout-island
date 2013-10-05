package com.relurori.breakout;

public class Line {
	
	private float x1;
	private float x2;
	private float y1;
	private float y2;
	
	public Line(float[] xy1, float[] xy2) {
		this.x1 = xy1[0];
		this.y1 = xy1[1];
		this.x2 = xy2[0];
		this.y2 = xy2[1];
	}
	
	public float getX1() {
		return x1;
	}
	
	public float getY1() {
		return y1;
	}
	
	public float getX2() {
		return x2;
	}
	
	public float getY2() {
		return y2;
	}
}
