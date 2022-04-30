package rogue.game.combat.skills.individualskills;

import rogue.game.combat.skills.Skill;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.combat.skills.Skill.SkillType;
import rogue.game.combat.skills.Skill.TargetType;

public class ShowArea extends Skill{

	public ShowArea() {
		super(SkillLibrary.SHOW_AREA);
		this.name="Show Area";
		this.description="Reveals an Area";
		this.target=TargetType.SINGLE_TARGET;
		this.type=SkillType.VISION;
		this.distance=5;
		this.radius=1;
		this.manaCost=30;
		this.actionCost=1;
	}
}
