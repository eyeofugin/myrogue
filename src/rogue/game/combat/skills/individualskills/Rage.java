package rogue.game.combat.skills.individualskills;

import rogue.game.combat.skills.Skill;
import rogue.game.combat.skills.Skill.Effect.EffectType;
import rogue.game.combat.skills.Skill.Effect.StatChange;
import rogue.game.combat.skills.Skill.Effect.StatusInfliction;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.world.objects.entities.Entity.Proficiency;

public class Rage extends Skill{

	public Rage() {
		super();
		this.id=SkillLibrary.MERCILESS_MASSACRE;
		this.name="Rage";
		this.description="User enters a state of rage and fury";
		this.target=TargetType.SELF;
		this.effects=of(new Effect[] {
				new Effect(EffectType.STATUS_INFLICTION,
						3,
						0,
						StatusInfliction.CURSED,
						null),
				new Effect(EffectType.STAT_CHANGE,
						3,
						0,
						null,
						new StatChange(
								null,
								0,
								Proficiency.STRENGTH,
								30)
						),
				new Effect(EffectType.STAT_CHANGE,
						3,
						0,
						null,
						new StatChange(
								null,
								0,
								Proficiency.FAITH,
								20)
						)
		});
		this.manaCost=20;
		this.actionCost=1;
	}
}
