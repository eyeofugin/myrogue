package rogue.game.npc;

import java.util.HashMap;

import rogue.framework.resources.Resources;
import rogue.game.combat.skills.Skill.DamageType;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.pvp.CharacterLibrary;
import rogue.game.world.objects.Entity.Proficiency;
import rogue.game.world.objects.NPC;

public class NPCLibrary extends CharacterLibrary{

	public static HashMap<Integer,NPC> npcs = new HashMap<>();
	public static void init() {
		npcs.put(Resources.LUKE_ASTRAL, new NPC(
				Resources.LUKE_ASTRAL,"Luke",Resources.P_LUKE,0,
				50,15,60,18,4,3,1,
				getSkills(SkillLibrary.RIGHTEOUS_SWING,SkillLibrary.FORCE_VISION,SkillLibrary.FORCE_PUSH,SkillLibrary.SHOCKWAVE_JUMP,SkillLibrary.NONE,SkillLibrary.NONE),
				DamageType.SLASHING,
				Proficiency.FAITH,
				resistance(50, 45, 75, 40, 80, 35, 20, 60, 35),
				multipliers(1.0, 0.2, 0.0, 0.2, 1.6, 1.0, 0.0, 1.2, 1.2,1.0),
				proficiencies(55, 95, 10, 0, 15)
				));
	}
	
	public static NPC getNpc(int id) {
		return npcs.get(id);
	}
}
