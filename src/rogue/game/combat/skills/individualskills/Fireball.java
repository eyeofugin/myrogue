package rogue.game.combat.skills.individualskills;

import rogue.game.combat.skills.Skill;
import rogue.game.combat.skills.Skill.Multiplier;
import rogue.game.combat.skills.Skill.Effect.EffectType;
import rogue.game.combat.skills.Skill.Effect.StatusInfliction;
import rogue.game.world.objects.entities.Entity.Proficiency;
import rogue.game.combat.skills.SkillLibrary;

public class Fireball extends Skill{

	public Fireball() {
		super(SkillLibrary.FIREBALL);
		this.name="Fireball";
		this.description="Hurls Fireball at first Target";
		this.target=TargetType.LINE;
		this.damageType=DamageType.BURNING;
		this.damageType=DamageType.BURNING;
		this.effects=of(new Effect[] {
				new Effect(EffectType.STATUS_INFLICTION, 2, 5, StatusInfliction.BURNING, null)});
		this.multipliers=of(
				new Multiplier[] {
					new Multiplier(Proficiency.KNOWLEDGE,0.4)});
		this.power=20;
		this.accuracy=80;
		this.distance=3;
		this.manaCost=30;
		this.actionCost=1;
	}
}
