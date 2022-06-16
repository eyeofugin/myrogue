package rogue.game.combat.skills.individualskills;

import rogue.game.combat.skills.Skill;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.world.objects.entities.Entity.Proficiency;

public class Barrage extends Skill{
	public Barrage() {
		super(SkillLibrary.ARROW_BARRAGE);
		this.name="Barrage";
		this.description="Fires on Area";
		this.target=TargetType.SINGLE_TARGET;
		this.damageType=DamageType.NORMAL;
		this.multipliers=of(new Multiplier[] {
				new Multiplier(Proficiency.PRECISION,0.8)
		});
		this.power=25;
		this.accuracy=85;
		this.distance=4;
		this.radius=1;
		this.manaCost=30;
		this.actionCost=2;
	}	
}
