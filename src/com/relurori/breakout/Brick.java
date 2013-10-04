package com.relurori.breakout;

import android.graphics.Bitmap;

public class Brick extends Graphic {
	
	private int width = 75;
	private int height = 75;
	
	public Brick(Bitmap bitmap) {
		super(bitmap);

	}
	
	/**
	 * ballHit - returns whether the ball has "hit" this brick.
	 * 
	 * Brick is as follows:
	 * 
	 * 			UL		UR
	 * 			+-------+
	 * 			|		|
	 * 			+-------+
	 * 			LL		LR
	 * 
	 * Look for whether ball has fallen between:
	 * 
	 * 	1.	UL		UL
	 * 		+-------+
	 * 
	 * 	2.	UL
	 * 		+
	 * 		|
	 * 		+
	 * 		UR
	 * 
	 * If both conditions are met, then the ball has effectively 
	 * "hit" the brick.
	 * 
	 * @param ball
	 * @return
	 */
	public boolean ballHit(Ball ball) {
		boolean hit = false;
		boolean hitx = false;
		boolean hity = false;	
		
		int ballx = ball.getCoordinates().getX();
		int bally = ball.getCoordinates().getY();
		
		if (ballHitX(ballx)) {
			hitx = true;
		}
		if (ballHitY(bally)) {
			hity = true;
		}
		if (hitx == true && hity == true)
			hit = true;
		
		return hit;
	}
	
	private float[] getCornerLL() {
		float llx = coordinates.getX() - (width/2);
		float lly = coordinates.getY() - (height/2);
		float[] xy = new float[2];
		
		xy[0] = llx;
		xy[1] = lly;
	
		return xy;
	}

	private float[] getCornerLR() {
		float llx = coordinates.getX() + (width/2);
		float lly = coordinates.getY() - (height/2);
		float[] xy = new float[2];
		
		xy[0] = llx;
		xy[1] = lly;
	
		return xy;
	}
	
	private float[] getCornerUL() {
		float llx = coordinates.getX() - (width/2);
		float lly = coordinates.getY() + (height/2);
		float[] xy = new float[2];
		
		xy[0] = llx;
		xy[1] = lly;
	
		return xy;
	}
	
	private float[] getCornerUR() {
		float llx = coordinates.getX() + (width/2);
		float lly = coordinates.getY() + (height/2);
		float[] xy = new float[2];
		
		xy[0] = llx;
		xy[1] = lly;
	
		return xy;
	}

	private boolean ballHitY(int bally) {
		boolean hit = false;
		float uly = this.getCornerUL()[1];
		float lly = this.getCornerLL()[1];
		
		if (bally >= lly && bally <= uly)
			hit = true;
		
		return hit;
	}

	private boolean ballHitX(int ballx) {
		boolean hit = false;
		float llx = this.getCornerLL()[0];
		float lrx = this.getCornerLR()[0];
		
		if (ballx >= llx && ballx <= lrx)
			hit = true;
		
		return hit;
	}
}
