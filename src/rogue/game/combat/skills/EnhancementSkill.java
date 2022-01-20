package rogue.game.combat.skills;

import java.util.List;

public class EnhancementSkill extends ActiveSkill{
	
	
	public EnhancementSkill(byte id,
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
}
