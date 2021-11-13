package rogue.game.world.objects;

import rogue.framework.eventhandling.Connector;
import util.MovementOption;

public class PlayableCharacter extends Entity{
	
	public PlayableCharacter() {
		super();
	}
	
	public PlayableCharacter(int x, int y, byte id,String name, byte portraitId, MovementOption movement, Connector connector) {
		super(x, y, id, connector,name,portraitId,true,movement);
		loadSkills(CharacterTemplate.NONE);
	}
	
	public PlayableCharacter(int x, int y, byte id,String name, byte portraitId, MovementOption movement, Connector connector, CharacterTemplate template) {
		super(x, y, id, connector,name,portraitId,true,movement);
		loadSkills(template);
	}
	
	private void loadSkills(CharacterTemplate t) {
		if(t.equals(CharacterTemplate.NONE)) {
			this.setSkills(new Skill[] {Skill.NONE,Skill.NONE,Skill.NONE,Skill.NONE,Skill.NONE,Skill.NONE,Skill.NONE});
		}
	}
	public static enum CharacterTab{
		STATS,
		SKILLS,
		ITEMS,
		GEAR,
	}
	public static enum CharacterTemplate{
		KNIGHT,
		NONE,
	}

}
