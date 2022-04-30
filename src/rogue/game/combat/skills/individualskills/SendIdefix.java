package rogue.game.combat.skills.individualskills;

import rogue.game.combat.skills.Skill;
import rogue.game.combat.skills.SkillLibrary;

public class SendIdefix extends Skill{
	public SendIdefix() {
		super(SkillLibrary.SEND_IDEFIX);
		this.name="Pet Scout";
		this.description="Pet Scout reveals Area";
		this.target=TargetType.LINE;
		this.type=SkillType.VISION;
		this.distance=5;
		this.radius=0;
		this.manaCost=30;
		this.actionCost=1;
	}
}
