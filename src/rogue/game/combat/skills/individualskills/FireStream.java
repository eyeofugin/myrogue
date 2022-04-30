package rogue.game.combat.skills.individualskills;

import rogue.game.combat.skills.Skill;
import rogue.game.combat.skills.Skill.Effect.EffectType;
import rogue.game.combat.skills.Skill.Effect.StatusInfliction;
import rogue.game.combat.skills.SkillLibrary;

public class FireStream extends Skill{
	public FireStream() {
		super(SkillLibrary.FLAMETHROWER);
		this.name="Fire Stream";
		this.description="A Stream of Fire";
		this.target=TargetType.LINE_PIERCING;
		this.damageType=DamageType.BURNING;
		this.effects=of(new Effect[] {
				new Effect(EffectType.STATUS_INFLICTION, 2, 5, StatusInfliction.BURNING, null),
				new Effect(EffectType.TERRAIN_ENHANCEMENT,2, 0, StatusInfliction.BURNING,null)}
				);
		this.power=30;
		this.accuracy=80;
		this.distance=3;
		this.manaCost=20;
		this.actionCost=1;
	}
}
