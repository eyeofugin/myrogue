package rogue.game.combat.skills.individualskills;

import rogue.game.combat.skills.Skill;
import rogue.game.combat.skills.SkillLibrary;

public class Flying extends Skill{
	public Flying() {
		super(SkillLibrary.ALL_TERRAIN);
		this.name="Flying";
		this.description="Can move on high terrain";
		this.isPassive=true;
	}	
}
