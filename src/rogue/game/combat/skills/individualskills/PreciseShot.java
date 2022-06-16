package rogue.game.combat.skills.individualskills;

import rogue.game.combat.skills.Skill;
import rogue.game.combat.skills.Skill.Effect.EffectType;
import rogue.game.combat.skills.Skill.Effect.StatusInfliction;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.world.objects.entities.Entity.Proficiency;

public class PreciseShot extends Skill{

	public PreciseShot() {
		super(SkillLibrary.HEART_SHOT);
		this.name="Precise Shot";
		this.description="Shot with possible Effect";
		this.target=TargetType.SINGLE_TARGET;
		this.damageType=DamageType.NORMAL;
		this.effects=of(new Effect[] {
				new Effect(EffectType.STATUS_INFLICTION,3,1,StatusInfliction.PARALYSED,null)
		});
		this.multipliers=of(new Multiplier[] {
				new Multiplier(Proficiency.PRECISION,0.8)
		});
		this.power=60;
		this.accuracy=75;
		this.distance=5;
		this.manaCost=30;
		this.actionCost=2;
	}	
}
