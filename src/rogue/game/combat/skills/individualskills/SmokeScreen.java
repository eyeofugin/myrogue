package rogue.game.combat.skills.individualskills;

import rogue.framework.resources.Resources;
import rogue.game.combat.skills.Skill;
import rogue.game.combat.skills.SkillLibrary;

public class SmokeScreen extends Skill{

	public SmokeScreen() {
		super(SkillLibrary.SMOKE_SCREEN);
		this.name="Smoke Screen";
		this.description="Area is covered by thick smoke";
		this.target=TargetType.SINGLE_TARGET;
		this.distance=3;
		this.radius=1;
		this.manaCost=20;
		this.actionCost=1;
		this.enhancementId=Resources.SMOKE_SCREEN;
	}	
}
