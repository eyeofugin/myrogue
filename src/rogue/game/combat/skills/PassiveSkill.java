package rogue.game.combat.skills;

import java.util.List;

public class PassiveSkill extends BaseSkill{

	public PassiveSkill(
			byte id,
			String name,
			String description,
			int power,
			int radius,
			DamageType damage,
			List<Effect> effects,
			List<Multiplier> multipliers) {
		super(id,name,description,damage,TargetType.SELF,radius,effects,multipliers);
		
	}
}
