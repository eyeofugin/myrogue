package rogue.game.pvp.individualcharacters;

import rogue.framework.resources.Resources;
import rogue.game.combat.skills.Skill.DamageType;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.world.objects.PlayableCharacter;

public class MotherTalzin extends PlayableCharacter{

	public MotherTalzin() {
		super(
				Resources.TALZIN,"Mother Talzin",Resources.P_LUKE,0,
				20,5,60,10,4,10,1,
				getSkills(SkillLibrary.ALLY_HEAL,SkillLibrary.ZOMBIE_MINIONS,SkillLibrary.VOODOO_SHIT,SkillLibrary.NONE,SkillLibrary.NONE,SkillLibrary.NONE),
				DamageType.SHOCK,
				Proficiency.INTELLIGENCE,
				resistance(20,30,20,20,30,15,20,5,35),
				multipliers(1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.1,1.0,1.0),
				proficiencies(10,0,35,0,5));
	}
}
