package rogue.game.combat.skills.individualskills;

import rogue.game.combat.skills.Skill;
import rogue.game.combat.skills.Skill.Effect.EffectType;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.world.objects.entities.Entity.Proficiency;

public class ForcePush extends Skill{
	public ForcePush() {
		super();
		this.id=SkillLibrary.FORCE_PUSH;
		this.name="Force Push";
		this.description="Pushes Enemies";
		this.target=TargetType.SURROUNDING;
		this.damageType=DamageType.MAGICAL;
		this.effects=of(new Effect[] {
				new Effect(EffectType.OBJECT_PUSH,0,1,null,null)
		});
		this.multipliers=of(new Multiplier[] {
				new Multiplier(Proficiency.FAITH,0.3)
		});
		this.power=20;
		this.accuracy=100;
		this.distance=1;
		this.manaCost=20;
		this.actionCost=1;
	}
}
