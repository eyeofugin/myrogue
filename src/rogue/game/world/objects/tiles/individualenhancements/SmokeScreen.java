package rogue.game.world.objects.tiles.individualenhancements;

import rogue.framework.resources.Resources;
import rogue.game.world.objects.tiles.Enhancement;

public class SmokeScreen extends Enhancement{
	public SmokeScreen() {
		super(Resources.SMOKE_SCREEN);
		this.level = Level.SUB;
		this.visTeam=true;
		this.visEnemy=true;
		this.solid=false;
		this.blockVis=true;
		this.duration=3;
	}
}
