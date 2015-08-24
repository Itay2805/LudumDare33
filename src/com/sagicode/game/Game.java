package com.sagicode.game;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import com.sagicode.engine.AbstractGame;
import com.sagicode.engine.GameContainer;
import com.sagicode.engine.util.Log;
import com.sagicode.game.states.MenuState;
import com.sagicode.game.util.Cheat;

public class Game extends AbstractGame {
	
	public Game() {
		Cheat.init();
		push(new MenuState());
	}

	public void init() {
		Images.init();
	}

	public void update(GameContainer gc) {
		peek().update(gc);
	}

	public void render(GameContainer gc, Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, gc.getWidth(), gc.getHeight());
		peek().render(gc, g);
	}
	
	public static void main(String[] args) {
		try {
			Log.setPrintStream(new PrintStream(new File("game.log")));
		} catch (FileNotFoundException e) {
			Log.error("GameContainer", "Could not create log file.");
			e.printStackTrace();
		}
		GameContainer gc = new GameContainer(new Game());
		gc.setHeight(480).setWidth(640);
		gc.setTitle("Monster Jumper");
		gc.start();
	}

}
