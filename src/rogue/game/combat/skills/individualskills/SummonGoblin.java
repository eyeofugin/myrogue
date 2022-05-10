package rogue.game.combat.skills.individualskills;

import rogue.framework.resources.Resources;
import rogue.game.combat.skills.Skill;
import rogue.game.combat.skills.SkillLibrary;

public class SummonGoblin extends Skill{
	public SummonGoblin() {
		super(SkillLibrary.SUMMON_GOBLIN);
		this.name="Summon Goblin";
		this.description="Summons a Goblin";
		this.target=TargetType.SINGLE_FREE;
		this.distance=1;
		this.manaCost=30;
		this.actionCost=2;
		this.summonedId=Resources.GOBLINNPC;
	}
}
