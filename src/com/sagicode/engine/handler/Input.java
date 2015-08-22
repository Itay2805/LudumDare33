package com.sagicode.engine.handler;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import com.sagicode.engine.GameContainer;
import com.sagicode.engine.util.Log;

public class Input implements KeyListener, MouseListener, MouseMotionListener {
		
	private boolean[] keys = new boolean[256];
	private boolean[] keysLast = new boolean[256];
	
	private boolean[] buttons = new boolean[5];
	private boolean[] buttonsLast = new boolean[5];
	
	private int mouseX, mouseY;
	
	public Input(GameContainer gc) {
		gc.getWindow().getCanvas().addKeyListener(this);
		gc.getWindow().getCanvas().addMouseListener(this);
		gc.getWindow().getCanvas().addMouseMotionListener(this);
		Log.info("Input", "Input Handler has started.");
	}
	
	public void update() {
		keysLast = keys.clone();
		buttonsLast = buttons.clone();
	}
	
	// Mouse Motion Listener

	public void mouseDragged(MouseEvent e) {
		mouseX = (int) (e.getX());
		mouseY = (int) (e.getY());
	}

	public void mouseMoved(MouseEvent e) {
		mouseX = (int) (e.getX());
		mouseY = (int) (e.getY());
	}
	
	// Mouse Listener

	public void mouseClicked(MouseEvent e) {}

	public void mouseEntered(MouseEvent e) {}

	public void mouseExited(MouseEvent e) {}

	public void mousePressed(MouseEvent e) {
		buttons[e.getButton()] = true;
	}

	public void mouseReleased(MouseEvent e) {
		buttons[e.getButton()] = false;
	}
	
	// Key Listener

	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	public void keyTyped(KeyEvent e) {}
	
	// Getters
	
	// Keys
	
	public boolean isKey(int keyCode) {
		return keys[keyCode];
	}
	
	public boolean isKeyPressed(int keyCode) {
		return keys[keyCode] && !keysLast[keyCode];
	}
	
	public boolean isKeyReleased(int keyCode) {
		return !keys[keyCode] && keysLast[keyCode];
	}
	
	// Buttons
	
	public boolean isButton(int button) {
		return buttons[button];
	}
	
	public boolean isButtonPressed(int button) {
		return buttons[button] && !buttonsLast[button];
	}
	
	public boolean isButtonReleased(int button) {
		return !buttons[button] && buttonsLast[button];
	}
	
	// Mouse
	
	public int getMouseX() {
		return mouseX;
	}
	
	public int getMouseY() {
		return mouseY;
	}
	
}
