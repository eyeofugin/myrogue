package rogue.game.combat.skills.individualskills;

import rogue.game.combat.skills.Skill;
import rogue.game.combat.skills.SkillLibrary;

public class Flying extends Skill{
	public Flying() {
		super();
		this.id=SkillLibrary.ALL_TERRAIN;
		this.name="Flying";
		this.description="Can pass several more Objects";
	}	
}
