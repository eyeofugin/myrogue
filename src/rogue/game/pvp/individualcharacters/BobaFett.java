package rogue.game.pvp.individualcharacters;

import rogue.framework.resources.Resources;
import rogue.game.combat.skills.Skill.DamageType;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.world.objects.entities.Entity.Proficiency;
import rogue.game.world.objects.entities.PlayableCharacter;

public class BobaFett extends PlayableCharacter{

	public BobaFett() {
		super(
				Resources.BOBA,"Boba Fett",Resources.P_BOBA,0,
				50,5,40,5,4,3,3,
				getSkills(SkillLibrary.FLAMETHROWER,SkillLibrary.JETPACK_BOOST,SkillLibrary.ROCKET,SkillLibrary.NONE,SkillLibrary.NONE,SkillLibrary.NONE),
				DamageType.BURNING,
				Proficiency.PRECISION,
				resistance(40, 55, 40, 90, 20, 60, 95),
				multipliers( 0.0, 0.0, 1.0, 1.0, 0.0, 1.0, 1.0,1.0),
				proficiencies(55, 95, 10, 0, 15));
	}
}
