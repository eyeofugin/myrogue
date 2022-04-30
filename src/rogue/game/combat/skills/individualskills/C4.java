package rogue.game.combat.skills.individualskills;

import rogue.game.combat.skills.Skill;
import rogue.game.combat.skills.Skill.Effect.EffectType;
import rogue.game.combat.skills.Skill.Effect.StatusInfliction;
import rogue.game.combat.skills.SkillLibrary;

public class C4 extends Skill{
	public C4() {
		super(SkillLibrary.C4);
		this.name="C4";
		this.description="Use Explosives to remove Obstacles";
		this.target=TargetType.SINGLE_TARGET;
		this.effects=of(
				new Effect[] {
					new Effect(EffectType.TERRAIN_ENHANCEMENT,0,0,StatusInfliction.REMOVE_OBSTACLE,null)});
		this.distance=1;
		this.accuracy=80;
		this.actionCost=2;
		this.manaCost=20;
		this.lifeCost=10;
	}
}
