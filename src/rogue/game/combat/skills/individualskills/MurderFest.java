package rogue.game.combat.skills.individualskills;

import rogue.game.combat.skills.Skill;
import rogue.game.combat.skills.Skill.Effect.EffectType;
import rogue.game.combat.skills.Skill.Effect.StatusInfliction;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.world.objects.entities.Entity.Proficiency;

public class MurderFest extends Skill{
	public MurderFest() {
		super(SkillLibrary.MURDER_FEST);
		this.name="Voldemorts Murder Fest";
		this.description="Voldemort inflicts great Damage to Surrounding Characters";
		this.target=TargetType.SURROUNDING;
		this.damageType=DamageType.MAGICAL;
		this.effects=of(
			new Effect[] {
				new Effect(EffectType.STATUS_INFLICTION,3,5,StatusInfliction.CURSED,null),
				new Effect(EffectType.OBJECT_PUSH,0,1,null,null)});
		this.multipliers=of(new Multiplier[] {
				new Multiplier(Proficiency.KNOWLEDGE,0.3)
		});
		this.power=45;
		this.accuracy=95;
		this.distance=1;
		this.manaCost=40;
		this.actionCost=1;
	}
}
