package com.sagicode.game.states;

import java.awt.Graphics;

import com.sagicode.engine.GameContainer;
import com.sagicode.engine.components.State;
import com.sagicode.game.gameobjects.Player;
import com.sagicode.game.util.MapLoader;

public class PlayState extends State {
	
	private MapLoader loader;
	
	public PlayState(GameContainer gc) {
		loader = new MapLoader();
		loader.init();
		loader.load();
		manager.addObject(new Player(loader));
	}

	public void update(GameContainer gc) {
		manager.updateObjects(gc);
	}

	public void render(GameContainer gc, Graphics g) {
		loader.render(gc, g);
		manager.renderObjects(gc, g);
	}

	public void dispose() {
		manager.disposeObjects();
	}

}
