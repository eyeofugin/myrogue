package rogue.game.combat.skills.individualskills;

import rogue.game.combat.skills.Skill;
import rogue.game.combat.skills.Skill.Effect.EffectType;
import rogue.game.combat.skills.Skill.Effect.StatusInfliction;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.world.objects.entities.Entity.Proficiency;

public class FlameWhip extends Skill{

	public FlameWhip() {
		super(SkillLibrary.FLAME_WHIP);
		this.name="Flame Whip";
		this.description="A Flaming Whip strikes your foes.";
		this.target=TargetType.LINE_PIERCING;
		this.damageType=DamageType.BURNING;
		this.effects=of(new Effect[] {
				new Effect(EffectType.STATUS_INFLICTION,1,0,StatusInfliction.STUNNED,null)
		});
		this.multipliers=of(new Multiplier[] {
				new Multiplier(Proficiency.STRENGTH,0.5)
		});
		this.power=45;
		this.accuracy=80;
		this.distance=3;
		this.manaCost=30;
		this.actionCost=1;
	}	
}
