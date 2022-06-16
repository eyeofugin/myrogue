package rogue.game.combat.skills.individualskills;

import rogue.framework.resources.Resources;
import rogue.game.combat.skills.Skill;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.combat.skills.Skill.TargetType;

public class SummonSpider extends Skill{
	public SummonSpider() {
		super(SkillLibrary.ARAGOG);
		this.name="Summon Spiderling";
		this.description="Summons a Spider";
		this.target=TargetType.SINGLE_FREE;
		this.distance=1;
		this.manaCost=25;
		this.actionCost=2;
		this.summonedId=Resources.ARAGOG;
	}
}
