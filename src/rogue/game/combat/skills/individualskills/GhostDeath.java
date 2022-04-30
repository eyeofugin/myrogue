package rogue.game.combat.skills.individualskills;

import rogue.framework.resources.Resources;
import rogue.game.combat.skills.Skill;
import rogue.game.combat.skills.SkillLibrary;

public class GhostDeath extends Skill{
	public GhostDeath() {
		super(SkillLibrary.GHOST_DEATH);
		this.name="Unholy Remains";
		this.description="Spawn a Ghost on death";
		this.isPassive=true;
		this.onDeath=true;
		this.summonedId=Resources.GHOSTNPC;
	}
}
