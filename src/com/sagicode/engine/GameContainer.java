package com.sagicode.engine;

import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import com.sagicode.engine.graphics.Window;
import com.sagicode.engine.handler.Input;
import com.sagicode.engine.util.Log;

public class GameContainer implements Runnable {
	
	private AbstractGame game;
	private Window window;
	private Input input;
	
	private Thread thread;
	private boolean running = false;
	private double frameRate = 1.0 / 60;
	
	private String title = "Game";
	private int width = 960, height = 720;
	
	public GameContainer(AbstractGame game) {
		this.game = game;
	}
	
	public void start() {
		try {
			Log.setPrintStream(new PrintStream(new File(title + ".log")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		if(running) {
			Log.warning("GameContainer", "Attempting to run the game while running.");
			return;
		}else {
			Log.info("GameContainer", "Running the game engine.");
			window = new Window(this);
			input = new Input(this);
			running = true;
			thread = new Thread(this, "Game");
			Log.info("GameContainer", "Engine has started.");
			thread.run();
		}
	}
	
	public void stop() {
		running = false;
		window.cleanUp();
	}

	public void run() {
		game.init();
		double nextTime = (double) System.nanoTime() / 1000000000.0;
		double maxTimeDiff = 0.5;
		int skippedFrames = 1;
		int maxSkippedFrames = 10;
		while(running) {
			double currTime = (double) System.nanoTime() / 1000000000.0;
			if((currTime - nextTime) > maxTimeDiff) nextTime = currTime;
			if(currTime >= nextTime) {
				nextTime += frameRate;
				game.update(this);
				input.update();
				if((currTime < nextTime) || skippedFrames > maxSkippedFrames) {
					Graphics g = window.getBs().getDrawGraphics();
					game.render(this, g);
					window.getBs().show();
					skippedFrames = 1;
				}else {
					skippedFrames++;
				}
			}else {
				int sleepTime = (int) (1000.0 * (nextTime - currTime));
				if(sleepTime > 0) {
					try {
						Thread.sleep(sleepTime);
					} catch (InterruptedException e) {
						Log.error("GameContainer", e.getMessage());
						e.printStackTrace();
					}
				}
			}
		}
		stop();
	}
	
	///// Getters and Setters
	
	public AbstractGame getGame() {
		return game;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}
	
	public String getTitle() {
		return title;
	}
	
	public GameContainer setFrameRate(double frameRate) {
		this.frameRate = frameRate;
		return this;
	}
	
	public GameContainer setTitle(String title) {
		this.title = title;
		return this;
	}
	
	public GameContainer setWidth(int width) {
		this.width = width;
		return this;
	}
	
	public GameContainer setHeight(int height) {
		this.height = height;
		return this;
	}
	
	public Window getWindow() {
		return window;
	}
	
	public Input getInput() {
		return input;
	}
	
}
