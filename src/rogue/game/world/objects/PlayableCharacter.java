package rogue.game.world.objects;

import java.util.HashMap;
import java.util.Map;

import rogue.framework.eventhandling.Connector;
import rogue.game.combat.skills.Skill;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.combat.skills.Skill.DamageType;
import rogue.game.world.objects.Entity.Proficiency;
import util.MovementOption;

public class PlayableCharacter extends Entity{
	
	public PlayableCharacter() {
		super();
	}
	
	public PlayableCharacter(int x, int y, int id,String name, int portraitId, int team, MovementOption movement, Connector connector) {
		super();
	}
	public PlayableCharacter(int id, String name, int portraitId, int team,
			int maxLife,int lifeRegain,int maxMana,int manaRegain,int maxActions,int maxMovement,int range,Skill[] skills,DamageType std,Proficiency stdP,
			Map<DamageType,Integer> resistances,Map<DamageType,Double> multipliers,Map<Proficiency,Integer> proficiencies) {
		super(id,portraitId,name,MovementOption.PLAYER,team,null,maxLife,lifeRegain,maxMana,manaRegain,maxActions,maxMovement,range,skills,std,stdP,resistances,multipliers,proficiencies);
	}
	
	protected static Skill[] getSkills(int s1, int s2, int s3, int s4, int s5, int s6) {
		return new Skill[] {
			SkillLibrary.getSkill(s1),
			SkillLibrary.getSkill(s2),
			SkillLibrary.getSkill(s3),
			SkillLibrary.getSkill(s4),
			SkillLibrary.getSkill(s5),
			SkillLibrary.getSkill(s6)
		};
	}
	protected static Map<DamageType,Integer> resistance(int bludge,int burn,int dark,int freeze,int light,int pierce,int psych,int shock,int slash){
		Map<DamageType,Integer> resistances = new HashMap<>();
		resistances.put(DamageType.BLUDGEONING, bludge);
		resistances.put(DamageType.BURNING, burn);
		resistances.put(DamageType.DARK, dark);
		resistances.put(DamageType.FREEZING, freeze);
		resistances.put(DamageType.LIGHT, light);
		resistances.put(DamageType.PIERCING, pierce);
		resistances.put(DamageType.MAGICAL, psych);
		resistances.put(DamageType.SHOCK, shock);
		resistances.put(DamageType.SLASHING, slash);
		return resistances;
	}
	protected static Map<DamageType,Double> multipliers(double bludge,double burn,double dark,double freeze,double light,double pierce,double psych,double shock,double slash,double heal){
		Map<DamageType,Double> mult = new HashMap<>();
		mult.put(DamageType.BLUDGEONING, bludge);
		mult.put(DamageType.BURNING, burn);
		mult.put(DamageType.DARK, dark);
		mult.put(DamageType.FREEZING, freeze);
		mult.put(DamageType.LIGHT, light);
		mult.put(DamageType.PIERCING, pierce);
		mult.put(DamageType.MAGICAL, psych);
		mult.put(DamageType.SHOCK, shock);
		mult.put(DamageType.SLASHING, slash);
		mult.put(DamageType.HEAL,heal);
		return mult;
	}
	protected static Map<Proficiency,Integer> proficiencies(int strength, int faith, int intelligence, int lethality, int precision){
		Map<Proficiency,Integer> proficiencies = new HashMap<>();
		proficiencies.put(Proficiency.STRENGTH,strength);
		proficiencies.put(Proficiency.FAITH,faith);
		proficiencies.put(Proficiency.INTELLIGENCE,intelligence);
		proficiencies.put(Proficiency.LETHALITY,lethality);
		proficiencies.put(Proficiency.PRECISION,precision);
		return proficiencies;
	}
}
