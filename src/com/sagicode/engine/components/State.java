package com.sagicode.engine.components;

import java.awt.Graphics;

import com.sagicode.engine.GameContainer;

public abstract class State {
	
	protected ObjectManager manager = new ObjectManager();
	
	public abstract void update(GameContainer gc);
	public abstract void render(GameContainer gc, Graphics g);
	public abstract void dispose();
	
	public ObjectManager getManager() {
		return manager;
	}
	
}
