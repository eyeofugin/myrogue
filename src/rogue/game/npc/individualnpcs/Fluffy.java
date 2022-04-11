package rogue.game.npc.individualnpcs;

import rogue.framework.resources.Resources;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.combat.skills.Skill.DamageType;
import rogue.game.world.objects.NPC;
import rogue.game.world.objects.Entity.Proficiency;

public class Fluffy extends NPC{
	public Fluffy() {
		super(	Resources.FLUFFY,"Fluffy",Resources.SKELETONMALE,0,
				20,0,0,0,0,1,2,
				getSkills(SkillLibrary.NONE,SkillLibrary.NONE,SkillLibrary.NONE,SkillLibrary.NONE,SkillLibrary.NONE,SkillLibrary.NONE),
				DamageType.PIERCING,
				Proficiency.STRENGTH,
				resistance(70, 60, 75, 40, 20, 70, 70, 70, 70),
				multipliers(1.0, 1.0, 1.0,1.0, 1.0, 1.0, 1.0, 1.0, 1.0,1.0),
				proficiencies(30, 0, 0, 0, 0));
	}

}
