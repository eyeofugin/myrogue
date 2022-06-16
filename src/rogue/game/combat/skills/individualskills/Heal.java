package rogue.game.combat.skills.individualskills;

import rogue.game.combat.skills.Skill;
import rogue.game.combat.skills.SkillLibrary;

public class Heal extends Skill{
	public Heal() {
		super(SkillLibrary.TEND_THE_GARDEN);
		this.name="Heal";
		this.description="Heal yourself";
		this.target=TargetType.SELF;
		this.damageType=DamageType.HEAL;
		this.power=50;
		this.accuracy=100;
		this.manaCost=35;
		this.actionCost=1;
	}	
}
