package rogue.game.combat.skills.individualskills;

import rogue.game.combat.skills.Skill;
import rogue.game.combat.skills.SkillLibrary;

public class Tarning extends Skill{
	public Tarning() {
		super();
		this.id=SkillLibrary.TARNING;
		this.name="Tarning";
		this.description="Is invisible to all but close Enemies";
	}
}
