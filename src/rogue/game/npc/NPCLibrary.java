package rogue.game.npc;

import java.util.HashMap;

import rogue.framework.resources.Resources;
import rogue.game.combat.skills.Skill;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.npc.individualnpcs.Aragog;
import rogue.game.npc.individualnpcs.Fluffy;
import rogue.game.npc.individualnpcs.GhostNpc;
import rogue.game.npc.individualnpcs.GoblinNpc;
import rogue.game.npc.individualnpcs.Zombie;
import rogue.game.pvp.CharacterLibrary;
import rogue.game.pvp.individualcharacters.Ghost;
import rogue.game.world.objects.entities.NPC;
import rogue.game.world.objects.entities.PlayableCharacter;

public class NPCLibrary extends CharacterLibrary{

	public static HashMap<Integer,Class> npcs = new HashMap<>();
	public static void init() {
		npcs.put(Resources.ARAGOG,Aragog.class);
		npcs.put(Resources.ZOMBIE,Zombie.class);
		npcs.put(Resources.FLUFFY,Fluffy.class);
		npcs.put(Resources.GOBLINNPC,GoblinNpc.class);
		npcs.put(Resources.GHOSTNPC,GhostNpc.class);
	}
	public static NPC getNpc(int id) {
		try {
			return NPC.class.cast(npcs.get(id).newInstance());
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
}
