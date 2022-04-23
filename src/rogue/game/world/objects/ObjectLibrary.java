package rogue.game.world.objects;

import java.util.HashMap;
import java.util.Map;

import rogue.framework.resources.Resources;
import rogue.game.world.objects.tiles.Enhancement;
import rogue.game.world.objects.tiles.Enhancement.Level;

public class ObjectLibrary {
	
	private static Map<Integer,Enhancement> enh = new HashMap<>();
	
	public static void init() {
		enh.put(Resources.TALLGRASS,new Enhancement(Resources.TALLGRASS,Level.TOP,true,true,false,true,-1));
		enh.put(Resources.TREE,new Enhancement(Resources.TREE,Level.TOP,true,true,true,true,-1));
		enh.put(Resources.SMOKE_SCREEN,new Enhancement(Resources.SMOKE_SCREEN,Level.SUB,true,true,false,true,3));
		enh.put(Resources.HORCRUX,new Enhancement(Resources.HORCRUX,Level.SUB,true,true,false,false,-1));
		enh.put(Resources.WATER,new Enhancement(Resources.WATER,Level.TOP,true,true,true,false,-1));
		enh.put(Resources.RUNE_TRAP,new Enhancement(Resources.RUNE_TRAP,Level.SUB,true,false,false,false,-1));
		enh.put(Resources.ENHANCEMENT_RUNE,new Enhancement(Resources.ENHANCEMENT_RUNE,Level.SUB,true,false,false,false,5));
	}
	
	public static Enhancement getEnhancement(int id) {
		if( enh.get(id)==null) {
			return null;
		}
		Enhancement e = new Enhancement();
		e.setId(id);
		e.setDuration(enh.get(id).getDuration());
		e.setSolid(enh.get(id).isSolid());
		e.setBlockVis(enh.get(id).isBlockVis());
		e.setVisEnemy(enh.get(id).isVisEnemy());
		e.setVisTeam(enh.get(id).isVisTeam());
		return e;
	}
}
