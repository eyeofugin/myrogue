package rogue.game.combat.skills.individualskills;

import rogue.game.combat.skills.Skill;
import rogue.game.combat.skills.Skill.Effect.EffectType;
import rogue.game.combat.skills.Skill.Effect.StatusInfliction;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.world.objects.entities.Entity.Proficiency;

public class ThrowingWeapon extends Skill{
	public ThrowingWeapon() {
		super(SkillLibrary.BATARANG);
		this.name="Throwing Weapon";
		this.description="Throw a small Weapon";
		this.target=TargetType.LINE;
		this.damageType=DamageType.NORMAL;
		this.effects=of(new Effect[] {
				new Effect(EffectType.STATUS_INFLICTION,2,10,StatusInfliction.BLEEDING,null)
		});
		this.multipliers=of(new Multiplier[] {
				new Multiplier(Proficiency.LETHALITY,0.3)
		});
		this.power=25;
		this.accuracy=60;
		this.distance=5;
		this.manaCost=20;
		this.actionCost=1;
	}	
}
