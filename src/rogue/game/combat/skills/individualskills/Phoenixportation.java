package rogue.game.combat.skills.individualskills;

import rogue.game.combat.skills.Skill;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.combat.skills.Skill.Effect;
import rogue.game.combat.skills.Skill.Effect.EffectType;
import rogue.game.world.objects.entities.Entity.Proficiency;

public class Phoenixportation extends Skill{
	public Phoenixportation() {
		super(SkillLibrary.PHOENIX_TELEPORTATION);
		this.name="Dumbledores Phoenixportation";
		this.description="Teleports and inflicts Damage";
		this.target=TargetType.SINGLE_FREE;
		this.damageType=DamageType.BURNING;
		this.multipliers=of(new Multiplier[] {
				new Multiplier(Proficiency.KNOWLEDGE,0.3)
		});
		this.type=SkillType.MOVEMENT;
		this.power=20;
		this.accuracy=100;
		this.distance=3;
		this.radius=1;
		this.manaCost=20;
		this.actionCost=1;
	}
}
