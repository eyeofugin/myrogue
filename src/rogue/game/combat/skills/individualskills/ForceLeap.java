package rogue.game.combat.skills.individualskills;

import rogue.game.combat.skills.Skill;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.combat.skills.Skill.DamageType;
import rogue.game.combat.skills.Skill.Effect;
import rogue.game.combat.skills.Skill.Multiplier;
import rogue.game.combat.skills.Skill.TargetType;
import rogue.game.combat.skills.Skill.Effect.EffectType;
import rogue.game.combat.skills.Skill.Effect.StatusInfliction;
import rogue.game.world.objects.entities.Entity.Proficiency;

public class ForceLeap extends Skill{
	public ForceLeap() {
		super(SkillLibrary.SHOCKWAVE_JUMP);
		this.name="Lukes Force Leap";
		this.description="Leaps to Spot and paralyses Enemies";
		this.target=TargetType.SINGLE_FREE;
		this.damageType=DamageType.LIGHT;
		this.effects=of(new Effect[] {
				new Effect(EffectType.STATUS_INFLICTION,1,1,StatusInfliction.PARALYSED,null)
			});
		this.multipliers=of(new Multiplier[] {
				new Multiplier(Proficiency.FAITH,0.3)
		});
		this.type=SkillType.MOVEMENT;
		this.power=20;
		this.accuracy=100;
		this.distance=1;
		this.manaCost=20;
		this.actionCost=1;
	}
}
