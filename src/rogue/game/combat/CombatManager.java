package rogue.game.combat;



import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import rogue.game.world.objects.Entity;
import rogue.game.world.objects.SecondLayerObject;
import rogue.game.world.objects.Skill;

public class CombatManager {

	static public void normalMelee(SecondLayerObject p, SecondLayerObject o) {
		
		Entity defender = Entity.class.cast(o);
		Entity attacker = Entity.class.cast(p);
		
		int normalMeleeDamage = attacker.getNormalMeleeDamage();
		int normalMeleeDefense = defender.getNormalMeleeDefense();
		System.out.println(normalMeleeDamage + "||" + normalMeleeDefense);
		
		int damage = (int)(rdmize(normalMeleeDamage) * (10/(10+(double)rdmize(normalMeleeDefense))));
		System.out.println("damage: " + damage);
		
		defender.damage(damage);
		System.out.println("life: " + defender.getCurrentLife());
	
	}
	static public void executeSkill(SecondLayerObject p, List<SecondLayerObject> os,Skill s) {
		
		Entity attacker = Entity.class.cast(p);
		for(SecondLayerObject o: os) {
			Entity defender = Entity.class.cast(o);
			
			int damage = attacker.getNormalMeleeDamage();
			int defense = defender.getNormalMeleeDefense();
			
			int total = (int)(rdmize(damage) * (10/(10+(double)rdmize(defense))));
			
			defender.damage(total);
		}
	}
	static private int rdmize(int a) {
		return a + ThreadLocalRandom.current().nextInt(-1, 1);
		
	}
}
