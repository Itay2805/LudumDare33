package com.sagicode.game;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.sagicode.engine.util.Log;

public class Images {
	
	public static BufferedImage player, grass, dirt, portal, spikes, portal_frame, stone_bricks, stone;
	
	public static void init() {
		player = loadImage("/monster.png");
		grass = loadImage("/grass.png");
		dirt = loadImage("/dirt.png");
		portal = loadImage("/portal.png");
		spikes = loadImage("/spikes.png");
		portal_frame = loadImage("/portal_frame.png");
		stone_bricks = loadImage("/wood.png");
		stone = loadImage("/stone.png");
		
	}
	
	public static BufferedImage loadImage(String path) {
		try {
			Log.info("ImageLoader", "Loading image " + path);
			return ImageIO.read(Images.class.getResourceAsStream(path));
		} catch (IOException e) {
			Log.error("ImageLoader", "Could not load image " + path + " -> " + e.getMessage());
			e.printStackTrace();
			System.exit(-1);
		}
		return null;
	}

}
