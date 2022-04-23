package rogue.game.combat.skills.individualskills;

import rogue.game.combat.skills.Skill;
import rogue.game.combat.skills.SkillLibrary;

public class DuelToTheDeath extends Skill{
	public DuelToTheDeath() {
		super();
		this.id=SkillLibrary.DUEL_TO_THE_DEATH;
		this.name="Duel to the Death";
		this.description="Duels with an enemy and only one can survive";
		this.target=TargetType.SINGLE_TARGET;
		this.distance=1;
		this.manaCost=20;
		this.actionCost=1;
	}
}