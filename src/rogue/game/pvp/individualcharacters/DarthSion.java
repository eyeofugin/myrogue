package rogue.game.pvp.individualcharacters;

import rogue.framework.resources.Resources;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.combat.skills.Skill.DamageType;
import rogue.game.world.objects.PlayableCharacter;

public class DarthSion extends PlayableCharacter{

	public DarthSion() {
		super(
				Resources.DARTH_SION,"Darth Sion",Resources.P_D_SION,0,
				140,20,50,5,3,2,1,
				getSkills(SkillLibrary.HATEFUL_SWING,SkillLibrary.CONDEMN,SkillLibrary.UNSTOPPABLE,SkillLibrary.LAST_RESERVE,SkillLibrary.NONE,SkillLibrary.NONE),
				DamageType.SLASHING,
				Proficiency.STRENGTH,
				resistance(70, 80, 90, 50, 40, 65, 35, 85, 90),
				multipliers(1.0, 0.5, 1.4, 0.2, 0.0, 1.0, 0.0, 1.1, 1.1,1.0),
				proficiencies(65, 75, 15, 0, 15));
	}
}
