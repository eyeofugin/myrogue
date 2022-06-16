package rogue.game.combat.skills.individualskills;

import rogue.framework.resources.Resources;
import rogue.game.combat.skills.Skill;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.combat.skills.Skill.TargetType;

public class SummonHellspawn extends Skill{
	public SummonHellspawn() {
		super(SkillLibrary.FLUFFY);
		this.name="Summon Hellspawn";
		this.description="Summons a Hellspawn";
		this.target=TargetType.SINGLE_FREE;
		this.distance=1;
		this.manaCost=35;
		this.actionCost=2;
		this.summonedId=Resources.FLUFFY;
	}
}
