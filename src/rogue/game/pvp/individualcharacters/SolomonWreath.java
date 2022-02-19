package rogue.game.pvp.individualcharacters;

import rogue.framework.resources.Resources;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.combat.skills.Skill.DamageType;
import rogue.game.world.objects.PlayableCharacter;

public class SolomonWreath extends PlayableCharacter{
	public SolomonWreath() {
		super(Resources.SOLOMON,"Solomon Wreath",Resources.P_LUKE,0,
				20,5,60,10,4,2,1,
				getSkills(SkillLibrary.SHADOW_SPEARS,SkillLibrary.SHADOW_WALK,SkillLibrary.SHADOW_WAVE,SkillLibrary.NONE,SkillLibrary.NONE,SkillLibrary.NONE),
				DamageType.DARK,
				Proficiency.FAITH,
				resistance(20,30,20,20,30,15,20,5,35),
				multipliers(1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.1,1.0,1.0),
				proficiencies(10,0,35,0,5));}
}
