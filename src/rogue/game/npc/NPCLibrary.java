package rogue.game.npc;

import java.util.HashMap;

import rogue.framework.resources.Resources;
import rogue.game.combat.skills.Skill;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.npc.individualnpcs.Aragog;
import rogue.game.npc.individualnpcs.Zombie;
import rogue.game.pvp.CharacterLibrary;
import rogue.game.world.objects.entities.NPC;

public class NPCLibrary extends CharacterLibrary{

	public static HashMap<Integer,NPC> npcs = new HashMap<>();
	public static void init() {
//		npcs.put(Resources.LUKE_ASTRAL, new NPC(
//				Resources.LUKE_ASTRAL,"Luke",Resources.P_LUKE,0,
//				50,15,60,18,4,3,1,
//				getSkills(SkillLibrary.RIGHTEOUS_SWING,SkillLibrary.FORCE_VISION,SkillLibrary.FORCE_PUSH,SkillLibrary.SHOCKWAVE_JUMP,SkillLibrary.NONE,SkillLibrary.NONE),
//				DamageType.SLASHING,
//				Proficiency.FAITH,
//				resistance(50, 45, 75, 40, 80, 35, 20, 60, 35),
//				multipliers(1.0, 0.2, 0.0, 0.2, 1.6, 1.0, 0.0, 1.2, 1.2,1.0),
//				proficiencies(55, 95, 10, 0, 15)
//				));
		npcs.put(Resources.ARAGOG,new Aragog());
		npcs.put(Resources.ZOMBIE, new Zombie());
//		npcs.put(Resources.ARAGOG, new NPC(
//				Resources.ARAGOG,"Aragog",Resources.SKELETONMALE,0,
//				15,0,0,0,0,2,1,
//				getSkills(SkillLibrary.NONE,SkillLibrary.NONE,SkillLibrary.NONE,SkillLibrary.NONE,SkillLibrary.NONE,SkillLibrary.NONE),
//				DamageType.PIERCING,
//				Proficiency.STRENGTH,
//				resistance(70, 60, 75, 40, 20, 70, 10, 70, 70),
//				multipliers(1.0, 1.0, 1.0,1.0, 1.0, 1.0, 1.0, 1.0, 1.0,1.0),
//				proficiencies(35, 0, 0, 0, 0)
//				));
	}
	protected static Skill[] getSkills(int s1, int s2, int s3, int s4, int s5, int s6) {
		return new Skill[] {
			SkillLibrary.getSkill(s1),
			SkillLibrary.getSkill(s2),
			SkillLibrary.getSkill(s3),
			SkillLibrary.getSkill(s4),
			SkillLibrary.getSkill(s5),
			SkillLibrary.getSkill(s6)
		};
	}
	
	public static NPC getNpc(int id) {
		NPC npc = new NPC();
		npc.setId(npcs.get(id).getId());
		npc.setName(npcs.get(id).getName());
		npc.setPortraitId(npcs.get(id).getPortraitId());
		npc.setTeam(npcs.get(id).getTeam());
		npc.setMaxLife(npcs.get(id).getMaxLife());
		npc.setCurrentLife(npcs.get(id).getMaxLife());
		npc.setLifeRegain(npcs.get(id).getLifeRegain());
		npc.setMaxMana(npcs.get(id).getMaxMana());
		npc.setCurrentMana(npcs.get(id).getMaxMana());
		npc.setManaRegain(npcs.get(id).getManaRegain());
		npc.setMaxActions(npcs.get(id).getMaxActions());
		npc.setMaxMovement(npcs.get(id).getMaxMovement());
		npc.setRange(npcs.get(id).getRange());
		npc.setSkills(npcs.get(id).getSkills());
		npc.setStdDamageType(npcs.get(id).getStdDamageType());
		npc.setStdDamageProf(npcs.get(id).getStdDamageProf());
		npc.setResistances(npcs.get(id).getResistances());
		npc.setMultipliers(npcs.get(id).getMultipliers());
		npc.setProficiencies(npcs.get(id).getProficiencies());
		
		return npc;
	}
}
