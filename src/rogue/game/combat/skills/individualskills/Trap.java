package rogue.game.combat.skills.individualskills;

import rogue.framework.resources.Resources;
import rogue.game.combat.skills.Skill;
import rogue.game.combat.skills.SkillLibrary;

public class Trap extends Skill{
	public Trap() {
		super(SkillLibrary.RUNE_TRAP);
		this.name="Trap";
		this.description="Plants a Trap";
		this.target=TargetType.SINGLE_FREE;
		this.accuracy=100;
		this.distance=1;
		this.manaCost=20;
		this.actionCost=1;
		this.enhancementId=Resources.RUNE_TRAP;
	}
}
