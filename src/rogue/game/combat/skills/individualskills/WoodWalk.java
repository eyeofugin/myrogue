package rogue.game.combat.skills.individualskills;

import rogue.game.combat.skills.Skill;
import rogue.game.combat.skills.SkillLibrary;

public class WoodWalk extends Skill{

	public WoodWalk() {
		super();
		this.id=SkillLibrary.WOOD_WALK;
		this.name="Wood Walk";
		this.description="Moves fast on tall grass";
	}
}
