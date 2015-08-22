package com.sagicode.game.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import com.sagicode.engine.GameContainer;
import com.sagicode.engine.components.State;
import com.sagicode.engine.sounds.SoundClip;

public class MenuState extends State {
	
	private MenuOption[] options;
	private int index = 0;
	
	private SoundClip select;
		
	public MenuState() {
		select = new SoundClip("/select.wav");
		options = new MenuOption[2];
		options[0] = new MenuOption("Play") {
			public void action(GameContainer gc) {
				gc.getGame().setState(new PlayState(gc));
			}
		};
		options[1] = new MenuOption("About") {
			public void action(GameContainer gc) {
				gc.getGame().setState(new AboutState());
			}
		};
	}

	public void update(GameContainer gc) {
		if(gc.getInput().isKeyPressed(KeyEvent.VK_DOWN)){
			index--;
			select.play();
		}
		if(gc.getInput().isKeyPressed(KeyEvent.VK_UP)) {
			index++;
			select.play();
		}
		if(index > 1) {
			index = 0;
		}
		if(index < 0) {
			index = 1;
		}
		options[index].setSelected(true);
		if(gc.getInput().isKeyPressed(KeyEvent.VK_ENTER)) {
			select.play();
			options[index].action(gc);
		}
	}

	public void render(GameContainer gc, Graphics g) {
		//g.drawImage(bg, 0, 0, null);
		g.setColor(Color.BLACK);
		g.setFont(new Font("arial", Font.BOLD, 30));
		int strLen = (int) g.getFontMetrics().getStringBounds("Menu", g).getWidth();
		g.drawString("Menu", gc.getWidth() / 2 - strLen / 2, 150);
		for(int i = 0; i < options.length; i++) {
			strLen = (int) g.getFontMetrics().getStringBounds(options[i].getTitle(), g).getWidth();
			g.drawString(options[i].getTitle(), gc.getWidth() / 2 - strLen / 2, 300 + i * 80);
			options[i].setSelected(false);
		}
	}

	public void dispose() {
		select.close();
	}
	
	private abstract class MenuOption {
		
		private String title;
		private boolean selected;
		
		public MenuOption(String title) {
			this.title = title;
		}
		
		public String getTitle() {
			if(selected) {
				return "< " + title + " >";
			}else {
				return title;
			}
		}
		
		public void setSelected(boolean selected) {
			this.selected = selected;
		}
		
		public abstract void action(GameContainer gc);
		
	}
	
}
