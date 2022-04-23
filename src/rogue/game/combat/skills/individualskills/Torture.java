package rogue.game.combat.skills.individualskills;

import rogue.game.combat.skills.Skill;
import rogue.game.combat.skills.Skill.Effect.EffectType;
import rogue.game.combat.skills.Skill.Effect.StatusInfliction;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.world.objects.entities.Entity.Proficiency;

public class Torture extends Skill{
	public Torture() {
		super();
		this.id=SkillLibrary.CRUCIO;
		this.name="Torture Spell";
		this.description="Tortures Enemy";
		this.target=TargetType.SINGLE_TARGET;
		this.damageType=DamageType.MAGICAL;
		this.effects=of(new Effect[] {
				new Effect(EffectType.STATUS_INFLICTION,1,10,StatusInfliction.BLEEDING,null),
				new Effect(EffectType.STATUS_INFLICTION,3,0,StatusInfliction.CURSED,null)
		});
		this.multipliers=of(new Multiplier[] {
				new Multiplier(Proficiency.KNOWLEDGE,0.3)
		});
		this.power=20;
		this.accuracy=80;
		this.distance=3;
		this.manaCost=20;
		this.actionCost=1;
	}
}
