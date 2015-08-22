package com.sagicode.game;

import java.awt.Color;
import java.awt.Graphics;

import com.sagicode.engine.AbstractGame;
import com.sagicode.engine.GameContainer;
import com.sagicode.game.states.MenuState;

public class Game extends AbstractGame {
	
	public Game() {
		push(new MenuState());
	}

	public void init() {
		
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
		GameContainer gc = new GameContainer(new Game());
		gc.setHeight(15 * 32).setWidth(640);
		gc.start();
	}

}
