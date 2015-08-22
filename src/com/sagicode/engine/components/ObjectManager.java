package com.sagicode.engine.components;

import java.awt.Graphics;
import java.util.ArrayList;

import com.sagicode.engine.GameContainer;
import com.sagicode.engine.util.Log;

public class ObjectManager {
	
	private ArrayList<GameObject> objects = new ArrayList<GameObject>();
	
	public void updateObjects(GameContainer gc) {
		for(int i = 0; i < objects.size(); i++) {
			objects.get(i).update(gc);
			if(objects.get(i).isDead()) {
				objects.remove(i);
			}
		}
	}
	
	public void renderObjects(GameContainer gc, Graphics g) {
		for(int i = 0; i < objects.size(); i++) {
			objects.get(i).render(gc, g);
		}
	}
	
	public void disposeObjects() {
		for(GameObject go : objects) {
			go.dispose();
		}
	}
	
	public void addObject(GameObject object) {
		String name = object.getClass().getName().split("\\.")[object.getClass().getName().split("\\.").length - 1];
		Log.info("ObjectManager", "Added new object " + name + " (tag: " + object.getTag() + ")");
		objects.add(object);
	}
	
	public GameObject findObject(String tag) {
		for(GameObject go : objects) {
			if(go.getTag().equalsIgnoreCase(tag)) return go;
		}
		return null;
	}
	
}
