package rogue.game.pvp;

import java.util.HashMap;
import java.util.Map;

import rogue.framework.resources.Resources;
import rogue.game.combat.skills.Skill.DamageType;
import rogue.game.combat.skills.Skill;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.world.objects.Entity.Proficiency;
import rogue.game.world.objects.PlayableCharacter;

public class CharacterLibrary {

	
	public static HashMap<Integer,PlayableCharacter> characters = new HashMap<>();
	
	public static void init() {
		characters.put(Resources.DARTH_VADER,new PlayableCharacter(
				Resources.DARTH_VADER,"Darth Vader",Resources.P_VADER,0,
				100,5,50,10,3,3,1,
				getSkills(SkillLibrary.HATEFUL_SWING,SkillLibrary.FORCE_CHOKE,SkillLibrary.WEAPON_THROW,SkillLibrary.MERCILESS_MASSACRE,SkillLibrary.NONE,SkillLibrary.NONE),
				DamageType.SLASHING,
				Proficiency.STRENGTH,
				resistance(50, 20, 80, 70, 40, 50, 30, 10, 50),
				multipliers(1.0, 0.4, 1.7, 0.2, 0.0, 1.0, 0.0, 0.0, 1.2,1.0),
				proficiencies(75, 85, 20, 0, 30)));
		characters.put(Resources.DARTH_SION,new PlayableCharacter(
				Resources.DARTH_SION,"Darth Sion",Resources.P_D_SION,0,
				140,20,50,5,3,2,1,
				getSkills(SkillLibrary.HATEFUL_SWING,SkillLibrary.CONDEMN,SkillLibrary.UNSTOPPABLE,SkillLibrary.LAST_RESERVE,SkillLibrary.NONE,SkillLibrary.NONE),
				DamageType.SLASHING,
				Proficiency.STRENGTH,
				resistance(70, 80, 90, 50, 40, 65, 35, 85, 90),
				multipliers(1.0, 0.5, 1.4, 0.2, 0.0, 1.0, 0.0, 1.1, 1.1,1.0),
				proficiencies(65, 75, 15, 0, 15)));
		characters.put(Resources.LUKE,new PlayableCharacter(
				Resources.LUKE,"Luke Skywalker",Resources.P_LUKE,0,
				50,15,60,18,4,3,1,
				getSkills(SkillLibrary.RIGHTEOUS_SWING,SkillLibrary.FORCE_VISION,SkillLibrary.FORCE_PUSH,SkillLibrary.SHOCKWAVE_JUMP,SkillLibrary.NONE,SkillLibrary.NONE),
				DamageType.SLASHING,
				Proficiency.FAITH,
				resistance(50, 45, 75, 40, 80, 35, 20, 60, 35),
				multipliers(1.0, 0.2, 0.0, 0.2, 1.6, 1.0, 0.0, 1.2, 1.2,1.0),
				proficiencies(55, 95, 10, 0, 15)));
		characters.put(Resources.BOBA,new PlayableCharacter(
				Resources.BOBA,"Boba Fett",Resources.P_BOBA,0,
				50,5,40,5,4,3,3,
				getSkills(SkillLibrary.FLAMETHROWER,SkillLibrary.BACTA_PAD,SkillLibrary.JETPACK_BOOST,SkillLibrary.ROCKET,SkillLibrary.NONE,SkillLibrary.NONE),
				DamageType.BURNING,
				Proficiency.PRECISION,
				resistance(90, 75, 40, 55, 40, 90, 20, 60, 95),
				multipliers(1.0, 1.0, 0.0, 0.0, 1.0, 1.0, 0.0, 1.0, 1.0,1.0),
				proficiencies(55, 95, 10, 0, 15)));
	}
	public static PlayableCharacter get(int id) {
		return characters.get(id);
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
		resistances.put(DamageType.PSYCHIC, psych);
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
		mult.put(DamageType.PSYCHIC, psych);
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
