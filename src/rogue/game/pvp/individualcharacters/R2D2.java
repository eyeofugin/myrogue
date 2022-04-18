package rogue.game.pvp.individualcharacters;

import rogue.framework.resources.Resources;
import rogue.game.combat.skills.Skill.DamageType;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.world.objects.entities.PlayableCharacter;

public class R2D2 extends PlayableCharacter{
	public R2D2() {
		super(
				Resources.R2D2,"R2D2",Resources.P_BOBA,0,
				20,5,60,10,4,2,1,
				getSkills(SkillLibrary.SCAN,SkillLibrary.SMOKE_SCREEN,SkillLibrary.NONE,SkillLibrary.NONE,SkillLibrary.NONE,SkillLibrary.NONE),
				DamageType.SHOCK,
				Proficiency.INTELLIGENCE,
				resistance(20,20,30,15,20,5,35),
				multipliers(1.0,1.0,1.0,1.0,1.0,1.1,1.0,1.0),
				proficiencies(10,0,35,0,5));
	}
}
