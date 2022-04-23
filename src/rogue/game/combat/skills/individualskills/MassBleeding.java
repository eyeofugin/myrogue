package rogue.game.combat.skills.individualskills;

import rogue.game.combat.skills.Skill;
import rogue.game.combat.skills.Skill.Effect.EffectType;
import rogue.game.combat.skills.Skill.Effect.StatusInfliction;
import rogue.game.combat.skills.SkillLibrary;

public class MassBleeding extends Skill{

	public MassBleeding(){
		super();
		this.id=SkillLibrary.I_SHALL_NOT_TELL_LIES;
		this.name="Mass Bleeding";
		this.description="Make your Enemies bleed";
		this.target=TargetType.ALL_ENEMY;
		this.effects=of(new Effect[] {
				new Effect(EffectType.STATUS_INFLICTION,3,5,StatusInfliction.BLEEDING,null)
		});
		this.power=0;
		this.manaCost=20;
		this.actionCost=1;
	}
}
