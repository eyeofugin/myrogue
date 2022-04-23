package rogue.game.combat.skills.individualskills;

import rogue.game.combat.skills.Skill;
import rogue.game.combat.skills.Skill.Effect.EffectType;
import rogue.game.combat.skills.Skill.Effect.StatusInfliction;
import rogue.game.combat.skills.SkillLibrary;

public class Explosion extends Skill{
	public Explosion() {
		super();
		this.id=SkillLibrary.ROCKET;
		this.name="Explosion";
		this.description="Create a large Explosion";
		this.target=TargetType.SINGLE_TARGET;
		this.damageType=DamageType.NORMAL;
		this.effects=of(new Effect[] {
				new Effect(EffectType.TERRAIN_ENHANCEMENT,0,1,StatusInfliction.REMOVE_OBSTACLE,null),
				new Effect(EffectType.STATUS_INFLICTION,1,0,StatusInfliction.STUNNED,null)});
		this.power=30;
		this.accuracy=75;
		this.distance=3;
		this.radius=1;
		this.manaCost=20;
		this.actionCost=1;
	}
}
