package rogue.game.combat.skills.individualskills;

import rogue.game.combat.skills.Skill;
import rogue.game.combat.skills.SkillLibrary;

public class Cerebro extends Skill{
	public Cerebro() {
		super(SkillLibrary.CEREBRO);
		this.name="Charles Cerebro";
		this.description="Reveals all Enemies";
		this.target=TargetType.ALL_ENEMY;
		this.type=SkillType.VISION;
		this.manaCost=30;
		this.actionCost=1;
	}
}
