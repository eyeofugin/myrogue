package rogue.game.combat.skills.individualskills;

import rogue.game.combat.skills.Skill;
import rogue.game.combat.skills.Skill.Effect.EffectType;
import rogue.game.combat.skills.Skill.Effect.StatChange;
import rogue.game.combat.skills.Skill.Effect.StatusInfliction;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.world.objects.entities.Entity.Proficiency;

public class ForceChoke extends Skill{
	public ForceChoke() {
		super(SkillLibrary.FORCE_CHOKE);
		this.name="Vaders Force Choke";
		this.description="Chokes Foe and pulls them";
		this.target=TargetType.SINGLE_TARGET;
		this.damageType=DamageType.DARK;
		this.effects=of(new Effect[] {
				new Effect(EffectType.STATUS_INFLICTION,1,0,StatusInfliction.STUNNED,null),
				new Effect(EffectType.STAT_CHANGE,1,0,null,new StatChange(
						DamageType.DARK,0.8,null,0)),
				new Effect(EffectType.STAT_CHANGE,1,0,null,new StatChange(
						DamageType.BURNING,0.8,null,0)),
				new Effect(EffectType.STAT_CHANGE,1,0,null,new StatChange(
						DamageType.SHOCK,0.8,null,0)),
				new Effect(EffectType.STAT_CHANGE,1,0,null,new StatChange(
						DamageType.NORMAL,0.8,null,0)),
				new Effect(EffectType.OBJECT_PULL,0,1,null,null)
		});
		this.multipliers=of(new Multiplier[] {
				new Multiplier(Proficiency.FAITH,0.3)
		});
		this.power=20;
		this.distance=3;
		this.manaCost=20;
		this.actionCost=1;
	}
}
