package com.relurori.breakout;

import java.util.ArrayList;

import com.relurori.breakout.Graphic.Coordinates;
import com.relurori.breakout.Graphic.Speed;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MainPanel extends SurfaceView implements SurfaceHolder.Callback {

	private static final String TAG = MainPanel.class.getSimpleName();

	private MainThread thread;

	private ArrayList<Ball> balls = new ArrayList<Ball>();
	private ArrayList<Brick> bricks = new ArrayList<Brick>();
	private Graphic currentGraphic = null;
	private Paddle paddle = null;

	private boolean win = false;
	
	private static float eventDownX = 0;
	private static float eventDownY = 0;
	private static float paddleDownX = 0;

	public MainPanel(Context context) {
		super(context);
		getHolder().addCallback(this);

		// instantiate game loop thread
		thread = new MainThread(getHolder(), this);

		setFocusable(true);
	}

	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void surfaceCreated(SurfaceHolder arg0) {
		thread.setRunning(true);
		thread.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		boolean retry = true;
		while (retry) {
			try {
				thread.join();
				retry = false;
			} catch (InterruptedException e) {
				// try again
			}
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		synchronized (thread.getSurfaceHolder()) {

			Graphic graphic = null;

			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				eventDownX = event.getX();
				eventDownY = event.getY();
				paddleDownX = paddle.getCoordinates().getX();
			} else if (event.getAction() == MotionEvent.ACTION_MOVE) {
				float deltaX = event.getX() - eventDownX;
				float deltaY = event.getY() - eventDownY;
				
				paddle.getCoordinates().setX(
						(int) deltaX + (int)paddleDownX
								+ paddle.getGraphic().getWidth() / 2);
				paddle.getCoordinates().setY(
						getHeight() - paddle.getGraphic().getHeight());
			}

			return true;
		}
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawColor(Color.BLACK);

		drawBall(canvas);
		drawPaddle(canvas);
		drawBricks(canvas);
	}

	public boolean getWinStatus() {
		return win;
	}
	
	private void addFirstBricks() {
		// draw initial blocks
		Bitmap launcher = BitmapFactory.decodeResource(getResources(), 
				R.drawable.ic_launcher);
		Brick brick = new Brick(launcher);
		brick.getCoordinates().setX(150);
		brick.getCoordinates().setY(100);
		bricks.add(brick);
	}
	
	private void drawBricks(Canvas canvas) {
		Bitmap bitmap;
		Graphic.Coordinates coords;
		
		if (bricks.isEmpty()) {
			if (win == false) {
				addFirstBricks();
			}
		}
		
		for (Brick brick : bricks) {
			bitmap = brick.getGraphic();
			coords = brick.getCoordinates();
			canvas.drawBitmap(bitmap, coords.getX(), coords.getY(), null);
		}
	}

	private void drawPaddle(Canvas canvas) {
		Bitmap bitmap;
		Graphic.Coordinates coords;
		int xRight;
		int xLeft;

		/* getHeight() returns 0 in onCreate() */
		if (paddle == null) {
			Bitmap launcher = BitmapFactory.decodeResource(getResources(),
					R.drawable.ic_launcher);
			paddle = new Paddle(launcher);
			paddle.coordinates.setY(getHeight() - launcher.getHeight());
		}

		bitmap = paddle.getGraphic();
		coords = paddle.getCoordinates();
		xLeft = coords.getX();
		xRight = coords.getX() + paddle.getWidth();
		canvas.drawBitmap(bitmap, xLeft, coords.getY(), null);
		canvas.drawBitmap(bitmap, xRight, coords.getY(), null);
	}

	private void drawBall(Canvas canvas) {
		Bitmap bitmap;
		Graphic.Coordinates coords;

		if (balls.isEmpty()) {
			Bitmap launcher = BitmapFactory.decodeResource(getResources(),
					R.drawable.ic_launcher);
			Ball ball = new Ball(launcher);
			ball.coordinates.setX(500);
			ball.coordinates.setY(500);
			balls.add(ball);
		}
		for (Ball ball : balls) {
			bitmap = ball.getGraphic();
			coords = ball.getCoordinates();
			canvas.drawBitmap(bitmap, coords.getX(), coords.getY(), null);
		}

	}

	public void updatePhysics() {

		for (Ball ball : balls) {
			updateBallPhysics(ball);
		}
		if (paddle != null)
			updatePaddlePhysics();
	}

	private void updatePaddlePhysics() {
		Graphic.Coordinates coord = paddle.getCoordinates();

		if (coord.getX() < 0)
			paddle.getCoordinates().setX(0);
		else if ((coord.getX() + paddle.getWidth()) > getWidth())
			paddle.getCoordinates().setX(getWidth() - paddle.getWidth());
	}

	private void updateBallPhysics(Ball ball) {
		Graphic.Coordinates coord = ball.getCoordinates();
		Graphic.Speed speed = ball.getSpeed();

		// Direction
		coord = updateBallPhysicsDirections(coord, speed);

		// Brick hit
		ballHitBricks(ball);
		
		// borders for x...
		if (coord.getX() < 0) {
			speed.toggleXDirection();
			coord.setX(-coord.getX());
		} else if (coord.getX() + ball.getGraphic().getWidth() > getWidth()) {
			speed.toggleXDirection();
			coord.setX(coord.getX() + getWidth()
					- (coord.getX() + ball.getGraphic().getWidth()));
		}

		// borders for y...
		if (coord.getY() < 0) {
			speed.toggleYDirection();
			coord.setY(-coord.getY());
		} else if (coord.getY() + ball.getGraphic().getHeight() > getHeight()) {
			if (ballHitsPaddle(coord.getX())) {
				speed.toggleYDirection();
				coord.setY(coord.getY() + getHeight()
						- (coord.getY() + ball.getGraphic().getHeight()));
			} else {
				balls.remove(ball);
			}
		}
	}

	private void ballHitBricks(Ball ball) {
		for (Brick brick : bricks) {
			if (ballHitBrick(ball, brick)) {
				bricks.remove(brick);
				if (bricks.isEmpty() == true) {
					win = true;
				}
			}
		}
	}

	private boolean ballHitBrick(Ball ball, Brick brick) {
		
		return brick.ballHit(ball);
	}

	private Coordinates updateBallPhysicsDirections(Coordinates coord,
			Speed speed) {
		
		if (speed.getXDirection() == Graphic.Speed.X_DIRECTION_RIGHT) {
			coord.setX(coord.getX() + speed.getX());
		} else {
			coord.setX(coord.getX() - speed.getX());
		}
		if (speed.getYDirection() == Graphic.Speed.Y_DIRECTION_DOWN) {
			coord.setY(coord.getY() + speed.getY());
		} else {
			coord.setY(coord.getY() - speed.getY());
		}
		return coord;
	}

	private boolean ballHitsPaddle(int x) {
		boolean found = false;
		
		int x2 = paddle.getCoordinates().getX();
		int i = paddle.getWidth();
		
		if (x < (x2 + i) && x > x2)
			found = true;
		else
			found = false;
		
		return found;
	}
}
