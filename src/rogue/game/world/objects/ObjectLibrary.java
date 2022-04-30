package rogue.game.world.objects;

import java.util.HashMap;
import java.util.Map;

import rogue.framework.resources.Resources;
import rogue.game.world.objects.tiles.Enhancement;
import rogue.game.world.objects.tiles.individualenhancements.EnhancementRune;
import rogue.game.world.objects.tiles.individualenhancements.Frosted;
import rogue.game.world.objects.tiles.individualenhancements.RuneTrap;
import rogue.game.world.objects.tiles.individualenhancements.SmokeScreen;
import rogue.game.world.objects.tiles.individualenhancements.TallGrass;
import rogue.game.world.objects.tiles.individualenhancements.Tree;
import rogue.game.world.objects.tiles.individualenhancements.Water;

public class ObjectLibrary {
	
	private static Map<Integer,Class> enh = new HashMap<>();
	
	public static void init() {
		enh.put(Resources.TALLGRASS,TallGrass.class);
		enh.put(Resources.TREE,Tree.class);
		enh.put(Resources.SMOKE_SCREEN,SmokeScreen.class);
		enh.put(Resources.WATER,Water.class);
		enh.put(Resources.RUNE_TRAP,RuneTrap.class);
		enh.put(Resources.ENHANCEMENT_RUNE,EnhancementRune.class);
		enh.put(Resources.FROSTED,Frosted.class);
	}
	
	public static Enhancement getEnhancement(int id) {
		try {
			return Enhancement.class.cast(enh.get(id).newInstance());
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
}
