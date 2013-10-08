package com.relurori.engine.graphics.shapes;

public class Intersection {
	
	private boolean intersect;
	private float x;
	private float y;
	private int face;
	
	public Intersection() {
		intersect = false;
		x = 0;
		y = 0;
	}
	
	public void setIntersect(boolean bool) {
		this.intersect = bool;
	}
	
	public boolean getIntersect() {
		return this.intersect;
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public float getX() {
		return this.x;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
	public float getY() {
		return this.y;
	}
	
	public void setFace(int i) {
		face = i;
	}
	
	public int getFace() {
		return face;
	}
}
