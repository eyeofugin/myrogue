package rogue.game.combat.skills.individualskills;

import rogue.game.combat.skills.Skill;
import rogue.game.combat.skills.SkillLibrary;

public class Resistant extends Skill{
	public Resistant() {
		super();
		this.id=SkillLibrary.FELL_IN_THE_POT;
		this.name="Resistant";
		this.description="Cant receive Buffs or Debuffs";
	}
}
