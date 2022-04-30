package rogue.game.combat.skills.individualskills;

import rogue.framework.resources.Resources;
import rogue.game.combat.skills.Skill;
import rogue.game.combat.skills.SkillLibrary;

public class SummonZombie extends Skill{
	
	public SummonZombie() {
		super(SkillLibrary.ZOMBIE_MINIONS);
		this.name="Summon the Undead";
		this.description="Summons a Zombie";
		this.distance=1;
		this.manaCost=30;
		this.actionCost=2;
		this.summonedId=Resources.ZOMBIE;
	}
}
