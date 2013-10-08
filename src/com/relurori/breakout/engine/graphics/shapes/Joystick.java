package com.relurori.breakout.engine.graphics.shapes;

import android.graphics.Bitmap;

public class Joystick extends InputSystem {

	public static final int MOVEMENT_STOP = 0;
	public static final int MOVEMENT_EAST = 1;
	public static final int MOVEMENT_WEST = 2;
	public static final int MOVEMENT_SOUTH = 3;
	public static final int MOVEMENT_NORTH = 4;
	
	private float restingX;
	private float restingY;
	
	private boolean resting = true;
	
	public Joystick(Bitmap bitmap, float x, float y) {
		super(bitmap);
		restingX = x;
		restingY = y;
	}

	public void setRestingX(float x) {
		restingX = x;
	}
	
	public float getRestingX() {
		return restingX;
	}
	
	public void setRestingY(float y) {
		restingY = y;
	}
	
	public float getRestingY() {
		return restingY;
	}
	
	public boolean pressed(float x, float y) {
		boolean touch = false;
		
		if (getCoordinates().getX() <= x) {
			if ((getCoordinates().getX() + getGraphic().getWidth()) >= x) {
				if (getCoordinates().getY() <= y) {
					if ((getCoordinates().getY() + getGraphic().getHeight()) >= y) {
						touch = true;
					}
				}
			}
		}
		
		return touch;
	}

	public void setToRestingState() {
		getCoordinates().setX(getRestingX());
		getCoordinates().setY(getRestingY());
		resting = true;
	}

	public void setRestingState(boolean b) {
		resting = b;
	}
	
	public boolean getRestingState() {
		return resting;
	}
	
	public float getDeltaX() {
		return getCoordinates().getX() - restingX;
	}
	
	public float getDeltaY() {
		return getCoordinates().getY() - restingY;
	}
	
	public int getDirection() {
		if (getDeltaX() > 0)
			return MOVEMENT_EAST;
		if (getDeltaX() < 0)
			return MOVEMENT_WEST;
		if (getDeltaY() < 0) 
			return MOVEMENT_NORTH;
		if (getDeltaY() > 0)
			return MOVEMENT_SOUTH;
		return MOVEMENT_STOP;
	}
	
	public String toString() {
		String s = "mvmnt=" + getDirection();
		return s;
	}
}
