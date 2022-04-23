package rogue.game.combat.skills.individualskills;

import rogue.game.combat.skills.Skill;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.world.objects.entities.Entity.Proficiency;

public class Barrage extends Skill{
	public Barrage() {
		super();
		this.id=SkillLibrary.ARROW_BARRAGE;
		this.name="Barrage";
		this.description="Fires on Area";
		this.target=TargetType.SINGLE_TARGET;
		this.damageType=DamageType.NORMAL;
		this.multipliers=of(new Multiplier[] {
				new Multiplier(Proficiency.PRECISION,0.8)
		});
		this.power=20;
		this.accuracy=100;
		this.distance=3;
		this.radius=1;
		this.manaCost=20;
		this.actionCost=1;
	}	
}
