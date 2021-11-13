package rogue.game.combat;



import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import rogue.game.world.objects.Entity;
import rogue.game.world.objects.SecondLayerObject;

public class CombatManager {

	static public void normalMelee(SecondLayerObject p, SecondLayerObject o) {
		Random rand = new Random();
		
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
	static private int rdmize(int a) {
		return a + ThreadLocalRandom.current().nextInt(-1, 1);
		
	}
}
