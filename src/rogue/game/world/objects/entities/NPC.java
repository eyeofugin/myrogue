package rogue.game.world.objects.entities;

import java.util.List;
import java.util.Map;

import rogue.game.combat.skills.Skill;
import rogue.game.combat.skills.Skill.DamageType;

public class NPC extends Entity{

	public NPC() {
	}
	public NPC(int id, int portraitId, String name, int team, int maxLife, int lifeRegain,
			int maxMana, int manaRegain, int maxActions, int maxMovement, int range, List<Skill> skills, DamageType std,
			Proficiency stdP, Map<DamageType, Integer> resistances, Map<DamageType, Double> multipliers,
			Map<Proficiency, Integer> proficiencies) {
		super(id, portraitId, name, team, maxLife, lifeRegain, maxMana, manaRegain, maxActions, maxMovement, range,
				skills, std, stdP, resistances, multipliers, proficiencies);
	}

	
	
}
