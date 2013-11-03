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

	private String[] tempDeserial = new String[1];
	private String tempDeserialObject = new String();
	private int tempDeserialIndex = 0;
	
	private static final int BRICKS = 0;
	private static final int PADDLES = 1;
	private static final int BALLS = 2;

	private static final int DESERIAL_TIMESTAMP = 0;
	private static final int DESERIAL_OBJECT = 1;
	private static final int DESERIAL_INDEX = 2;
	
	private ArrayList<Brick> bricks;
	private ArrayList<Paddle> paddles;
	private ArrayList<Ball> balls;
	
	private ArrayList<Brick> serverBricks;
	private ArrayList<Paddle> serverPaddles;
	private ArrayList<Ball> serverBalls;
	private Brick tempBrick;
	private Paddle tempPaddle;
	private Ball tempBall;
	
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
	 * 		1382939829286,1,brick,93.4375150.0,50,50;
	 * 		1382939829286,2,brick,560.625300.0,50,50;
	 * 		1382939829286,1,paddle,721.575.0,26,150;
	 * 		1382939829286,2,paddle,0.075.0,26,150;
	 * 		1382939829287,1,ball,492.1875,525.0,10.0;
	 * 
	 * @return
	 */
	@Override
	public void deserialize(String s) {
		Log.d(TAG,"deserializing");
		updateStatesFromServer(s);
		serialToObjects();
	}

	private void serialToObjects() {
		for (int i = 0; i < getStatesFromServer().size(); i++) {
			
			tempDeserial = ((String) getStatesFromServer().get(i)).split(",");
			tempDeserialObject = tempDeserial[this.DESERIAL_OBJECT];
			tempDeserialIndex = Integer.getInteger(tempDeserial[this.DESERIAL_INDEX]);
			
			if (tempDeserialObject.equalsIgnoreCase(Brick.class.getSimpleName())) {
				serialToBricks(tempDeserial);
			} else if (tempDeserialObject.equalsIgnoreCase(Paddle.class.getSimpleName())) {
				serialToPaddles(tempDeserial);
			} else if (tempDeserialObject.equalsIgnoreCase(Ball.class.getSimpleName())) {
				serialToBalls(tempDeserial);
			}
		}
	}

	private void serialToBalls(String[] deserial) {
		if (tempDeserialIndex < serverBalls.size()) {
			tempBall = serverBalls.get(tempDeserialIndex);
		} else {
			tempBall = null;
		}
		if (tempBall == null) {
			// TODO add a zero-argument constructor
		}
		tempBall.getCoordinates().setX(Float.valueOf(tempDeserial[Ball.DESERIAL_X]));
		tempBall.getCoordinates().setY(Float.valueOf(tempDeserial[Ball.DESERIAL_Y]));
		
		serverBalls.add(tempDeserialIndex, tempBall);
	}

	private void serialToPaddles(String[] deserial) {
		if (tempDeserialIndex < serverPaddles.size()) {
			tempPaddle = serverPaddles.get(tempDeserialIndex);
		} else {
			tempPaddle = null;
		}
		if (tempPaddle == null) {
			// add a new paddle?!
			// TODO add a zero-argument constructor. thanks.
		}
		tempPaddle.getCoordinates().setX(Float.valueOf(tempDeserial[Paddle.DESERIAL_X]));
		tempPaddle.getCoordinates().setY(Float.valueOf(tempDeserial[Paddle.DESERIAL_Y]));
		
		serverPaddles.add(tempDeserialIndex, tempPaddle);
	}

	private void serialToBricks(String[] deserial) {
		if (tempDeserialIndex < serverBricks.size()) {
			tempBrick = serverBricks.get(tempDeserialIndex);
		} else {
			tempBrick = null;
		}
		if (tempBrick == null) {
			// TODO add a zero-argument constructor
		}
		tempBrick.getCoordinates().setX(Float.valueOf(tempDeserial[Brick.DESERIAL_X]));
		tempBrick.getCoordinates().setY(Float.valueOf(tempDeserial[Brick.DESERIAL_Y]));
		
		serverBricks.add(tempDeserialIndex, tempBrick);
	}
}
