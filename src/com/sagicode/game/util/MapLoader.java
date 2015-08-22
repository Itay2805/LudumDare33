package com.sagicode.game.util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import com.sagicode.engine.GameContainer;
import com.sagicode.engine.util.Log;
import com.sagicode.game.states.ScoreState;

public class MapLoader {
	
	private int x, y, w, h, playerX, playerY;
	private int tileSize = 32;
	
	private int[][] map;
	private int level = 0;
	
	private BufferedImage grass, dirt, portal, spikes;
	
	public void init() {
		try {
			grass = ImageIO.read(getClass().getResourceAsStream("/grass.png"));
			Log.info("MapLoader", "grass texture loaded");
			dirt = ImageIO.read(getClass().getResourceAsStream("/dirt.png"));
			Log.info("MapLoader", "dirt texture loaded");
			portal = ImageIO.read(getClass().getResourceAsStream("/portal.png"));
			Log.info("MapLoader", "portal texture loaded");
			spikes = ImageIO.read(getClass().getResourceAsStream("/spikes.png"));
			Log.info("MapLoader", "spikes texture loaded");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
		
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
		if(level == 2) {
			gc.getGame().setState(new ScoreState(level));
		}else {
			load();
		}
	}
		
	public void render(GameContainer gc, Graphics g) {
		for(int x = 0; x < w; x++) {
			for(int y = 0; y < h; y++) {
				int tile = map[x][y];
				if(tile == 1) {
					g.setColor(new Color(0xff0094FF));
					g.fillRect(this.x * tileSize + x * tileSize,this.y * tileSize + y * tileSize, tileSize, tileSize);
				}else if(tile == 0) {
					g.drawImage(grass, this.x * tileSize + x * tileSize, this.y * tileSize + y * tileSize, null);
				}else if(tile == 2) {
					g.drawImage(portal, this.x * tileSize + x * tileSize, this.y * tileSize + y * tileSize, null);
				}else if(tile == 3) {
					g.drawImage(dirt, this.x * tileSize + x * tileSize, this.y * tileSize + y * tileSize, null);
				}else if(tile == 4) {
					g.drawImage(spikes, this.x * tileSize + x * tileSize, this.y * tileSize + y * tileSize, null);
				}
			}
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
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
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
	
}
