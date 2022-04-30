package rogue.game.combat.skills.individualskills;

import rogue.framework.resources.Resources;
import rogue.game.combat.skills.Skill;
import rogue.game.combat.skills.SkillLibrary;

public class PlantTree extends Skill{
	public PlantTree() {
		super(SkillLibrary.PLANT);
		this.name="Plant Tree";
		this.description="Plants a Tree";
		this.target=TargetType.SINGLE_FREE;
		this.accuracy=100;
		this.distance=1;
		this.manaCost=20;
		this.actionCost=1;
		this.enhancementId=Resources.TREE;
	}	
}
