package com.sagicode.engine.components;

import java.awt.Graphics;

import com.sagicode.engine.GameContainer;

public abstract class GameObject {
	
	protected float x, y, w, h;
	protected String tag = "null";
	protected boolean dead = false;
	
	public abstract void update(GameContainer gc);
	public abstract void render(GameContainer gc, Graphics g);
	public abstract void dispose();
	
	public void setDead(boolean dead) {
		this.dead = dead;
	}
	
	public boolean isDead() {
		return dead;
	}
	
	public String getTag() {
		return tag;
	}
	
	public float getH() {
		return h;
	}
	
	public float getW() {
		return w;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
}
