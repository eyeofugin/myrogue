package rogue.game.world.objects;

import java.util.Map;

import rogue.framework.eventhandling.Connector;
import rogue.game.combat.skills.Skill;
import rogue.game.combat.skills.Skill.DamageType;
import rogue.game.world.objects.Entity.Proficiency;
import util.MovementOption;

public class NPC extends Entity{

	public NPC() {
		super();
	}
	
	public NPC(int x, int y, byte id,String name, byte portraitId, int team, MovementOption movement, Connector connector) {
		super(x, y, id, connector,name,CharacterTemplate.NONE,team,portraitId,movement);
	}
	
	public NPC(int x, int y, byte id,String name, byte portraitId, int team, MovementOption movement, Connector connector, CharacterTemplate template) {
		super(x, y, id, connector,name,template,team,portraitId,movement);
	}
	public NPC(int id, String name, int portraitId, int team,
			int maxLife,int lifeRegain,int maxMana,int manaRegain,int maxActions,int maxMovement,int range,Skill[] skills,DamageType std,Proficiency stdP,
			Map<DamageType,Integer> resistances,Map<DamageType,Double> multipliers,Map<Proficiency,Integer> proficiencies) {
		super(id, portraitId, name, MovementOption.ENEMY, team, null, maxLife, lifeRegain, maxMana, manaRegain, maxActions, maxMovement, range, skills, std, stdP, resistances, multipliers, proficiencies);
	}
	public EntityType getEntityType() {
		return EntityType.NPC;
	}
}
