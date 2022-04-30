package rogue.game.combat.skills.individualskills;

import rogue.game.combat.skills.Skill;
import rogue.game.combat.skills.SkillLibrary;

public class Tarning extends Skill{
	public Tarning() {
		super(SkillLibrary.TARNING);
		this.name="Tarning";
		this.description="Is invisible to all but close Enemies";
		this.isPassive=true;
	}
}
