package rogue.game.pvp.individualcharacters;

import rogue.game.world.objects.PlayableCharacter;

import rogue.framework.resources.Resources;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.combat.skills.Skill.DamageType;

public class Baumbart extends PlayableCharacter{
	public Baumbart() {
		super(Resources.BAUMBART,"Baumbart",Resources.P_LUKE,0,
				20,5,60,10,4,2,1,
				getSkills(SkillLibrary.TEND_THE_GARDEN,SkillLibrary.TALL_GRASS,SkillLibrary.PLANT,SkillLibrary.NONE,SkillLibrary.NONE,SkillLibrary.NONE),
				DamageType.BLUDGEONING,
				Proficiency.STRENGTH,
				resistance(20,30,20,20,30,15,20,5,35),
				multipliers(1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.1,1.0,1.0),
				proficiencies(10,0,35,0,5));
	}
}
