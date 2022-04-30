package rogue.game.combat.skills.individualskills;

import rogue.game.combat.skills.Skill;
import rogue.game.combat.skills.SkillLibrary;

public class TrueVision extends Skill{
	public TrueVision() {
		super(SkillLibrary.TRUE_VISION);
		this.name="True Vision";
		this.description="They see it all";
		this.isPassive=true;
	}
}
