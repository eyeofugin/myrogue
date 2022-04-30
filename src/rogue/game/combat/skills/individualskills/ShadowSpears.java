package rogue.game.combat.skills.individualskills;

import rogue.game.combat.skills.Skill;
import rogue.game.combat.skills.Skill.Effect.EffectType;
import rogue.game.combat.skills.Skill.Effect.StatusInfliction;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.world.objects.entities.Entity.Proficiency;

public class ShadowSpears extends Skill{
	public ShadowSpears() {
		super(SkillLibrary.SHADOW_SPEARS);
		this.name="Shadow Spears";
		this.description="Cast Spears made out of Darkness";
		this.target=TargetType.LINE_PIERCING;
		this.damageType=DamageType.DARK;
		this.effects=of(new Effect[] {
				new Effect(EffectType.STATUS_INFLICTION,1,5,StatusInfliction.BLEEDING,null)
		});
		this.multipliers=of(new Multiplier[] {
				new Multiplier(Proficiency.FAITH,0.5)
		});
		this.power=20;
		this.accuracy=80;
		this.distance=3;
		this.manaCost=20;
		this.actionCost=1;
	}
}
