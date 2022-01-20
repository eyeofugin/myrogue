package rogue.game.combat.skills;

import java.util.List;

import rogue.game.world.objects.SecondLayerObject;

public class SummonSkill extends ActiveSkill{
	
	private SecondLayerObject summonedObject;
	
	public SummonSkill(byte id,
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
			int actionCost
			) {
		super(id, name, description, target, damage, radius, effects, multipliers, power, distance, manaCost, lifeCost,
				actionCost, 100);
		
	}
}
