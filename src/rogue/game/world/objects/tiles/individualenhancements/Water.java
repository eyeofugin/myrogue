package rogue.game.world.objects.tiles.individualenhancements;

import rogue.framework.resources.Resources;
import rogue.game.world.objects.tiles.Enhancement;

public class Water extends Enhancement{
	public Water() {
		super(Resources.WATER);
		this.level = Level.TOP;
		this.visTeam=true;
		this.visEnemy=true;
		this.solid=true;
		this.blockVis=false;
		this.duration=-1;
	}
}
