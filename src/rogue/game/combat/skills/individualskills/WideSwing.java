package rogue.game.combat.skills.individualskills;

import rogue.game.combat.skills.Skill;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.world.objects.entities.Entity.Proficiency;

public class WideSwing extends Skill{
	public WideSwing() {
		super(SkillLibrary.WEAPON_SWING);
		this.name="Wide Swing";
		this.description="Hurls Weapon around";
		this.target=TargetType.SURROUNDING;
		this.damageType=DamageType.NORMAL;
		this.multipliers=of(new Multiplier[] {
				new Multiplier(Proficiency.STRENGTH,0.3) 
		});
		this.power=20;
		this.accuracy=80;
		this.distance=1;
		this.manaCost=20;
		this.actionCost=1;
	}
}
