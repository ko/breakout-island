package com.relurori.engine.network;

public class NetworkThread extends Thread {

	/** running state */
	protected boolean running;

	/** change running state */
	public void setRunning(boolean running) {
		this.running = running;
	}
	
}
