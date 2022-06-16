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

public class FistFury extends Skill{
	public FistFury() {
		super(SkillLibrary.FIST_BARRAGE);		
		this.name="Fist Fury";
		this.description="Punches in quick Succession";
		this.target=TargetType.SINGLE_TARGET;
		this.damageType=DamageType.NORMAL;
		this.multipliers=of(new Multiplier[] {
				new Multiplier(Proficiency.STRENGTH,0.3)
		});
		this.power=50;
		this.accuracy=80;
		this.distance=1;
		this.manaCost=25;
		this.actionCost=2;
	}
}
