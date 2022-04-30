package rogue.game.combat.skills.individualskills;

import rogue.game.combat.skills.Skill;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.world.objects.entities.Entity.Proficiency;

public class FastShots extends Skill{
	public FastShots() {
		super(SkillLibrary.EMPTY_REVOLVER);
		this.name="Fast Shots";
		this.description="Shoots in quick Succession";
		this.target=TargetType.LINE;
		this.damageType=DamageType.NORMAL;
		this.multipliers=of(new Multiplier[] {
				new Multiplier(Proficiency.PRECISION,0.3)
		});
		this.power=20;
		this.accuracy=80;
		this.distance=3;
		this.manaCost=20;
		this.actionCost=1;
	}
}
