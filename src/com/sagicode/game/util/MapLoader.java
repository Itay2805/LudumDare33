package com.sagicode.game.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.sagicode.engine.GameContainer;
import com.sagicode.engine.util.Log;
import com.sagicode.game.Images;
import com.sagicode.game.states.ScoreState;

public class MapLoader {
	
	private int w, h, playerX, playerY;
	private int tileSize = 32;
	
	private int[][] map;
	private int level = 10;
	
	public void load() {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/level_" + level + ".map")));
			w = Integer.parseInt(in.readLine());
			h = Integer.parseInt(in.readLine());
			playerX = Integer.parseInt(in.readLine());
			playerY = Integer.parseInt(in.readLine());
			map = new int[w][h];
			for(int y = 0; y < h; y++) {
				String line = in.readLine();
				String[] tokens = line.split(" ");
				for(int x = 0; x < w; x++) {
					map[x][y] = Integer.parseInt(tokens[x]);
				}
			}
			in.close();
		}catch (Exception e) {
			Log.error("MapLoader", e.getMessage());
			e.printStackTrace();
		}
		Log.info("MapLoader", "The map level_" + level + ".map has been loaded!");
	}
	
	public int getLevel() {
		return level;
	}
	
	public void nextLevel(GameContainer gc) {
		level++;
		if(level == 10) {
			gc.getGame().setState(new ScoreState(level));
		}else {
			load();
		}
	}
		
	public void render(GameContainer gc, Graphics g) {
		for(int x = 0; x < w; x++) {
			for(int y = 0; y < h; y++) {
				int tile = map[x][y];
				switch(tile) {
					case 0:
						g.setColor(new Color(0xff0094FF));
						g.fillRect(x * tileSize, y * tileSize, tileSize, tileSize);
						break;
					case 1:
						g.drawImage(Images.grass, x * tileSize, y * tileSize, null);
						break;
					case 2:
						g.drawImage(Images.dirt, x * tileSize, y * tileSize, null);
						break;
					case 3:
						g.drawImage(Images.portal, x * tileSize, y * tileSize, null);
						break;
					case 4:
						g.drawImage(Images.portal_frame, x * tileSize, y * tileSize, null);
						break;
					case 5:
						g.drawImage(Images.stone_bricks, x * tileSize, y * tileSize, null);
						break;
					case 6:
						g.drawImage(Images.stone, x * tileSize, y * tileSize, null);
						break;
					case 7:
						g.drawImage(Images.spikes, x * tileSize, y * tileSize, null);
						break;
				}
			}
		}
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial", Font.BOLD, 15));
		g.drawString("Cheat Code: " + Cheat.getLevel(level), 16, 16);
		if(level == 0) {
			g.setColor(Color.WHITE);
			g.setFont(new Font("arial", Font.BOLD, 25));
			int strLen = (int) g.getFontMetrics().getStringBounds("Use A + D To move and SPACE for jumping", g).getWidth();
			g.drawString("Use A + D To move and SPACE for jumping", gc.getWidth() / 2 - strLen / 2, gc.getHeight() / 2);
		}
	}
	
	// Getters And Setters
	
	public int getH() {
		return h;
	}
	
	public int getW() {
		return w;
	}
	
	public int getTileSize() {
		return tileSize;
	}
	
	public int getColTile(int x) {
		return x / tileSize;
	}
	
	public int getRowTile(int y) {
		return y / tileSize;
	}
	
	public int getTile(int x, int y) {
		return map[x][y];
	}
	
	public int getPlayerX() {
		return playerX * tileSize;
	}
	
	public int getPlayerY() {
		return playerY * tileSize;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
}
