package rogue.game.world.objects;

import rogue.framework.eventhandling.Connector;

public class Enemy extends Entity{

	public Enemy(int x, int y, byte id,String name, byte portraitId, Connector connector) {
		super(x, y, id, connector,name,portraitId,false);
	}

}
