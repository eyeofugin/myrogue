package rogue.game.combat.skills.individualskills;

import rogue.game.combat.skills.Skill;
import rogue.game.combat.skills.SkillLibrary;

public class Hexproof extends Skill{
	public Hexproof() {
		super(SkillLibrary.HEXPROOF);
		this.name="Hexproof";
		this.description="Can not be a Target";
		this.isPassive=true;
	}
}
