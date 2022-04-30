package rogue.game.combat.skills.individualskills;

import rogue.game.combat.skills.Skill;
import rogue.game.combat.skills.SkillLibrary;

public class Unstoppable extends Skill{
	public Unstoppable() {
		super(SkillLibrary.UNSTOPPABLE);
		this.name="Unstoppable";
		this.description="Immune to Slowing Down";
		this.isPassive=true;
	}	
}
