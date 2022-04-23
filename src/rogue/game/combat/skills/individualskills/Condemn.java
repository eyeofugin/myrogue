package rogue.game.combat.skills.individualskills;

import rogue.game.combat.skills.Skill;
import rogue.game.combat.skills.Skill.Effect.EffectType;
import rogue.game.combat.skills.Skill.Effect.StatusInfliction;
import rogue.game.combat.skills.SkillLibrary;

public class Condemn extends Skill{
	public Condemn() {
		super();
		this.id=SkillLibrary.CONDEMN;
		this.name="Condemn";
		this.description="Condemns a Character";
		this.target=TargetType.SINGLE_TARGET;
		this.effects=of(
				new Effect[] {
					new Effect(EffectType.STATUS_INFLICTION,0,0,StatusInfliction.CLEAR,null),
					new Effect(EffectType.STATUS_INFLICTION,3,0,StatusInfliction.CURSED,null)});
		this.distance=3;
		this.manaCost=30;
		this.actionCost=1;
	}
}
