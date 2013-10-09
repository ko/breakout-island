package com.relurori.engine.math;

public class Slope {

	public static final int NORTH = 0;
	public static final int EAST = 1;
	public static final int SOUTH = 2;
	public static final int WEST = 3;
	
	private boolean isVertical;
	private float rise;
	private float run;
	private float slope;

	public Slope(float[] xy1, float[] xy2) {
		rise = xy2[1] - xy1[1];
		run = xy2[0] - xy1[0];

		if (run == 0) {
			isVertical = true;
			slope = 0; // whatever
		} else {
			slope = rise / run;
		}
	}

	public boolean isVertical() {
		return isVertical;
	}

	public float getSlope() {
		return slope;
	}
}
