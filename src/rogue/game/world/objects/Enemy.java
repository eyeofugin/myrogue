package rogue.game.world.objects;

import rogue.framework.eventhandling.Connector;
import util.MovementOption;

public class Enemy extends Entity{

	public Enemy(int x, int y, byte id,String name, byte portraitId, MovementOption movement, Connector connector) {
		super(x, y, id, connector,name,portraitId,movement);
	}

}
