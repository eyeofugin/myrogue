package rogue.game.combat.skills.individualskills;

import rogue.game.combat.skills.Skill;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.combat.skills.Skill.SkillType;

public class Grapple extends Skill{

	public Grapple() {
		super(SkillLibrary.GRAPPLING_HOOK);
		this.name="Grapple";
		this.description="Pull towards Point";
		this.target=TargetType.SINGLE_FREE;
		this.type=SkillType.MOVEMENT;
		this.type=SkillType.MOVEMENT;
		this.distance=3;
		this.manaCost=20;
		this.actionCost=2;
	}	
}
