package rogue.game.combat.skills.individualskills;

import rogue.game.combat.skills.Skill;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.world.objects.entities.Entity.Proficiency;

public class WeaponThrow extends Skill{
	public WeaponThrow() {
		super(SkillLibrary.WEAPON_THROW);
		this.name="Weapon Throw";
		this.description="User throws their Weapon";
		this.target=TargetType.LINE;
		this.damageType=DamageType.NORMAL;
		this.multipliers=of(new Multiplier[] {
				new Multiplier(Proficiency.PRECISION,0.3),
				new Multiplier(Proficiency.STRENGTH,0.3)
		});
		this.power=45;
		this.accuracy=80;
		this.distance=3;
		this.manaCost=35;
		this.actionCost=2;
	}
}
