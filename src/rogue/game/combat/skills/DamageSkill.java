package rogue.game.combat.skills;

import java.util.List;

import rogue.game.world.objects.Entity;

public class DamageSkill extends ActiveSkill{

	private int power;
	
	public DamageSkill(byte id,
			String name, 
			String description, 
			TargetType target, 
			DamageType damage, 
			int radius,
			List<Effect> effects, 
			List<Multiplier> multipliers, 
			int power, 
			int distance, 
			int manaCost, 
			int lifeCost,
			int actionCost, 
			int accuracy) {
		super(id, name, description, target, damage, radius, effects, multipliers, power, distance, manaCost, lifeCost,
				actionCost, accuracy);
		
	}
	public int getDamage(Entity caster) {
		int totalDamage = (int) ((this.power + (getMultipliers(caster))) * caster.getMultiplier(this.damageType));
		return totalDamage;
	}
	private int getMultipliers(Entity caster) {
		int multiplierDamage=0;
		for(Multiplier m : this.multipliers) {
			multiplierDamage+=caster.getProficiency(m.getProf());
		}
		return multiplierDamage;
	}

}
