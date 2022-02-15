package rogue.game.pvp.individualcharacters;

import rogue.framework.resources.Resources;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.combat.skills.Skill.DamageType;
import rogue.game.world.objects.PlayableCharacter;

public class LukeSkywalker extends PlayableCharacter{

	
	public LukeSkywalker() {
		super(	Resources.LUKE,"Luke Skywalker",Resources.P_LUKE,0,
				50,15,60,18,4,3,1,
				getSkills(SkillLibrary.RIGHTEOUS_SWING,SkillLibrary.FORCE_VISION,SkillLibrary.FORCE_PUSH,SkillLibrary.SHOCKWAVE_JUMP,SkillLibrary.NONE,SkillLibrary.NONE),
				DamageType.SLASHING,
				Proficiency.FAITH,
				resistance(50, 45, 75, 40, 80, 35, 20, 60, 35),
				multipliers(1.0, 0.2, 0.0, 0.2, 1.6, 1.0, 0.0, 1.2, 1.2,1.0),
				proficiencies(55, 95, 10, 0, 15));
	}
}
