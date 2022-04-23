package rogue.game.combat.skills.individualskills;

import rogue.game.combat.skills.Skill;
import rogue.game.combat.skills.Skill.Effect.EffectType;
import rogue.game.combat.skills.Skill.Effect.StatusInfliction;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.world.objects.entities.Entity.Proficiency;

public class FireRing extends Skill{

	public FireRing() {
		super();
		this.id=SkillLibrary.FIRE_RING;
		this.name="Fire Ring";
		this.description="Cast Flames Surrounding them";
		this.target=TargetType.SURROUNDING;
		this.damageType=DamageType.BURNING;
		this.effects=of(new Effect[] {
				new Effect(EffectType.STATUS_INFLICTION,1,5,StatusInfliction.BURNING,null)
		});
		this.multipliers=of(new Multiplier[] {
				new Multiplier(Proficiency.KNOWLEDGE,0.3)
		});
		this.power=20;
		this.accuracy=80;
		this.distance=1;
		this.manaCost=20;
		this.actionCost=1;
	}
}
