package rogue.game.combat.skills.individualskills;

import rogue.framework.resources.Resources;
import rogue.game.combat.skills.Skill;
import rogue.game.combat.skills.SkillLibrary;

public class EnhancementField extends Skill{
	public EnhancementField() {
		super(SkillLibrary.ENHANCEMENT_RUNE);
		this.name="Enhancement Field";
		this.description="Prepare a Field to Enhance your Allies";
		this.target=TargetType.SINGLE_FREE;
		this.accuracy=100;
		this.distance=1;
		this.manaCost=40;
		this.actionCost=2;
		this.enhancementId=Resources.ENHANCEMENT_RUNE;
	}
}
