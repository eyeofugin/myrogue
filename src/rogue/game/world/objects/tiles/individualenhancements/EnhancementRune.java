package rogue.game.world.objects.tiles.individualenhancements;

import rogue.framework.resources.Resources;
import rogue.game.combat.skills.Skill.Effect;
import rogue.game.combat.skills.Skill.Effect.EffectType;
import rogue.game.combat.skills.Skill.Effect.StatChange;
import rogue.game.world.objects.entities.Entity;
import rogue.game.world.objects.entities.Entity.Proficiency;
import rogue.game.world.objects.tiles.Enhancement;

public class EnhancementRune extends Enhancement{
	public EnhancementRune() {
		super(Resources.ENHANCEMENT_RUNE);
		this.level = Level.TOP;
		this.visTeam=true;
		this.visEnemy=true;
		this.solid=false;
		this.blockVis=false;
		this.duration=5;
	}
	
	@Override
	public void onEnter(Entity e) {
		if(this.team!=e.getTeam()) {
			this.duration=0;
		}else {
			Effect faithBuff = new Effect(EffectType.STAT_CHANGE,-1,0,null,new StatChange(null, 0.0, Proficiency.FAITH, 10));
			faithBuff.setEnhancementId(this.getId());
			Effect precisionBuff = new Effect(EffectType.STAT_CHANGE,-1,0,null,new StatChange(null, 0.0, Proficiency.PRECISION, 10));
			precisionBuff.setEnhancementId(this.getId());
			Effect lethalityBuff = new Effect(EffectType.STAT_CHANGE,-1,0,null,new StatChange(null, 0.0, Proficiency.LETHALITY, 10));
			lethalityBuff.setEnhancementId(this.getId());
			Effect strengthBuff = new Effect(EffectType.STAT_CHANGE,-1,0,null,new StatChange(null, 0.0, Proficiency.STRENGTH, 10));
			strengthBuff.setEnhancementId(this.getId());
			Effect knowledgeBuff = new Effect(EffectType.STAT_CHANGE,-1,0,null,new StatChange(null, 0.0, Proficiency.KNOWLEDGE, 10));
			knowledgeBuff.setEnhancementId(this.getId());
			e.addEffect(knowledgeBuff);
			e.addEffect(faithBuff);
			e.addEffect(strengthBuff);
			e.addEffect(lethalityBuff);
			e.addEffect(precisionBuff);
		}
	}
	
	@Override
	public void onLeave(Entity e) {
		e.removeEffectOfEnhancement(this.getId());
	}
}
