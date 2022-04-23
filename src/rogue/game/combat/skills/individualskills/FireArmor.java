package rogue.game.combat.skills.individualskills;

import rogue.game.combat.skills.Skill;
import rogue.game.combat.skills.SkillLibrary;

public class FireArmor extends Skill{
	public FireArmor() {
		super();
		this.id=SkillLibrary.FIRE_ARMOR;
		this.name="Balrogs Fire Armor";
		this.description="Foe may take burn damage on contact";
		this.damageType=DamageType.BURNING;
		this.power=20;
		this.accuracy=75;
	}	
}