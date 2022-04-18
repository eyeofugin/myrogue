package rogue.game.pvp.individualcharacters;

import rogue.framework.resources.Resources;
import rogue.game.combat.skills.Skill.DamageType;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.world.objects.entities.Entity.Proficiency;
import rogue.game.world.objects.entities.PlayableCharacter;

public class Obelix extends PlayableCharacter{
	public Obelix() {
		super(Resources.OBELIX,"Obelix",Resources.P_LUKE,0,
				20,5,60,10,4,2,1,
				getSkills(SkillLibrary.ROCK_THROW,SkillLibrary.FELL_IN_THE_POT,SkillLibrary.SEND_IDEFIX,SkillLibrary.NONE,SkillLibrary.NONE,SkillLibrary.NONE),
				DamageType.NORMAL,
				Proficiency.STRENGTH,
				resistance(20,20,30,15,20,5,35),
				multipliers(1.0,1.0,1.0,1.0,1.0,1.1,1.0,1.0),
				proficiencies(10,0,35,0,5));}
}
