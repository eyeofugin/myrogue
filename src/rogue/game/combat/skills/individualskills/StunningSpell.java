package rogue.game.combat.skills.individualskills;

import rogue.game.combat.skills.Skill;
import rogue.game.combat.skills.Skill.Effect.EffectType;
import rogue.game.combat.skills.Skill.Effect.StatusInfliction;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.world.objects.entities.Entity.Proficiency;

public class StunningSpell extends Skill{
	public StunningSpell() {
		super(SkillLibrary.STUPOR);
		this.name="Stunning Spell";
		this.description="Casts a Spell that stuns the first Target";
		this.target=TargetType.LINE;
		this.damageType=DamageType.MAGICAL;
		this.effects=of(new Effect[] {
				new Effect(EffectType.STATUS_INFLICTION,1,0,StatusInfliction.STUNNED,null)
		});
		this.multipliers=of(new Multiplier[] {
				new Multiplier(Proficiency.KNOWLEDGE,0.3),
				new Multiplier(Proficiency.PRECISION,0.3)
		});
		this.power=20;
		this.accuracy=80;
		this.distance=3;
		this.manaCost=20;
		this.actionCost=1;
	}
}
