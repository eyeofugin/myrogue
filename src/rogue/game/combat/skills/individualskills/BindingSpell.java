package rogue.game.combat.skills.individualskills;

import rogue.game.combat.skills.Skill;
import rogue.game.combat.skills.Skill.Effect.EffectType;
import rogue.game.combat.skills.Skill.Effect.StatusInfliction;
import rogue.game.combat.skills.SkillLibrary;

public class BindingSpell extends Skill{
	public BindingSpell(){
		super(SkillLibrary.ROPES);
		this.name="Binding Spell";
		this.description="Binds Enemy";
		this.target=TargetType.SINGLE_TARGET;
		this.damageType=DamageType.MAGICAL;
		this.effects=of(new Effect[] {
				new Effect(EffectType.STATUS_INFLICTION,3,0,StatusInfliction.ROOTED,null)
		});
		this.power=20;
		this.accuracy=80;
		this.distance=3;
		this.manaCost=20;
		this.actionCost=1;
	}
}
