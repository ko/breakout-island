package com.relurori.breakout;

import java.util.ArrayList;

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

	private ArrayList<Graphic> graphics = new ArrayList<Graphic>();
	private Graphic currentGraphic = null;

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
				graphic = new Graphic(BitmapFactory.decodeResource(
						getResources(), R.drawable.ic_launcher));
				graphic.getCoordinates().setX(
						(int) event.getX() - graphic.getGraphic().getWidth()
								/ 2);
				graphic.getCoordinates().setY(
						(int) event.getY() - graphic.getGraphic().getHeight()
								* 2);
				currentGraphic = graphic;
			} else if (event.getAction() == MotionEvent.ACTION_MOVE) {
				currentGraphic.getCoordinates().setX(
						(int) event.getX()
								- currentGraphic.getGraphic().getWidth() / 2);
				currentGraphic.getCoordinates().setY(
						(int) event.getY()
								- currentGraphic.getGraphic().getHeight() * 2);
			} else if (event.getAction() == MotionEvent.ACTION_UP) {
				graphics.add(currentGraphic);
				currentGraphic = null;
			}
			return true;
		}
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawColor(Color.BLACK);
		Bitmap bitmap;
		Graphic.Coordinates coords;
		for (Graphic graphic : graphics) {
			bitmap = graphic.getGraphic();
			coords = graphic.getCoordinates();
			canvas.drawBitmap(bitmap, coords.getX(), coords.getY(), null);
		}
		// draw current graphic at last...
		if (currentGraphic != null) {
			bitmap = currentGraphic.getGraphic();
			coords = currentGraphic.getCoordinates();
			canvas.drawBitmap(bitmap, coords.getX(), coords.getY(), null);
		}
	}

	public void updatePhysics() {
		Graphic.Coordinates coord;
		Graphic.Speed speed;
		for (Graphic graphic : graphics) {
			coord = graphic.getCoordinates();
			speed = graphic.getSpeed();

			// Direction
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

			// borders for x...
			if (coord.getX() < 0) {
				speed.toggleXDirection();
				coord.setX(-coord.getX());
			} else if (coord.getX() + graphic.getGraphic().getWidth() > getWidth()) {
				speed.toggleXDirection();
				coord.setX(coord.getX() + getWidth()
						- (coord.getX() + graphic.getGraphic().getWidth()));
			}

			// borders for y...
			if (coord.getY() < 0) {
				speed.toggleYDirection();
				coord.setY(-coord.getY());
			} else if (coord.getY() + graphic.getGraphic().getHeight() > getHeight()) {
				speed.toggleYDirection();
				coord.setY(coord.getY() + getHeight()
						- (coord.getY() + graphic.getGraphic().getHeight()));
			}
		}
	}
}
