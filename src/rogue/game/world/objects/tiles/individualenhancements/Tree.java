package rogue.game.world.objects.tiles.individualenhancements;

import rogue.framework.resources.Resources;
import rogue.game.world.objects.tiles.Enhancement;

public class Tree extends Enhancement{
	public Tree() {
		super(Resources.TREE);
		this.level = Level.TOP;
		this.visTeam=true;
		this.visEnemy=true;
		this.solid=true;
		this.blockVis=true;
		this.duration=-1;
	}
}
