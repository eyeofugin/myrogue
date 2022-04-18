package rogue.game.pvp.individualcharacters;

import rogue.framework.resources.Resources;
import rogue.game.combat.skills.Skill.DamageType;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.world.objects.entities.Entity.Proficiency;
import rogue.game.world.objects.entities.PlayableCharacter;

public class ChinaSorrows extends PlayableCharacter{
	public ChinaSorrows() {
		super(Resources.CHINA,"China Sorrows",Resources.P_LUKE,0,
				20,5,60,10,4,2,1,
				getSkills(SkillLibrary.RUNE_TRAP,SkillLibrary.EQUIPMENT_UPGRADE,SkillLibrary.EVASIVE,SkillLibrary.ENHANCEMENT_RUNE,SkillLibrary.NONE,SkillLibrary.NONE),
				DamageType.NORMAL,
				Proficiency.STRENGTH,
				resistance(20,20,30,15,20,5,35),
				multipliers(1.0,1.0,1.0,1.0,1.0,1.1,1.0,1.0),
				proficiencies(10,0,35,0,5));}
}
