package rogue.game.combat.skills.individualskills;

import rogue.game.combat.skills.Skill;
import rogue.game.combat.skills.Skill.Effect.EffectType;
import rogue.game.combat.skills.Skill.Effect.StatusInfliction;
import rogue.game.combat.skills.SkillLibrary;

public class SacrificialCurse extends Skill{
	public SacrificialCurse() {
		super(SkillLibrary.SACRIFICIAL_CURSE);
		this.name="Sacrificial Curse";
		this.description="Sacrifice to Curse Foe";
		this.target=TargetType.SINGLE_TARGET;
		this.effects=of(
				new Effect[] {
					new Effect(EffectType.STATUS_INFLICTION,0,0,StatusInfliction.CLEAR,null),
					new Effect(EffectType.STATUS_INFLICTION,2,10,StatusInfliction.CURSED,null)});
		this.distance=1;
		this.accuracy=80;
		this.actionCost=2;
		this.manaCost=20;
		this.lifeCost=10;
	}
}
