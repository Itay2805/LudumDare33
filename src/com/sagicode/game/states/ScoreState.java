package com.sagicode.game.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import com.sagicode.engine.GameContainer;
import com.sagicode.engine.components.State;
import com.sagicode.engine.sounds.SoundClip;

public class ScoreState extends State {
	
	private int score;

	private SoundClip select;
	
	public ScoreState(int score) {
		select = new SoundClip("/select.wav");
		this.score = score;
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
		if(score == 2) {
			int strLen = (int) g.getFontMetrics().getStringBounds("You won!", g).getWidth();
			g.drawString("You won!", gc.getWidth() / 2 - strLen / 2, 100);
		}else {
			int strLen = (int) g.getFontMetrics().getStringBounds("You lost!", g).getWidth();
			g.drawString("You lost!", gc.getWidth() / 2 - strLen / 2, 100);
		}
		int strLen = (int) g.getFontMetrics().getStringBounds("Score: " + score, g).getWidth();
		g.drawString("Score: " + score, gc.getWidth() / 2 - strLen / 2, 200);
		g.drawString("< Back to menu >", 20, gc.getHeight() - 80);
	}

	public void dispose() {
		select.close();
	}

}
