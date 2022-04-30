package rogue.game.combat.skills.individualskills;

import rogue.game.combat.skills.Skill;
import rogue.game.combat.skills.SkillLibrary;

public class Behead extends Skill{
	public Behead() {
		super(SkillLibrary.BEHEAD);
		this.name="Behead";
		this.description="Inflict great Damage on weakened Foes";
		this.isPassive=true;
	}
}
