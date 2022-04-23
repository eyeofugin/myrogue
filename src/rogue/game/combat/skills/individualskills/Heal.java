package rogue.game.combat.skills.individualskills;

import rogue.game.combat.skills.Skill;
import rogue.game.combat.skills.SkillLibrary;

public class Heal extends Skill{
	public Heal() {
		super();
		this.id=SkillLibrary.TEND_THE_GARDEN;
		this.name="Heal";
		this.description="Heal yourself";
		this.target=TargetType.SELF;
		this.damageType=DamageType.HEAL;
		this.power=20;
		this.accuracy=100;
		this.manaCost=20;
		this.actionCost=1;
	}	
}
