package com.relurori.engine.graphics.shapes;

import java.util.ArrayList;

import android.util.Log;

public class Collision {

	private static final String TAG = Collision.class.getSimpleName();
	
	public static final int INVALID_FACE = 0;
	public static final int WEST_FACE = 1;
	public static final int NORTH_FACE = 2;
	public static final int EAST_FACE = 3;
	public static final int SOUTH_FACE = 4;
	public static final int NE_CORNER = 5;
	public static final int SE_CORNER = 6;
	public static final int SW_CORNER = 7;
	public static final int NW_CORNER = 8;
	
	private static boolean DEBUG = true;

	public static Intersection between(Circle circle, Rectangle rect) {
		Intersection intersection = new Intersection();

		float cx = circle.getCenter().getX();
		float cy = circle.getCenter().getY();
		float cr = circle.getRadius();
		
		ArrayList<float[]> corners = rect.getMeta().getCorners(false);
		
		float[] rnw = corners.get(CORNER.NW);
		float[] rne = corners.get(CORNER.NE);
		float[] rsw = corners.get(CORNER.SW);
		
		float rnwx = rnw[0];
		float rnwy = rnw[1];
		float rnex = rne[0];
		float rswy = rsw[1];
		
		if (DEBUG) {
			Log.d(TAG,"cx,cy,cr=" + "(" + cx + "," + cy + "," + cr + ")");
			Log.d(TAG,"rnwx,rnwy=(" + rnwx + "," + rnwy + ")");
			DEBUG=false;
		}
		
		if (rnwx <= cx && cx <= rnex) {
			if (Math.abs(rnwy - cy) <= cr) {
				// above
				Log.d(TAG,"Hit:1");
				intersection.setIntersect(true);
				intersection.setFace(Collision.NORTH_FACE);
			}
			if (Math.abs(rswy - cy) <= cr) {
				// below
				Log.d(TAG,"Hit:2");
				intersection.setIntersect(true);
				intersection.setFace(Collision.SOUTH_FACE);
			}
		}
		
		if (rnwy <= cy && cy <= rswy) {
			if (Math.abs(rnwx - cx) <= cr) {
				// left
				Log.d(TAG,"Hit:3");
				intersection.setIntersect(true);
				intersection.setFace(Collision.WEST_FACE);
			}
			if (Math.abs(rnex - cx) <= cr) {
				// right
				Log.d(TAG,"Hit:4");
				intersection.setIntersect(true);
				intersection.setFace(Collision.EAST_FACE);
			}
		}
		
		return intersection;
	}
}
