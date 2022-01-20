package rogue.game.combat;



import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import rogue.game.combat.skills.DamageSkill;
import rogue.game.world.objects.BattleLog;
import rogue.game.world.objects.Entity;
import rogue.game.world.objects.Entity.Proficiency;
import rogue.game.world.objects.SecondLayerObject;

public class CombatManager {

	static public void normalMelee(SecondLayerObject p, SecondLayerObject o, BattleLog log) {
		
		Entity defender = Entity.class.cast(o);
		Entity attacker = Entity.class.cast(p);
		
		int normalMeleeDamage = attacker.getProficiency(Proficiency.STRENGTH);
		int normalMeleeDefense = defender.getResistance(attacker.getBasicDamageType());
		System.out.println(normalMeleeDamage + "||" + normalMeleeDefense);
		
		int damage = (int)(rdmize(normalMeleeDamage) * (10/(10+(double)rdmize(normalMeleeDefense))));
		System.out.println("damage: " + damage);
		
		defender.damage(damage);
		System.out.println("life: " + defender.getCurrentLife());
	
		log.formulateUse("Basic attack", p.getName());
		log.formulateEffect(o.getName(), damage);
	}
	static public void executeDamageSkill(SecondLayerObject p, List<SecondLayerObject> os,DamageSkill s, BattleLog log) {
		
		log.formulateUse(s.getName(), p.getName());
		Entity attacker = Entity.class.cast(p);
		int damage = s.getDamage(attacker);
		for(SecondLayerObject o: os) {
			Entity defender = Entity.class.cast(o);
			
			
			int defense = defender.getResistance(s.getDamageType());
			
			int total = (int)(rdmize(damage) * (10/(10+(double)rdmize(defense))));
			
			defender.damage(total);
			
			log.formulateEffect(o.getName(), damage);
		}
	}
	static private int rdmize(int a) {
		return a + ThreadLocalRandom.current().nextInt(-1, 1);
		
	}
}
