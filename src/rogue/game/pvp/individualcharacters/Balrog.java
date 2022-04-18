package rogue.game.pvp.individualcharacters;

import rogue.framework.resources.Resources;
import rogue.game.combat.skills.Skill.DamageType;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.world.objects.entities.Entity.Proficiency;
import rogue.game.world.objects.entities.PlayableCharacter;

public class Balrog extends PlayableCharacter{

	public Balrog() {
		super(Resources.BALROG,"Balrog",Resources.P_LUKE,0,
				200,15,//life / regain
				60,10,//mana / regain
				4,2,1,//actions / movement / range
				getSkills(SkillLibrary.FLAME_WHIP,SkillLibrary.FIRE_ARMOR,SkillLibrary.SMOKE_SCREEN,SkillLibrary.AREAFIRE,SkillLibrary.NONE,SkillLibrary.NONE),
				DamageType.BURNING,
				Proficiency.STRENGTH,
				resistance(20,30,20,20,30,15,20),
				multipliers(1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.1),
				proficiencies(10,0,35,0,5));}
}
