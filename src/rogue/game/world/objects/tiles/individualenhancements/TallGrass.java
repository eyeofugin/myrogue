package rogue.game.world.objects.tiles.individualenhancements;

import rogue.framework.resources.Resources;
import rogue.game.world.objects.tiles.Enhancement;

public class TallGrass extends Enhancement{
	
	public TallGrass() {
		super(Resources.TALLGRASS);
		this.level = Level.TOP;
		this.visTeam=true;
		this.visEnemy=true;
		this.solid=false;
		this.blockVis=true;
		this.duration=-1;
	}
}
