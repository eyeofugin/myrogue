package rogue.game.combat.skills.individualskills;

import rogue.game.combat.skills.Skill;
import rogue.game.combat.skills.SkillLibrary;

public class FireArmor extends Skill{
	public FireArmor() {
		super(SkillLibrary.FIRE_ARMOR);
		this.name="Balrogs Fire Armor";
		this.description="Foe may take burn damage on contact";
		this.isPassive=true;
		this.damageType=DamageType.BURNING;
		this.power=35;
		this.accuracy=75;
	}	
}
