package rogue.game.combat.skills.individualskills;

import rogue.game.combat.skills.Skill;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.world.objects.entities.Entity.Proficiency;

public class AreaFire extends Skill{
	public AreaFire() {
		super(SkillLibrary.AREAFIRE);
		this.name="Area Fire";
		this.description="Engulfs Area in Flames";
		this.target=TargetType.SINGLE_TARGET;
		this.damageType=DamageType.BURNING;
		this.multipliers=of(new Multiplier[] {
				new Multiplier(Proficiency.FAITH,0.8)
		});
		this.power=20;
		this.accuracy=100;
		this.distance=3;
		this.radius=1;
		this.manaCost=20;
		this.actionCost=1;
//		this.summonedId
	}	
}
