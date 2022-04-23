package rogue.game.combat.skills.individualskills;

import rogue.game.combat.skills.Skill;
import rogue.game.combat.skills.SkillLibrary;

public class ShadowWalk extends Skill{
	public ShadowWalk() {
		super();
		this.id=SkillLibrary.SHADOW_WALK;
		this.name="Shadow Walk";
		this.description="Glide through Shadows";
		this.target=TargetType.SINGLE_FREE;
		this.type=SkillType.MOVEMENT;
		this.distance=2;
		this.manaCost=20;
		this.actionCost=1;
	}
}
