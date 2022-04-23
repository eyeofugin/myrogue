package rogue.game.combat.skills.individualskills;

import rogue.game.combat.skills.Skill;
import rogue.game.combat.skills.SkillLibrary;

public class Grapple extends Skill{

	public Grapple() {
		super();
		this.id=SkillLibrary.GRAPPLING_HOOK;
		this.name="Grapple";
		this.description="Pull towards Point";
		this.target=TargetType.SINGLE_FREE;
		this.type=SkillType.MOVEMENT;
		this.distance=2;
		this.manaCost=20;
		this.actionCost=1;
	}	
}
