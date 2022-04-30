package rogue.game.combat.skills.individualskills;

import rogue.game.combat.skills.Skill;
import rogue.game.combat.skills.SkillLibrary;

public class Underdog extends Skill{
	public Underdog() {
		super(SkillLibrary.UNDERDOG);
		this.name="Underdog";
		this.description="Is stronger against mightier Foes";
		this.isPassive=true;
	}
}
