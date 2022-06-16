package rogue.game.combat.skills.individualskills;

import rogue.framework.resources.Resources;
import rogue.game.combat.skills.Skill;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.combat.skills.Skill.TargetType;

public class GrowGrass extends Skill{
	public GrowGrass() {
		super(SkillLibrary.TALL_GRASS);
		this.name="Grow Grass";
		this.description="Grows Tall Grass";
		this.target=TargetType.SINGLE_FREE;
		this.accuracy=100;
		this.distance=2;
		this.radius=1;
		this.manaCost=35;
		this.actionCost=1;
		this.enhancementId=Resources.TALLGRASS;
	}
}
