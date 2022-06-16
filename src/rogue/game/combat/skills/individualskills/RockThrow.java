package rogue.game.combat.skills.individualskills;

import rogue.game.combat.skills.Skill;
import rogue.game.combat.skills.Skill.Effect.EffectType;
import rogue.game.combat.skills.Skill.Effect.StatusInfliction;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.world.objects.entities.Entity.Proficiency;

public class RockThrow extends Skill{
	public RockThrow() {
		super(SkillLibrary.ROCK_THROW);
		this.name="Rock Throw";
		this.description="Throws Rock at Spot";
		this.target=TargetType.SINGLE_TARGET;
		this.damageType=DamageType.NORMAL;
		this.effects=of(new Effect[] {
				new Effect(EffectType.STATUS_INFLICTION,1,0,StatusInfliction.STUNNED,null)
		});
		this.multipliers=of(new Multiplier[] {
				new Multiplier(Proficiency.STRENGTH,0.3)
		});
		this.power=35;
		this.accuracy=60;
		this.distance=3;
		this.manaCost=20;
		this.actionCost=1;
	}
}
