package rogue.game.world.objects.tiles.individualenhancements;

import rogue.framework.resources.Resources;
import rogue.game.combat.skills.Skill.Effect;
import rogue.game.combat.skills.Skill.Effect.EffectType;
import rogue.game.combat.skills.Skill.Effect.StatusInfliction;
import rogue.game.world.objects.entities.Entity;
import rogue.game.world.objects.tiles.Enhancement;

public class RuneTrap extends Enhancement{
	public RuneTrap() {
		super(Resources.RUNE_TRAP);
		this.level = Level.SUB;
		this.visTeam=true;
		this.visEnemy=false;
		this.solid=false;
		this.blockVis=false;
		this.duration=10;
	}
	@Override
	public void onEnter(Entity e) {
		Effect effect = new Effect(EffectType.STATUS_INFLICTION,2,0,StatusInfliction.ROOTED,null);
		e.addEffect(effect);
		this.duration=0;
	}
}
