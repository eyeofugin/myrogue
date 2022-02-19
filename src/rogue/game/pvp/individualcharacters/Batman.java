package rogue.game.pvp.individualcharacters;

import rogue.framework.resources.Resources;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.combat.skills.Skill.DamageType;
import rogue.game.world.objects.PlayableCharacter;

public class Batman extends PlayableCharacter{
	public Batman(){
		super(Resources.BATMAN,"Batman",Resources.P_LUKE,0,
				20,5,60,10,4,2,1,
				getSkills(SkillLibrary.BATARANG,SkillLibrary.SMOKE_SCREEN,SkillLibrary.GRAPPLING_HOOK,SkillLibrary.NONE,SkillLibrary.NONE,SkillLibrary.NONE),
				DamageType.SLASHING,
				Proficiency.LETHALITY,
				resistance(20,30,20,20,30,15,20,5,35),
				multipliers(1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.1,1.0,1.0),
				proficiencies(10,0,35,0,5));}
}
