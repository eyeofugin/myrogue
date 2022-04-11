package rogue.game.world.objects;

import java.util.HashMap;
import java.util.Map;

import rogue.framework.eventhandling.Connector;
import rogue.game.combat.skills.Skill;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.combat.skills.Skill.DamageType;
import rogue.game.world.objects.Entity.Proficiency;
import util.MovementOption;

public class PlayableCharacter extends Entity{
	
	public PlayableCharacter() {
		super();
	}
	
	public PlayableCharacter(int x, int y, int id,String name, int portraitId, int team, MovementOption movement, Connector connector) {
		super();
	}
	public PlayableCharacter(int id, String name, int portraitId, int team,
			int maxLife,int lifeRegain,int maxMana,int manaRegain,int maxActions,int maxMovement,int range,Skill[] skills,DamageType std,Proficiency stdP,
			Map<DamageType,Integer> resistances,Map<DamageType,Double> multipliers,Map<Proficiency,Integer> proficiencies) {
		super(id,portraitId,name,MovementOption.PLAYER,team,null,maxLife,lifeRegain,maxMana,manaRegain,maxActions,maxMovement,range,skills,std,stdP,resistances,multipliers,proficiencies);
	}

}
