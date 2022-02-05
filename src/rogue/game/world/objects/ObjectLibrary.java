package rogue.game.world.objects;

import java.util.HashMap;
import java.util.Map;

import rogue.framework.resources.Resources;

public class ObjectLibrary {
	
	private static Map<Integer,Enhancement> obj = new HashMap<>();
	
	public static void init() {
		obj.put(Resources.SMOKE_SCREEN,new Enhancement(Resources.SMOKE_SCREEN,0,0,0,"Smoke Screen",false,true,3));
	}
	
	public static Enhancement getEnhancement(int id) {
		return obj.get(id);
	}
}
