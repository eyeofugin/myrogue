package rogue.game.combat;



import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import rogue.game.combat.skills.Skill;
import rogue.game.combat.skills.Skill.Effect;
import rogue.game.combat.skills.Skill.Effect.EffectType;
import rogue.game.world.objects.BattleLog;
import rogue.game.world.objects.Entity;
import rogue.game.world.objects.Entity.Proficiency;
import rogue.game.world.objects.PlayableCharacter;
import rogue.game.world.objects.SecondLayerObject;

public class CombatManager {
	static public boolean executeSkill(PlayableCharacter p, List<SecondLayerObject> os, Skill s, BattleLog log) {
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
		switch(s.getType()) {
		case DAMAGE:
			return executeDamage(p,os,s,log);
		case ENHANCEMENT:
			return executeEnhancement(p,os,s,log);
		case MOVEMENT:
			return executeMovementOrSummon(p,s,log);
		case PASSIVE:
			return executePassive(p,os,s,log);
		case SUMMON:
			return executeMovementOrSummon(p,s,log);
		case HEALING:
			return executeHeal(p,os,s,log);
		case VISION:
			return true;
		}
		return false;

	}
	static public boolean executeDamage(PlayableCharacter p, List<SecondLayerObject> os, Skill s, BattleLog log) {
		log.formulateUse(s.getName(), p.getName());
		if(miss(s)) {
			log.formulateMiss();
			return false;
		}
		int rawDamage = s.getSkillDamage(p);
		int lethality = p.getProficiency(Proficiency.LETHALITY);
		
		for(SecondLayerObject o: os) {
			if(Entity.class.isInstance(o)) {
				Entity defender = Entity.class.cast(o);
				if(defender.isIndestructible()) {
					log.formulateIndesctructible(defender.getName());
					continue;
				}
				int defense = defender.getResistance(s.getDamageType());
				int damage = (int)(rdmize(rawDamage) * ((100+lethality)  /  (100+(double)rdmize(defense))));
				defender.damage(damage);
				log.formulateEffect(o.getName(),damage);
				
				if(s.getEffects()!=null) {
					for(Effect e : s.getEffects()) {
						if(e.getType().equals(EffectType.STATUS_INFLICTION)||
								e.getType().equals(EffectType.STAT_CHANGE))
							defender.addEffect(e);
					}
				}
			}
		}
		return true;
	}
	static public boolean executeHeal(PlayableCharacter p, List<SecondLayerObject> os, Skill s, BattleLog log) {
		log.formulateUse(s.getName(), p.getName());
		if(miss(s)) {
			log.formulateMiss();
			return false;
		}
		int heal = s.getSkillDamage(p);
		for(SecondLayerObject o: os) {
			if(Entity.class.isInstance(o)) {
				Entity target = Entity.class.cast(o);
				if(target.isCursed()) {
					log.formulate(target.getName(),"cursed");
					continue;
				}
				target.heal(heal);
				log.formulateHeal(o.getName(),heal);
				if(s.getEffects()!=null) {
					for(Effect e : s.getEffects()) {
						if(e.getType().equals(EffectType.STATUS_INFLICTION)||
								e.getType().equals(EffectType.STAT_CHANGE))
							target.addEffect(e);
					}
				}
			}
		}
		return true;
	}
	static public boolean executeEnhancement(PlayableCharacter p, List<SecondLayerObject> os, Skill s, BattleLog log) {
		log.formulateUse(s.getName(), p.getName());
		if(miss(s)) {
			log.formulateMiss();
			return false;
		}
		for(SecondLayerObject o: os) {
			if(Entity.class.isInstance(o)) {
				Entity target = Entity.class.cast(o);
				
				if(s.getEffects()!=null) {
					for(Effect e : s.getEffects()) {
						if(e.getType().equals(EffectType.STATUS_INFLICTION)||
								e.getType().equals(EffectType.STAT_CHANGE))
							target.addEffect(e);
					}
				}
				
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
	static public boolean executePassive(PlayableCharacter p, List<SecondLayerObject> os, Skill s, BattleLog log) {
		
		return true;
	}

	static public void normalMelee(SecondLayerObject p, SecondLayerObject o, BattleLog log) {
		
		log.formulateUse("Basic attack", p.getName());
		
		Entity defender = Entity.class.cast(o);
		Entity attacker = Entity.class.cast(p);
		
		if(defender.isIndestructible()) {
			log.formulateIndesctructible(defender.getName());
			return ;
		}
		
		int normalMeleeDamage = attacker.getProficiency(Proficiency.STRENGTH);
		int normalMeleeDefense = defender.getResistance(attacker.getBasicDamageType());
		System.out.println(normalMeleeDamage + "||" + normalMeleeDefense);
		
		int damage = (int)(rdmize(normalMeleeDamage) * (10/(10+(double)rdmize(normalMeleeDefense))));
		System.out.println("damage: " + damage);
		
		defender.damage(damage);
		System.out.println("life: " + defender.getCurrentLife());
	
		
		log.formulateEffect(o.getName(), damage);
	}
//	static public void executeDamageSkill(SecondLayerObject p, List<SecondLayerObject> os,Skill s, BattleLog log) {
//		
//		log.formulateUse(s.getName(), p.getName());
//		Entity attacker = Entity.class.cast(p);
//		int damage = 0;//s.getDamage(attacker);
//		for(SecondLayerObject o: os) {
//			Entity defender = Entity.class.cast(o);
//			
//			
//			int defense = defender.getResistance(s.getDamageType());
//			
//			int total = (int)(rdmize(damage) * (10/(10+(double)rdmize(defense))));
//			
//			defender.damage(total);
//			
//			log.formulateEffect(o.getName(), damage);
//		}
//	}
	static private int rdmize(int a) {
		return a + ThreadLocalRandom.current().nextInt(-5, 5);
		
	}
	static private boolean miss(Skill s) {
		Random rand = new Random();
		float f = rand.nextFloat();
		if(f>(s.getAccuracy()/100.0)) {
			return true;
		}else {
			return false;
		}
	}
}
