package rogue.game.world.objects.tiles.individualenhancements;

import rogue.framework.resources.Resources;
import rogue.game.world.objects.tiles.Enhancement;
import rogue.game.world.objects.tiles.Enhancement.Level;

public class Frosted extends Enhancement{
	public Frosted() {
		super(Resources.FROSTED);
		this.level = Level.SUB;
		this.visTeam=true;
		this.visEnemy=true;
		this.solid=false;
		this.blockVis=true;
		this.duration=2;
	}
}
