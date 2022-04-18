package rogue.game.pvp.individualcharacters;

import rogue.framework.resources.Resources;
import rogue.game.combat.skills.Skill.DamageType;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.world.objects.entities.Entity.Proficiency;
import rogue.game.world.objects.entities.PlayableCharacter;

public class Voldemort extends PlayableCharacter{
	public Voldemort() {
		super(Resources.VOLDEMORT,"Voldemort",Resources.P_LUKE,0,
				20,5,60,10,4,2,1,
				getSkills(SkillLibrary.FIEND_FYRE,SkillLibrary.TELEPORT,SkillLibrary.HORCRUX,SkillLibrary.MURDER_FEST,SkillLibrary.NONE,SkillLibrary.NONE),
				DamageType.MAGICAL,
				Proficiency.INTELLIGENCE,
				resistance(20,20,30,15,20,5,35),
				multipliers(1.0,1.0,1.0,1.0,1.0,1.1,1.0,1.0),
				proficiencies(10,0,35,0,5));}
}
