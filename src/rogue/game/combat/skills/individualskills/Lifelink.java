package rogue.game.combat.skills.individualskills;

import rogue.game.combat.skills.Skill;
import rogue.game.combat.skills.SkillLibrary;

public class Lifelink extends Skill{
	public Lifelink() {
		super(SkillLibrary.LIFELINK);
		this.name="Lifelink";
		this.description="Gain half of your damage dealt as health";
		this.isPassive=true;
	}
}
