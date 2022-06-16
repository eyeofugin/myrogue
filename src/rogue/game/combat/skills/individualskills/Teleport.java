package rogue.game.combat.skills.individualskills;

import rogue.game.combat.skills.Skill;
import rogue.game.combat.skills.SkillLibrary;

public class Teleport extends Skill{

	public Teleport() {
		super(SkillLibrary.TELEPORT);
		this.name="Teleport";
		this.description="Bends Spacetime around them";
		this.target=TargetType.SINGLE_FREE;
		this.type=SkillType.MOVEMENT;
		this.distance=6;
		this.manaCost=35;
		this.actionCost=1;
	}
}
