package com.relurori.engine.graphics.shapes.meta;

import java.util.ArrayList;

import com.relurori.engine.graphics.shapes.Circle;
import com.relurori.engine.graphics.shapes.Rectangle;

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
	
	private static boolean DEBUG = false;

	public static Intersection between(Circle circle, Rectangle rect) {
		Intersection intersection = new Intersection();

		float cx = circle.getCenter().getX();
		float cy = circle.getCenter().getY();
		float cr = circle.getRadius();
		
		ArrayList<ArrayList<Float>> corners = rect.getMeta().getCorners(false);
		
		ArrayList<Float> rnw = corners.get(Corner.NW);
		ArrayList<Float> rne = corners.get(Corner.NE);
		ArrayList<Float> rsw = corners.get(Corner.SW);
		
		if (DEBUG) {
			Log.d(TAG,"cx,cy,cr=" + "(" + cx + "," + cy + "," + cr + ")");
			Log.d(TAG,"rnwx,rnwy=(" + rnw.get(Corner.X) + "," + rnw.get(Corner.Y) + ")");
			DEBUG=false;
		}
		
		if (rnw.get(Corner.X) <= cx && cx <= rne.get(Corner.X)) {
			if (Math.abs(rnw.get(Corner.Y) - cy) <= cr) {
				// above
				Log.d(TAG,"Hit:X1");
				intersection.setIntersect(true);
				intersection.setFace(Collision.NORTH_FACE);
			}
			if (Math.abs(rsw.get(Corner.Y) - cy) <= cr) {
				// below
				Log.d(TAG,"Hit:X2");
				intersection.setIntersect(true);
				intersection.setFace(Collision.SOUTH_FACE);
			}
		}
		
		if (rnw.get(Corner.Y) <= cy && cy <= rsw.get(Corner.Y)) {
			if (Math.abs(rnw.get(Corner.X) - cx) <= cr) {
				// left
				Log.d(TAG,"Hit:Y1");
				intersection.setIntersect(true);
				intersection.setFace(Collision.WEST_FACE);
			}
			if (Math.abs(rne.get(Corner.X) - cx) <= cr) {
				// right
				Log.d(TAG,"Hit:Y2");
				intersection.setIntersect(true);
				intersection.setFace(Collision.EAST_FACE);
			}
		}
		
		return intersection;
	}
}
