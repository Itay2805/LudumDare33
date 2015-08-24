package com.sagicode.game.util;

import java.util.ArrayList;

public class Cheat {
	
	public static ArrayList<String> cheats = new ArrayList<String>();
	
	public static void init() {
		cheats.add("Unu"); // Level 0 (1)
		cheats.add("IAh"); // Level 1 (2)
		cheats.add("Arl"); // Level 2 (3)
		cheats.add("6QY"); // Level 3 (4)
		cheats.add("mQd"); // Level 4 (5)
		cheats.add("84K"); // Level 5 (6)
		cheats.add("Kwo"); // Level 6 (7)
		cheats.add("Xg1"); // Level 7 (8)
		cheats.add("gfF"); // Level 8 (9)
		cheats.add("DUJ"); // Level 9 (10)
		cheats.add("GhB"); // Level 10 (11)
	}
	
	public static String getLevel(int level) {
		return cheats.get(level);
	}
	
	public static int getLevel(String cheat) {
		for(int i = 0; i < cheats.size(); i++) {
			if(cheats.get(i).equals(cheat)) {
				return i;
			}
		}
		return 0;
	}
	
}
