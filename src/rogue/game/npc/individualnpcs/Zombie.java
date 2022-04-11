package rogue.game.npc.individualnpcs;

import rogue.framework.resources.Resources;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.combat.skills.Skill.DamageType;
import rogue.game.world.objects.NPC;

public class Zombie extends NPC{
	public Zombie() {
		super(
				Resources.ZOMBIE,"Zombie",Resources.SKELETONMALE,0,
				10,0,0,0,0,1,1,
				getSkills(SkillLibrary.NONE,SkillLibrary.NONE,SkillLibrary.NONE,SkillLibrary.NONE,SkillLibrary.NONE,SkillLibrary.NONE),
				DamageType.DARK,
				Proficiency.STRENGTH,
				resistance(70, 60, 75, 40, 20, 70, 70, 70, 70),
				multipliers(1.0, 1.0, 1.0,1.0, 1.0, 1.0, 1.0, 1.0, 1.0,1.0),
				proficiencies(30, 0, 0, 0, 0)
				);
	}
}
