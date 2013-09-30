package com.relurori.breakout;

import android.graphics.Bitmap;

public class Graphic {
	protected Bitmap bitmap;
    protected Coordinates coordinates;
    protected Speed speed;
    
    public Graphic(Bitmap bitmap) {
        this.bitmap = bitmap;
        this.coordinates = new Coordinates();
        this.speed = new Speed();
    }
    
    public Graphic(Bitmap bitmap, int startX, int startY) {
    	this.bitmap = bitmap;
    	this.coordinates = new Coordinates(startX, startY);
    	this.speed = new Speed();
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
        private int x;
        private int y;
 
        public Coordinates() {
        	this.x = 0;
        	this.y = 0;
        }
        
        public Coordinates(int x, int y) {
        	this.x = x;
        	this.y = y;
        }
        
        public int getX() {
            return x + bitmap.getWidth() / 2;
        }
 
        public void setX(int value) {
            x = value - bitmap.getWidth() / 2;
        }
 
        public int getY() {
            return y + bitmap.getHeight() / 2;
        }
 
        public void setY(int value) {
            y = value - bitmap.getHeight() / 2;
        }
 
        public String toString() {
            return "Coordinates: (" + x + "/" + y + ")";
        }
    }
}
