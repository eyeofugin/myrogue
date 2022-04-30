package rogue.game.combat.skills.individualskills;

import rogue.game.combat.skills.Skill;
import rogue.game.combat.skills.SkillLibrary;

public class AllyHeal extends Skill{
	public AllyHeal() {
		super(SkillLibrary.ALLY_HEAL);
		this.name="Ally Heal";
		this.description="Heals an Ally";
		this.target=TargetType.SINGLE_TARGET;
		this.damageType=DamageType.HEAL;
		this.power=20;
		this.distance=2;
		this.manaCost=20;
		this.actionCost=1;
	}
}
