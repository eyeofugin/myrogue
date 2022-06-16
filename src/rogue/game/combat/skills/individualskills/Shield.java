package rogue.game.combat.skills.individualskills;

import rogue.game.combat.skills.Skill;
import rogue.game.combat.skills.Skill.Effect.EffectType;
import rogue.game.combat.skills.Skill.Effect.StatusInfliction;
import rogue.game.combat.skills.SkillLibrary;

public class Shield extends Skill{
	public Shield() {
		super(SkillLibrary.ALLY_SHIELD);
		this.name="Shield";
		this.description="Shields an Ally for some Time";
		this.target=TargetType.SINGLE_TARGET;
		this.effects=of(new Effect[] {
				new Effect(EffectType.STATUS_INFLICTION,1,0,StatusInfliction.INDESCTRUCTIBLE,null)
		});
		this.distance=2;
		this.manaCost=35;
		this.actionCost=1;
	}
}
