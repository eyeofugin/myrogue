package rogue.game.combat.skills.individualskills;

import rogue.game.combat.skills.Skill;
import rogue.game.combat.skills.Skill.DamageType;
import rogue.game.combat.skills.Skill.Effect;
import rogue.game.combat.skills.Skill.Effect.EffectType;
import rogue.game.combat.skills.Skill.Effect.StatusInfliction;
import rogue.game.combat.skills.Skill.Multiplier;
import rogue.game.combat.skills.Skill.TargetType;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.world.objects.entities.Entity.Proficiency;

public class RighteousSwing extends Skill{
	public RighteousSwing() {
		super(SkillLibrary.RIGHTEOUS_SWING);
		this.name="Righteous Swing";
		this.description="Strikes True";
		this.target=TargetType.SINGLE_TARGET;
		this.damageType=DamageType.LIGHT;
		this.effects=of(
				new Effect[] {
					new Effect(EffectType.STATUS_INFLICTION,2,10,StatusInfliction.CLEAR,null)});
		this.multipliers=of(
				new Multiplier[] {
					new Multiplier(Proficiency.FAITH,0.8)});
		this.power=45;
		this.distance=1;
		this.accuracy=95;
		this.actionCost=1;
		this.manaCost=30;
	}
}
