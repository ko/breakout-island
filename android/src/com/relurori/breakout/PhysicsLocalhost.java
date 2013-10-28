package com.relurori.breakout;

import java.io.InputStream;
import java.util.ArrayList;

import org.json.JSONObject;

import android.util.Log;

import com.relurori.engine.graphics.generic.Graphic;
import com.relurori.engine.graphics.premade.Ball;
import com.relurori.engine.graphics.premade.Brick;
import com.relurori.engine.graphics.premade.Paddle;
import com.relurori.engine.physics.PhysicsCache;

public class PhysicsLocalhost extends PhysicsCache {

	private final static String TAG = PhysicsLocalhost.class.getSimpleName();

	private static final int BRICKS = 0;
	private static final int PADDLES = 1;
	private static final int BALLS = 2;
	
	private ArrayList<Brick> bricks;
	private ArrayList<Paddle> paddles;
	private ArrayList<Ball> balls;
	
	public PhysicsLocalhost() {
		super();
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Brick> getLatestBricks() {
		bricks = (ArrayList<Brick>) getLatestListOf(PhysicsLocalhost.BRICKS);
		if (bricks == null) {
			bricks = new ArrayList<Brick>();
		}
		return bricks;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Paddle> getLatestPaddles() {
		paddles = (ArrayList<Paddle>) getLatestListOf(PhysicsLocalhost.PADDLES);
		if (paddles == null) {
			paddles = new ArrayList<Paddle>();
		}
		return paddles;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Ball> getLatestBalls() {
		balls = (ArrayList<Ball>) getLatestListOf(PhysicsLocalhost.BALLS);
		if (balls == null) {
			balls = new ArrayList<Ball>();
		}
		return balls;
	}

	public void updateStateOfBricks(ArrayList<Brick> bricks2) {
		bricks = bricks2;
		updateStateOf(PhysicsLocalhost.BRICKS, bricks);
	}

	public void updateStateOfPaddles(ArrayList<Paddle> paddles2) {
		paddles = paddles2;
		updateStateOf(PhysicsLocalhost.PADDLES, paddles);
	}

	public void updateStateOfBalls(ArrayList<Ball> balls2) {
		balls = balls2;
		updateStateOf(PhysicsLocalhost.BALLS, balls);
	}
	
	/**
	 * Serialize objects' elements to JSON.
	 * 
	 * TODO need synchronization for serializedState(s)?
	 */
	public String serialize() {
		initSerializedObjects();
		appendSerializedObject(bricksToJson());
		appendSerializedObject(paddlesToJson());
		appendSerializedObject(ballsToJson());
		return getSerializedObjects();
	}


	private Object ballsToJson() {
		return serializeList(PhysicsLocalhost.BALLS);
	}

	private Object paddlesToJson() {
		return serializeList(PhysicsLocalhost.PADDLES);
	}

	private Object bricksToJson() {
		return serializeList(PhysicsLocalhost.BRICKS);
	}

	/**
	 * deserialize to objects
	 * 
	 * Example parameter:
	 * 
	 * 		1382939829286,brick,93.4375150.0,50,50;
	 * 		1382939829286,brick,560.625300.0,50,50;
	 * 		1382939829286,paddle,721.575.0,26,150;
	 * 		1382939829286,paddle,0.075.0,26,150;
	 * 		1382939829287,ball,492.1875,525.0,10.0;
	 * 
	 * @return
	 */
	public void deserialize(Object o) {
		updateStatesFromServer(o);
		serialToBricks();
		serialToPaddles();
		serialToBalls();
	}

	private void serialToBalls() {

		deserializeList(PhysicsLocalhost.BALLS,
				getListStateFromServer(PhysicsLocalhost.BALLS));
	}

	private void serialToPaddles() {

		deserializeList(PhysicsLocalhost.PADDLES,
				getListStateFromServer(PhysicsLocalhost.PADDLES));
	}

	private void serialToBricks() {

		deserializeList(PhysicsLocalhost.BRICKS,
				getListStateFromServer(PhysicsLocalhost.BRICKS));
	}
}
