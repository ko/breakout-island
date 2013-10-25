package com.relurori.breakout;

import java.io.InputStream;
import java.util.ArrayList;

import org.json.JSONObject;

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
	 */
	public String serialize() {
		bricksToJson();
		paddlesToJson();
		ballsToJson();
		return thisSerialized;
	}
	
	private void ballsToJson() {
		serializeList(PhysicsLocalhost.BALLS);
	}

	private void paddlesToJson() {
		serializeList(PhysicsLocalhost.PADDLES);
	}

	private void bricksToJson() {
		serializeList(PhysicsLocalhost.BRICKS);
	}

	public void fromJson(JSONObject json) {
		
	}
}
