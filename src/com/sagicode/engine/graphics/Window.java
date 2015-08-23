package com.sagicode.engine.graphics;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import com.sagicode.engine.GameContainer;
import com.sagicode.engine.util.Log;

public class Window {
	
	private JFrame frame;
	private Canvas canvas;
	private BufferStrategy bs;
	
	public Window(GameContainer gc) {
		canvas = new Canvas();
		Dimension s = new Dimension(gc.getWidth(), gc.getHeight());
		canvas.setPreferredSize(s);
		canvas.setMinimumSize(s);
		canvas.setMaximumSize(s);
		
		frame = new JFrame(gc.getTitle());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(canvas, BorderLayout.CENTER);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		
		canvas.requestFocus();
		
		canvas.createBufferStrategy(2);
		bs = canvas.getBufferStrategy();
		Log.info("Window", "Window Created.");
	}
	
	public Window(GameContainer gc, Applet applet) {
		canvas = new Canvas();
		Dimension s = new Dimension(gc.getWidth(), gc.getHeight());
		canvas.setPreferredSize(s);
		canvas.setMinimumSize(s);
		canvas.setMaximumSize(s);
		
		applet.setLayout(new BorderLayout());
		applet.add(canvas, BorderLayout.CENTER);
		applet.setVisible(true);
		
		canvas.createBufferStrategy(2);
		bs = canvas.getBufferStrategy();
		Log.info("Window", "Applet Created.");
	}
	
	public void cleanUp() {
		bs.dispose();
		frame.dispose();
	}
	
	// Getters
	
	public BufferStrategy getBs() {
		return bs;
	}
	
	public Canvas getCanvas() {
		return canvas;
	}
	
	public JFrame getFrame() {
		return frame;
	}
}
