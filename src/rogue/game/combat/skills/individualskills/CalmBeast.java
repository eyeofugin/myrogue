package rogue.game.combat.skills.individualskills;

import rogue.game.combat.skills.Skill;
import rogue.game.combat.skills.Skill.Effect.EffectType;
import rogue.game.combat.skills.Skill.Effect.StatusInfliction;
import rogue.game.combat.skills.SkillLibrary;

public class CalmBeast extends Skill{
	public CalmBeast() {
		super(SkillLibrary.CALM_BEAST);
		this.name="Calm Beast";
		this.description="Use on Npc to make it flee";
		this.target=TargetType.SINGLE_TARGET;
		this.effects=of(
				new Effect[] {
					new Effect(EffectType.STATUS_INFLICTION,2,10,StatusInfliction.REMOVE_NPC,null)});
		this.distance=2;
		this.accuracy=80;
		this.actionCost=2;
		this.manaCost=55;
		this.lifeCost=0;
	}

}
