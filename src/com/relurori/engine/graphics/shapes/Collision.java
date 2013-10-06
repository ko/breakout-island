package com.relurori.engine.graphics.shapes;

import java.util.ArrayList;

import android.util.Log;

public class Collision {

	private static final String TAG = Collision.class.getSimpleName();
	
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
			}
			if (Math.abs(rswy - cy) <= cr) {
				// below
				Log.d(TAG,"Hit:2");
				intersection.setIntersect(true);
			}
		}
		
		if (rnwy <= cy && cy <= rswy) {
			if (Math.abs(rnwx - cx) <= cr) {
				// left
				Log.d(TAG,"Hit:3");
				intersection.setIntersect(true);
			}
			if (Math.abs(rnex - cx) <= cr) {
				// right
				Log.d(TAG,"Hit:4");
				intersection.setIntersect(true);
			}
		}
		
		return intersection;
	}
}
