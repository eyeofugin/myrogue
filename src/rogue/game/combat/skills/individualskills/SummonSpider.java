package rogue.game.combat.skills.individualskills;

import rogue.framework.resources.Resources;
import rogue.game.combat.skills.Skill;
import rogue.game.combat.skills.SkillLibrary;

public class SummonSpider extends Skill{
	public SummonSpider() {
		super();
		this.id = SkillLibrary.ARAGOG;
		this.name="Summon Spiderling";
		this.description="Summons a Spider";
		this.distance=1;
		this.manaCost=30;
		this.actionCost=2;
		this.summonedId=Resources.ARAGOG;
	}
}
