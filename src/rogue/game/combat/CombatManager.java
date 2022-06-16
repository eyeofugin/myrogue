package rogue.game.combat;



import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import rogue.game.combat.skills.Skill;
import rogue.game.combat.skills.Skill.DamageType;
import rogue.game.combat.skills.Skill.Effect;
import rogue.game.combat.skills.Skill.Effect.EffectType;
import rogue.game.combat.skills.Skill.Effect.StatusInfliction;
import rogue.game.combat.skills.Skill.TargetType;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.world.objects.BattleLog;
import rogue.game.world.objects.entities.Entity;
import rogue.game.world.objects.entities.Entity.Proficiency;
import rogue.game.world.objects.entities.NPC;
import rogue.game.world.objects.entities.PlayableCharacter;
import rogue.game.world.objects.tiles.Tile;
import rogue.game.world.objects.tiles.Enhancement.Level;

public class CombatManager {
	static public boolean executeSkill(Entity p, List<Entity> os,List<Tile> targets, Skill s, BattleLog log) {
		if(p.getCurrentActions()<s.getActionCost())
			return false;
		if(!p.useAction(s.getActionCost())) {
			return false;
		};
		if(!p.useMana(s.getManaCost())) {
			return false;
		};
		if(!p.useLife(s.getLifeCost())) {
			return false;
		};
		if(s.getDamageType()!=DamageType.NULL && s.getTarget()!=TargetType.NONE) {
			executeDamage(p,os,s,log);
		}
		if(s.getEffects()!=null && s.getEffects().size()>0) {
			applyEffects(p,os,targets,s,log);
		}
		if(s.getId()==SkillLibrary.DUEL_TO_THE_DEATH) {
			duelToTheDeath(p,os,log);
		}
//		switch(s.getType()) {
//		case DAMAGE:
//			return executeDamage(p,os,s,log);
//		case ENHANCEMENT:
//			return executeEnhancement(p,os,s,log);
//		case MOVEMENT:
//			return executeMovementOrSummon(p,s,log);
//		case PASSIVE:
//			return executePassive(p,os,s,log);
//		case SUMMONNPC:
//			return executeMovementOrSummon(p,s,log);
//		case HEALING:
//			return executeHeal(p,os,s,log);
//		case VISION:
//			return true;
//		case SUMMON:
//			return executeMovementOrSummon(p,s,log);
//		}
		return true;

	}
	static public boolean executeDamage(Entity p, List<Entity> targets, Skill s, BattleLog log) {
		log.formulateUse(s.getName(), p.getName());
		if(miss(s.getAccuracy())) {
			log.formulateMiss();
			return false;
		}
		int rawDamage = s.getSkillDamage(p);
		int lethality = p.getProficiency(Proficiency.LETHALITY);
		DamageType dmg = s.getDamageType();
		for(Entity o: targets) {
			Entity defender = Entity.class.cast(o);
			if(dmg.equals(DamageType.HEAL)) {
				o.heal(s.getPower());
			}else {
				if(defender.isIndestructible()) {
					log.formulateIndesctructible(defender.getName());
					continue;
				}
				int damage = defender.damage(rawDamage,s.getDamageType(),lethality);

				log.formulateEffect(o.getName(),damage);
				if(defender.getCurrentLife()<1) {
					log.formulateDeath(o.getName());
				}
			}
		}
		return true;
	}
	static private boolean applyEffects(Entity p, List<Entity> targets,List<Tile>tiles, Skill s, BattleLog log) {
		for(Entity defender : targets) {
			for(Effect e : s.getEffects()) {
				if(		e.getType().equals(EffectType.STATUS_INFLICTION)||
						e.getType().equals(EffectType.STAT_CHANGE)||
						e.getType().equals(EffectType.OBJECT_PUSH)||
						e.getType().equals(EffectType.OBJECT_PULL)) {
					if(e.getStatus()!=null && e.getStatus().equals(StatusInfliction.REMOVE_NPC)) {
						if(NPC.class.isInstance(defender)) {
							defender.setCurrentLife(0);
							log.formulate(defender.getName(), "Fled");
						}
					}
					defender.addEffect(e);
				}
			}
		}
		for(Tile tile : tiles) {
			for(Effect e : s.getEffects()) {
				if(e.getType().equals(EffectType.TERRAIN_ENHANCEMENT)) {
					if(e.getStatus().equals(StatusInfliction.REMOVE_OBSTACLE)) {
						tile.getEnhancements().removeIf(i->i.getLevel().equals(Level.TOP));
					}
				}
			}
		}
		return true;
	}
	static public boolean duelToTheDeath(Entity p, List<Entity> os, BattleLog log) {
		for(Entity e : os) {
			log.formulateUse(e.getName(), p.getName());
			if(p.getCurrentLife()>e.getCurrentLife()) {
				e.damage(e.getCurrentLife());
				p.damage(e.getCurrentLife());
				log.formulateEffect(p.getName(),e.getCurrentLife());
				log.formulateDeath(e.getName());
			}else {
				p.damage(p.getCurrentLife());
				e.damage(p.getCurrentLife());
				log.formulateEffect(e.getName(),p.getCurrentLife());
				log.formulateDeath(p.getName());
				return true;
			}
		}
		return true;
	}
	static public boolean executeMovementOrSummon(PlayableCharacter p, Skill s, BattleLog log) {
		log.formulateUse(s.getName(), p.getName());
		if(s.getEffects()!=null) {
			for(Effect e : s.getEffects()) {
				if(e.getType().equals(EffectType.STATUS_INFLICTION)||
						e.getType().equals(EffectType.STAT_CHANGE))
					p.addEffect(e);
			}
		}
		
		return true;
	}
	static public boolean executePassive(PlayableCharacter p, List<Entity> os, Skill s, BattleLog log) {
		
		return true;
	}

	static public void normalMelee(Entity p, Entity o, BattleLog log) {
		
		double attMult = 1.0;
		int    attFlat = 0;
		double defMult = 1.0;
		int    defFlat = 0;
		log.formulateUse("Basic attack", p.getName());
		if(p.hasAbility(SkillLibrary.HIT_OR_MISS) && miss(50)) {
			log.formulateMiss();
			return;
		}
		if(p.hasAbility(SkillLibrary.UNDERDOG) && p.getTier()<o.getTier()) {
			attMult=2.0;
		}
		if(p.hasAbility(SkillLibrary.BEHEAD)) {
			double defLifePercentage = o.getCurrentLife()*100 / (double)o.getMaxLife();
			if(defLifePercentage<50) {attMult=1.5;}
			if(defLifePercentage<40) {attMult=2.0;}
			if(defLifePercentage<30) {attMult=3.0;}
			if(defLifePercentage<20) {attMult=5.0;}
		}
		
		Entity defender = Entity.class.cast(o);
		Entity attacker = Entity.class.cast(p);
		
		if(defender.isIndestructible()) {
			log.formulateIndesctructible(defender.getName());
			return ;
		}
		
		int normalMeleeDamage = (int)((attacker.getProficiency(Proficiency.STRENGTH) + attFlat)* attMult);
		int normalMeleeDefense = (int)((defender.getResistance(attacker.getBasicDamageType()) +defFlat)*defMult);
		
		int damage = (int)(rdmize(normalMeleeDamage) * (100/(100+(double)rdmize(normalMeleeDefense))));
		
		defender.damage(damage);
		if(attacker.hasAbility(SkillLibrary.LIFELINK)) {
			attacker.heal(damage/2);
			log.formulateHeal(attacker.getName(), damage/2);
		}
		if(defender.getSkills().stream().map(i->i.getId()).collect(Collectors.toList()).contains(SkillLibrary.FIRE_ARMOR)) {
			Skill firearmor = SkillLibrary.get(SkillLibrary.FIRE_ARMOR);
			p.damage(firearmor.getPower(),DamageType.BURNING,0);
		}
		
		log.formulateEffect(o.getName(), damage);
	}
	static private int rdmize(int a) {
		return a + ThreadLocalRandom.current().nextInt(-5, 5);
		
	}
	static private boolean miss(int acc) {
		Random rand = new Random();
		float f = rand.nextFloat();
		if(f>(acc/100.0)) {
			return true;
		}else {
			return false;
		}
	}
}
