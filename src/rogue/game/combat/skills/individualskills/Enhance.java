package rogue.game.combat.skills.individualskills;

import rogue.game.combat.skills.Skill;
import rogue.game.combat.skills.Skill.Effect.EffectType;
import rogue.game.combat.skills.Skill.Effect.StatChange;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.world.objects.entities.Entity.Proficiency;

public class Enhance extends Skill{

	public Enhance() {
		super();
		this.id=SkillLibrary.EQUIPMENT_UPGRADE;
		this.name="Enhance";
		this.description="Enhance your Allies Stats";
		this.target=TargetType.SINGLE_TARGET;
		this.effects=of(new Effect[] {
				new Effect(EffectType.STAT_CHANGE,2,0,null,new StatChange(null, 0.0, Proficiency.STRENGTH, 20)),
				new Effect(EffectType.STAT_CHANGE,2,0,null,new StatChange(null, 0.0, Proficiency.FAITH, 20)),
				new Effect(EffectType.STAT_CHANGE,2,0,null,new StatChange(null, 0.0, Proficiency.KNOWLEDGE, 20)),
				new Effect(EffectType.STAT_CHANGE,2,0,null,new StatChange(null, 0.0, Proficiency.LETHALITY, 20)),
				new Effect(EffectType.STAT_CHANGE,2,0,null,new StatChange(null, 0.0, Proficiency.PRECISION, 20))
		});
		this.distance=1;
		this.accuracy=100;
		this.manaCost=30;
		this.actionCost=1;
	}
}
