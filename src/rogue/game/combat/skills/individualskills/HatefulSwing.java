package rogue.game.combat.skills.individualskills;

import rogue.game.combat.skills.Skill;
import rogue.game.combat.skills.Skill.Effect.EffectType;
import rogue.game.combat.skills.Skill.Effect.StatusInfliction;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.world.objects.entities.Entity.Proficiency;

public class HatefulSwing extends Skill{
	public HatefulSwing() {
		super(SkillLibrary.HATEFUL_SWING);
		this.name="Hateful Swing";
		this.description="Perform a rage infused Swing";
		this.target=TargetType.SINGLE_TARGET;
		this.damageType=DamageType.DARK;
		this.effects=of(
				new Effect[] {
					new Effect(EffectType.STATUS_INFLICTION,2,10,StatusInfliction.CURSED,null)});
		this.multipliers=of(
				new Multiplier[] {
					new Multiplier(Proficiency.FAITH,0.8)});
		this.distance=1;
		this.accuracy=80;
		this.actionCost=2;
		this.manaCost=20;
		this.lifeCost=10;
	}
}
