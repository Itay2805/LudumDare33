package com.sagicode.game.gameobjects;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.sagicode.engine.GameContainer;
import com.sagicode.engine.components.GameObject;
import com.sagicode.engine.sounds.SoundClip;
import com.sagicode.engine.util.Log;
import com.sagicode.game.states.ScoreState;
import com.sagicode.game.util.MapLoader;

public class Player extends GameObject {
	
	private double dx, dy;
	
	private final double MOVE_SPEED = 0.5;
	private final double MAX_SPEED = 3;
	private final double MAX_FALLING_SPEED = 12;
	private final double STOP_SPEED = 0.44;
	private final double JUMP_START = -12;
	private final double GRAVITY = 0.64;
	
	private SoundClip jump, dead;
		
	private boolean jumping = false, falling = true;
	
	private MapLoader map;
	
	private BufferedImage player;
		
	public Player(MapLoader map) {
		jump = new SoundClip("/jump.wav");
		dead = new SoundClip("/dead.wav");
		try {
			player = ImageIO.read(getClass().getResourceAsStream("/monster.png"));
			Log.info("Player", "Loaded player image");
		} catch (IOException e1) {
			Log.error("Player", e1.getMessage());
			e1.printStackTrace();
		}
		this.map = map;
		w = 20;
		h = 40;
		x = map.getPlayerX();
		y = map.getPlayerY();
		tag = "player";
	}

	public void update(GameContainer gc) {
		int tile = map.getTile((int)(x / map.getTileSize()), (int)(y / map.getTileSize()));
		switch(tile) {
			case 3:
				map.nextLevel(gc);
				x = map.getPlayerX();
				y = map.getPlayerY();
				dy = 0;
				dx = 0;
				break;
			case 7:
				dead.play();
				x = map.getPlayerX();
				y = map.getPlayerY();
				gc.getGame().setState(new ScoreState(map.getLevel()));
				break;
		}
		if(gc.getInput().isKey(KeyEvent.VK_SPACE) || gc.getInput().isKey(KeyEvent.VK_UP)) {
			jumping = true;
		}else {
			jumping = false;
		}
		if(gc.getInput().isKey(KeyEvent.VK_A) || gc.getInput().isKey(KeyEvent.VK_LEFT)) {
			dx -= MOVE_SPEED;
			if(dx > -MAX_SPEED) {
				dx = -MAX_SPEED;
			}
		}else if(gc.getInput().isKey(KeyEvent.VK_D) || gc.getInput().isKey(KeyEvent.VK_RIGHT)) {
			dx += MOVE_SPEED;
			if(dx < MAX_SPEED) {
				dx = MAX_SPEED;
			}
		}else {
			if(dx > 0) {
				dx -= STOP_SPEED;
				if(dx < 0) {
					dx = 0;
				}
			}else if(dx < 0) {
				dx += STOP_SPEED;
				if(dx > 0) {
					dx = 0;
				}
			}
		}
		if(jumping && !falling) {
			jump.play();
			dy = JUMP_START;
			falling = true;
			jumping = false;
		}
		if(falling) {
			dy += GRAVITY;
			if(dy > MAX_FALLING_SPEED) {
				dy = MAX_FALLING_SPEED;
			}
		}else {
			dy = 0;
		}
		int currCol = map.getColTile((int)x);
		int currRow = map.getRowTile((int)y);
		double tox = (x + dx);
		double toy = (y + dy);
		double tempX = x;
		double tempY = y;
		calulateCorners(x, toy);
		if(dy < 0) {
			if(topLeft || topRight) {
				dy = 0;
				tempY = (currRow * map.getTileSize() + h / 2);
			}else {
				tempY += dy;
			}
		}
		if(dy > 0) {
			if(bottomLeft || bottomRight) {
				dy = 0;
				falling = false;
				tempY = ((currRow + 1) * map.getTileSize() - h / 2);
			}else {
				tempY += dy;
			}
		}
		calulateCorners(tox, y);
		if(dx < 0) {
			if(topLeft || bottomLeft) {
				dx = 0;
				tempX = (currCol * map.getTileSize() + w / 2);
			}else {
				tempX += dx;
			}
		}
		if(dx > 0) {
			if(topRight || bottomRight) {
				dx = 0;
				tempX = ((currCol + 1) * map.getTileSize() - w / 2);
			}else {
				tempX += dx;
			}
		}
		if(!falling) {
			calulateCorners(x, y + 1);
			if((bottomLeft && !bottomRight) || (!bottomLeft && bottomRight)) {
				falling = true;
			}
		}
		x = (int)tempX;
		y = (int)tempY;
	}
	
	private boolean topLeft, topRight, bottomLeft, bottomRight;
	
	private void calulateCorners(double x, double y) {
		int leftTile = map.getColTile((int)(x - w / 2));
		int rightTile = map.getColTile((int)(x + w / 2) - 1);
		int topTile = map.getRowTile((int)(y - h / 2));
		int bottomTile = map.getRowTile((int)(y + h / 2) - 1);
		topLeft = map.getTile(leftTile, topTile) != 0 && map.getTile(leftTile, topTile) != 3 && map.getTile(leftTile, topTile) != 7;
		topRight = map.getTile(rightTile, topTile) != 0 && map.getTile(rightTile, topTile) != 3 && map.getTile(rightTile, topTile) != 7;
		bottomLeft = map.getTile(leftTile, bottomTile) != 0 && map.getTile(leftTile, bottomTile) != 3 && map.getTile(leftTile, bottomTile) != 7;
		bottomRight = map.getTile(rightTile, bottomTile) != 0 && map.getTile(rightTile, bottomTile) != 3 && map.getTile(rightTile, bottomTile) != 7;
	}
	
	private boolean last;

	public void render(GameContainer gc, Graphics g) {
		if(dx > 0) {
			g.drawImage(player, (int) x, (int) (y - 20), null);
			last = true;
		}else if(dx < 0) {
			g.drawImage(player, (int)x, (int) (y - 20), (int) -w, (int) h,  null);
			last = false;
		}else {
			if(last) {
				g.drawImage(player, (int) x, (int) (y - 20), null);
			}else {
				g.drawImage(player, (int) x, (int) (y - 20), (int) -w, (int) h,  null);
			}
		}
	}

	public void dispose() {
		jump.close();
		dead.close();
	}

}
