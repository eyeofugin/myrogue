package rogue.game.combat.skills.individualskills;

import rogue.game.combat.skills.Skill;
import rogue.game.combat.skills.Skill.Effect.EffectType;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.world.objects.entities.Entity.Proficiency;

public class ShadowWave extends Skill{
	public ShadowWave() {
		super(SkillLibrary.SHADOW_WAVE);
		this.name="Shadow Wave";
		this.description="Crush a Wave of Darkness into your Enemies";
		this.target=TargetType.SINGLE_TARGET;
		this.damageType=DamageType.DARK;
		this.effects=of(new Effect[] {
				new Effect(EffectType.OBJECT_PUSH,0,1,null,null)
		});
		this.multipliers=of(new Multiplier[] {
				new Multiplier(Proficiency.FAITH,0.3)
		});
		this.power=50;
		this.accuracy=80;
		this.distance=3;
		this.manaCost=25;
		this.actionCost=1;
	}
}
