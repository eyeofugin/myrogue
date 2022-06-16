package rogue.game.combat.skills.individualskills;

import rogue.game.combat.skills.SkillLibrary;
import rogue.game.combat.skills.Skill;
import rogue.game.combat.skills.Skill.Effect.EffectType;
import rogue.game.combat.skills.Skill.Effect.StatusInfliction;
import rogue.game.world.objects.entities.Entity.Proficiency;

public class Maul extends Skill{
	public Maul() {
		super(SkillLibrary.MAUL);
		this.name="Maul";
		this.description="Mauls target fith Claws";
		this.target=TargetType.SINGLE_TARGET;
		this.damageType=DamageType.NORMAL;
		this.effects=of(
				new Effect[] {
					new Effect(EffectType.STATUS_INFLICTION,2,10,StatusInfliction.BLEEDING,null)});
		this.multipliers=of(
				new Multiplier[] {
					new Multiplier(Proficiency.STRENGTH,0.8)});
		this.distance=1;
		this.accuracy=90;
		this.actionCost=1;
		this.manaCost=20;
		this.power=40;
	}
}
