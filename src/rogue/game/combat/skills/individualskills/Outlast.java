package rogue.game.combat.skills.individualskills;

import rogue.game.combat.skills.Skill;
import rogue.game.combat.skills.Skill.Effect.EffectType;
import rogue.game.combat.skills.Skill.Effect.StatChange;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.world.objects.entities.Entity.Proficiency;

public class Outlast extends Skill{
	public Outlast() {
		super(SkillLibrary.OUTLAST);
		this.name="Outlast";
		this.description="Strengthens themself";
		this.target=TargetType.SELF;
		this.effects=of(new Effect[] {
				new Effect(EffectType.STAT_CHANGE,-1,0,null,new StatChange(null, 0.0, Proficiency.STRENGTH, 20))
		});
		this.distance=1;
		this.accuracy=80;
		this.actionCost=2;
		this.manaCost=20;
		this.lifeCost=10;
	}
}