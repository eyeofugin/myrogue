package rogue.game.world.objects;

import java.util.HashMap;
import java.util.Map;

import rogue.framework.resources.Resources;
import rogue.game.world.objects.SubEnhancement.Level;

public class ObjectLibrary {
	
	private static Map<Integer,SubEnhancement> obj = new HashMap<>();
	
	public static void init() {
		obj.put(Resources.TALLGRASS,new SubEnhancement(Resources.TALLGRASS,Level.TOP,false,true,-1,true,true));
		obj.put(Resources.TREE,new SubEnhancement(Resources.TREE,Level.TOP,true,true,-1,true,true));
		obj.put(Resources.SMOKE_SCREEN,new SubEnhancement(Resources.SMOKE_SCREEN,Level.SUB,false,true,3,true,true));
	}
	
	public static SubEnhancement getEnhancement(int id) {
		if( obj.get(id)==null) {
			return null;
		}
		SubEnhancement e = new SubEnhancement();
		e.setId(id);
		e.setDuration(obj.get(id).getDuration());
		e.setSolid(obj.get(id).isSolid());
		e.setBlockVisibility(obj.get(id).isBlockVisibility());
		e.setShowEnemy(obj.get(id).isShowEnemy());
		e.setShowTeam(obj.get(id).isShowTeam());
		return e;
	}
}
