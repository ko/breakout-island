package com.relurori.engine.graphics.shapes;

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
	
	public Intersection intersectsWith(Line line) {
		Intersection intersect = new Intersection();
		float s1x,s1y,s2x,s2y;
		float s,t;
		
		intersect.setIntersect(true);
		
		s1x = this.getX2() - this.getX1();
		s1y = this.getY2() - this.getY1();
		s2x = line.getX2() - line.getX1();
		s2y = line.getY2() - line.getY1();
		
		s = (-s1y * (this.getX1() - line.getX1()) + s1x * (this.getY1() - line.getY1()));
		s = s / (-s2x * s1y + s1x * s2y);
		t = (-s2x * (this.getY1() - line.getY1()) - s2y * (this.getX1() - line.getX1()));
		t = t / (-s2x * s1y + s1x * s2y);
		
		if (s >= 0 && s <= 1 && t >= 0 && t <= 1) {
			intersect.setIntersect(true);
			intersect.setX(this.getX1() + (t * s1x));
			intersect.setY(this.getY1() + (t * s1y));
		}
		
		return intersect;
	}
}
