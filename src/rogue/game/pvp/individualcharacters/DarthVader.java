package rogue.game.pvp.individualcharacters;

import rogue.framework.resources.Resources;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.combat.skills.Skill.DamageType;
import rogue.game.world.objects.PlayableCharacter;

public class DarthVader extends PlayableCharacter{

	public DarthVader() {
		super(Resources.DARTH_VADER,"Darth Vader",Resources.P_VADER,0,
				100,5,50,10,3,3,1,
				getSkills(SkillLibrary.HATEFUL_SWING,SkillLibrary.FORCE_CHOKE,SkillLibrary.WEAPON_THROW,SkillLibrary.MERCILESS_MASSACRE,SkillLibrary.NONE,SkillLibrary.NONE),
				DamageType.SLASHING,
				Proficiency.STRENGTH,
				resistance(50, 20, 80, 70, 40, 50, 30, 10, 50),
				multipliers(1.0, 0.4, 1.7, 0.2, 0.0, 1.0, 0.0, 0.0, 1.2,1.0),
				proficiencies(75, 85, 20, 0, 30));
	}
}
