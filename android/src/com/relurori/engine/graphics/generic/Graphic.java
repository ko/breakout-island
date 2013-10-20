package com.relurori.engine.graphics.generic;

import java.util.ArrayList;

import com.relurori.engine.graphics.shapes.Line;
import com.relurori.engine.graphics.shapes.meta.Corner;
import com.relurori.engine.math.Slope;


import android.graphics.Bitmap;

public class Graphic {
	protected Bitmap bitmap;
    protected Coordinates coordinates;
    protected Speed speed;
    protected Meta meta;
    
    public Graphic(Bitmap bitmap) {
        this.bitmap = bitmap;
        this.coordinates = new Coordinates();
        this.speed = new Speed();
        this.meta = new Meta();
    }
    
    public Graphic(Bitmap bitmap, int startX, int startY) {
    	this.bitmap = bitmap;
    	this.coordinates = new Coordinates(startX, startY);
    	this.speed = new Speed();
    }
     
    public Meta getMeta() {
    	return meta;
    }
    
    public Speed getSpeed() {
        return speed;
    }
 
    public Bitmap getGraphic() {
        return bitmap;
    }
 
    public Coordinates getCoordinates() {
        return coordinates;
    }

    public class Speed {
        public static final int X_DIRECTION_RIGHT = 1;
        public static final int X_DIRECTION_LEFT = -1;
        public static final int Y_DIRECTION_DOWN = 1;
        public static final int Y_DIRECTION_UP = -1;
     
        private int _x = 1;
        private int _y = 1;
     
        private int _xDirection = X_DIRECTION_RIGHT;
        private int _yDirection = Y_DIRECTION_DOWN;
     
        /**
         * @return the _xDirection
         */
        public int getXDirection() {
            return _xDirection;
        }
     
        /**
         * @param direction the _xDirection to set
         */
        public void setXDirection(int direction) {
            _xDirection = direction;
        }
     
        public void toggleXDirection() {
            if (_xDirection == X_DIRECTION_RIGHT) {
                _xDirection = X_DIRECTION_LEFT;
            } else {
                _xDirection = X_DIRECTION_RIGHT;
            }
        }
     
        /**
         * @return the _yDirection
         */
        public int getYDirection() {
            return _yDirection;
        }
     
        /**
         * @param direction the _yDirection to set
         */
        public void setYDirection(int direction) {
            _yDirection = direction;
        }
     
        public void toggleYDirection() {
            if (_yDirection == Y_DIRECTION_DOWN) {
                _yDirection = Y_DIRECTION_UP;
            } else {
                _yDirection = Y_DIRECTION_DOWN;
            }
        }
     
        /**
         * @return the _x
         */
        public int getX() {
            return _x;
        }
     
        /**
         * @param speed the _x to set
         */
        public void setX(int speed) {
            _x = speed;
        }
     
        /**
         * @return the _y
         */
        public int getY() {
            return _y;
        }
     
        /**
         * @param speed the _y to set
         */
        public void setY(int speed) {
            _y = speed;
        }
     
        public String toString() {
            String xDirection;
            if (_xDirection == X_DIRECTION_RIGHT) {
                xDirection = "right";
            } else {
                xDirection = "left";
            }
            return "Speed: x: " + _x + " | y: " + _y + " | xDirection: " + xDirection;
        }
    }
    
    
    /** Contains the coordinates of the graphic. */
    public class Coordinates {
        private float x;
        private float y;
        
        public Coordinates() {
        	this.x = 0;
        	this.y = 0;
        }
        
        public Coordinates(int x, int y) {
        	setX(x);
        	setY(y);
        }
        
        public float getX() {
            return x + bitmap.getWidth() / 2;
        }
 
        public void setX(float f) {
            x = f - bitmap.getWidth() / 2;
        }
 
        public float getY() {
            return y + bitmap.getHeight() / 2;
        }
 
        public void setY(float value) {
            y = value - bitmap.getHeight() / 2;
        }
 
        public String toString() {
            return "Coordinates: (" + x + "/" + y + ")";
        }
    }
    
    /**
	 * Meta
	 * 
	 * Hold meta information that doesn't necessarily belong in a single class.
	 * 
	 * @author ko
	 * 
	 */
    public class Meta {
    	
    	private ArrayList<ArrayList<Float>> corners = null;
    	private ArrayList<Float> cornerNW = null;
    	private ArrayList<Float> cornerNE = null;
    	private ArrayList<Float> cornerSE = null;
    	private ArrayList<Float> cornerSW = null;
    	private ArrayList<Slope> slopes = null;

    	public Meta() {
    		slopes = new ArrayList<Slope>();
    		corners = new ArrayList<ArrayList<Float>>();
    		cornerNW = new ArrayList<Float>();
    		cornerNE = new ArrayList<Float>();
    		cornerSE = new ArrayList<Float>();
    		cornerSW = new ArrayList<Float>();
    	}
    	
    	public ArrayList<ArrayList<Float>> getCorners() {
	    	if (corners.isEmpty()) {
	    		updateCorners();
	    	}
	    	return corners;
    	}
    	
    	public ArrayList<ArrayList<Float>> getCorners(boolean cached) {
    		if (corners.isEmpty() || cached == false) {
    			updateCorners();
    		}
    		return corners;
    	}
    	/**
         * getCorners - get new/updated corners of object
         * @return	array of float[] for corner coordinates start
         * 			at the top-left and clockwise thereafter.
         */
        private void updateCorners() {
        	synchronized(corners) {
        		updateNWCorner();
        		updateNECorner();
        		updateSECorner();
        		updateSWCorner();
	
				if (corners.isEmpty()) {
					corners.add(Corner.NW, cornerNW);
					corners.add(Corner.NE, cornerNE);
					corners.add(Corner.SE, cornerSE);
					corners.add(Corner.SW, cornerSW);

				} else {
					corners.set(Corner.NW, cornerNW);
					corners.set(Corner.NE, cornerNE);
					corners.set(Corner.SE, cornerSE);
					corners.set(Corner.SW, cornerSW);
				}
			}
        }

		private void updateSWCorner() {
			if (cornerSW.isEmpty()) {
				cornerSW.add(Corner.X, Corner.getSW.X(bitmap, getCoordinates()));
				cornerSW.add(Corner.Y, Corner.getSW.Y(bitmap, getCoordinates()));
			} else {
				cornerSW.set(Corner.X, Corner.getSW.X(bitmap, getCoordinates()));
				cornerSW.set(Corner.Y, Corner.getSW.Y(bitmap, getCoordinates()));
			}
		}

		private void updateSECorner() {
			if (cornerSE.isEmpty()) {
				cornerSE.add(Corner.X, Corner.getSE.X(bitmap, getCoordinates()));
				cornerSE.add(Corner.Y, Corner.getSE.Y(bitmap, getCoordinates()));
			} else {
				cornerSE.set(Corner.X, Corner.getSE.X(bitmap, getCoordinates()));
				cornerSE.set(Corner.Y, Corner.getSE.Y(bitmap, getCoordinates()));
			}
		}

		private void updateNECorner() {
			if (cornerNE.isEmpty()) {
				cornerNE.add(Corner.X, Corner.getNE.X(bitmap, getCoordinates()));
				cornerNE.add(Corner.Y, Corner.getNE.Y(bitmap, getCoordinates()));
			} else {
				cornerNE.set(Corner.X, Corner.getNE.X(bitmap, getCoordinates()));
				cornerNE.set(Corner.Y, Corner.getNE.Y(bitmap, getCoordinates()));
			}
		}

		private void updateNWCorner() {
			if (cornerNW.isEmpty()) {
				cornerNW.add(Corner.X, Corner.getNW.X(bitmap, getCoordinates()));
				cornerNW.add(Corner.Y, Corner.getNW.Y(bitmap, getCoordinates()));
			} else {
				cornerNW.set(Corner.X, Corner.getNW.X(bitmap, getCoordinates()));
				cornerNW.set(Corner.Y, Corner.getNW.Y(bitmap, getCoordinates()));
			}
		}
    }
}
