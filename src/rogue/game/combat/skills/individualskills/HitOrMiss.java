package rogue.game.combat.skills.individualskills;

import rogue.game.combat.skills.Skill;
import rogue.game.combat.skills.SkillLibrary;

public class HitOrMiss extends Skill{
	public HitOrMiss() {
		super(SkillLibrary.HIT_OR_MISS);
		this.name="Hit or Miss";
		this.description="Normal Attacks might miss";
		this.isPassive=true;
	}
}
