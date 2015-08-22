package com.sagicode.game.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import com.sagicode.engine.GameContainer;
import com.sagicode.engine.components.State;
import com.sagicode.engine.sounds.SoundClip;

public class AboutState extends State {
	
	private SoundClip select;
	
	public AboutState() {
		select = new SoundClip("/select.wav");
	}

	public void update(GameContainer gc) {
		if (gc.getInput().isKeyPressed(KeyEvent.VK_ENTER)) {
			select.play();
			gc.getGame().setState(new MenuState());
		}
	}

	public void render(GameContainer gc, Graphics g) {
		g.setColor(Color.BLACK);
		g.setFont(new Font("arial", Font.BOLD, 30));
		int strLen = (int) g.getFontMetrics().getStringBounds("This game was made by Itay Almog for ", g).getWidth();
		g.drawString("This game was made by Itay Almog for ", gc.getWidth() / 2 - strLen / 2, 100);
		strLen = (int) g.getFontMetrics().getStringBounds("Ludum Dare #33 under ?? Hours", g).getWidth();
		g.drawString("Ludum Dare #33 under ?? Hours", gc.getWidth() / 2 - strLen / 2, 150);
		g.drawString("< Back to menu >", 20, gc.getHeight() - 80);
	}

	public void dispose() {
		select.close();
	}

}
