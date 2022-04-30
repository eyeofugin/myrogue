package rogue.game.combat.skills.individualskills;

import rogue.game.combat.skills.SkillLibrary;
import rogue.game.combat.skills.Skill;

public class FrostWalk extends Skill{
	public FrostWalk() {
		super(SkillLibrary.FROSTWALK);
		this.name="Frostwalk";
		this.description="Creates Frost on Step";
		this.isPassive=true;
	}
}
