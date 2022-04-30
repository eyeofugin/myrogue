package rogue.game.combat.skills.individualskills;

import rogue.game.combat.skills.Skill;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.combat.skills.Skill.DamageType;
import rogue.game.combat.skills.Skill.Effect;
import rogue.game.combat.skills.Skill.Multiplier;
import rogue.game.combat.skills.Skill.TargetType;
import rogue.game.combat.skills.Skill.Effect.EffectType;
import rogue.game.combat.skills.Skill.Effect.StatusInfliction;
import rogue.game.world.objects.entities.Entity.Proficiency;

public class WindWall extends Skill{
	public WindWall() {
		super(SkillLibrary.WIND_WALL);
		this.name="Wind Wall";
		this.description="Push against the Air";
		this.target=TargetType.SINGLE_TARGET;
		this.damageType=DamageType.MAGICAL;
		this.effects=of(new Effect[] {
				new Effect(EffectType.OBJECT_PUSH,0,1,null,null)
		});
		this.multipliers=of(new Multiplier[] {
				new Multiplier(Proficiency.KNOWLEDGE,0.3)
		});
		this.power=20;
		this.accuracy=95;
		this.distance=1;
		this.manaCost=20;
		this.actionCost=1;
	}
}
