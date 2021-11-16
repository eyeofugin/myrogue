package rogue.game.world.objects;

import rogue.framework.eventhandling.Connector;
import util.MovementOption;

public class NPC extends Entity{

	public NPC() {
		super();
	}
	
	public NPC(int x, int y, byte id,String name, byte portraitId, MovementOption movement, Connector connector) {
		super(x, y, id, connector,name,CharacterTemplate.NONE,portraitId,movement);
	}
	
	public NPC(int x, int y, byte id,String name, byte portraitId, MovementOption movement, Connector connector, CharacterTemplate template) {
		super(x, y, id, connector,name,template,portraitId,movement);
	}	
}
