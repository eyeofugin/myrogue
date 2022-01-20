package rogue.game.world.objects;

import rogue.framework.eventhandling.Connector;
import rogue.game.world.objects.Entity.EntityType;
import util.MovementOption;

public class PlayableCharacter extends Entity{
	
	public PlayableCharacter() {
		super();
	}
	
	public PlayableCharacter(int x, int y, byte id,String name, byte portraitId, int team, MovementOption movement, Connector connector) {
		super(x, y, id, connector,name,CharacterTemplate.NONE,team,portraitId,movement);
	}
	
	public PlayableCharacter(int x, int y, byte id,String name, int team, byte portraitId, MovementOption movement, Connector connector, CharacterTemplate template) {
		super(x, y, id, connector,name,template,team,portraitId,movement);
	}	
	public EntityType getEntityType() {
		return EntityType.PLAYABLE;
	}
}
