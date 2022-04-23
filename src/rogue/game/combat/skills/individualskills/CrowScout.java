package rogue.game.combat.skills.individualskills;

import rogue.game.combat.skills.Skill;
import rogue.game.combat.skills.SkillLibrary;

public class CrowScout extends Skill{
	public CrowScout() {
		super();
		this.id=SkillLibrary.CROW_SCOUT;
		this.name="Pet Scout";
		this.description="Pet Scout reveals Area";
		this.target=TargetType.SINGLE_TARGET;
		this.type=SkillType.VISION;
		this.distance=5;
		this.radius=1;
		this.manaCost=30;
		this.actionCost=1;
	}
}
