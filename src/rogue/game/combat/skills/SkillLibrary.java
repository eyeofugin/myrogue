package rogue.game.combat.skills;

import java.util.HashMap;
import java.util.Map;

import rogue.game.combat.skills.Skill.DamageType;
import rogue.game.combat.skills.Skill.TargetType;
import rogue.game.combat.skills.Skill.Effect;
import rogue.game.combat.skills.Skill.Multiplier;
import rogue.game.combat.skills.Skill.Effect.EffectType;
import rogue.game.combat.skills.Skill.Effect.StatChange;
import rogue.game.combat.skills.Skill.Effect.StatusInfliction;
import rogue.game.world.objects.Entity.Proficiency;

public class SkillLibrary {
	
	public static int NONE = 7;
	public static int HATEFUL_SWING = 8;
	public static int WEAPON_THROW = 9;
	public static int FORCE_CHOKE = 10;
	public static int MERCILESS_MASSACRE = 11;
	public static int RIGHTEOUS_SWING = 12;
	public static int FORCE_PUSH = 13;
	public static int FORCE_PROJECTION = 14;
	public static int SHOCKWAVE_JUMP = 15;
	public static int UNSTOPPABLE = 16;
	public static int CONDEMN = 17;
	public static int LAST_RESERVE = 18;
	public static int FLAMETHROWER = 19;
	public static int ROCKET = 20;
	public static int JETPACK_BOOST = 21;
	public static int BACTA_PAD = 22;
	public static int ALL_TERRAIN = 23;
	public static int ALLY_HEAL = 24;
	public static int ALLY_SHIELD = 25;
	public static int ARAGOG = 26;
	public static int AREAFIRE = 27;
	public static int ARROW_BARRAGE = 28;
	public static int ARROW_SHOTS = 29;
	public static int AVADA_KEDAVRA = 30;
	public static int BATARANG = 31;
	public static int BEHEAD = 32;
	public static int BE_TOSSED = 33;
	public static int BLIND = 34;
	public static int BLUDGER = 35;
	public static int BONDING = 36;
	public static int CAMOUFLAGE = 37;
	public static int CEREBRO = 38;
	public static int COUNT_KILLS = 39;
	public static int CLAW_TO_LIFE = 40;
	public static int CROW_SCOUT = 41;
	public static int CRUCIO = 42;
	public static int DAGGER_THROW = 43;
	public static int DEMONIC_FORM = 44;
	public static int DISARM = 45;
	public static int DISGUISE = 46;
	public static int EDUCATIONAL_DECREE_NO_4 = 47;
	public static int EMPTY_REVOLVER = 48;
	public static int ENERGY_BIND = 49;
	public static int ENHANCEMENT_RUNE = 50;
	public static int EQUIPMENT_UPGRADE = 51;
	public static int EVASIVE = 52;
	public static int EVERYTHING_BURNS = 53;
	

	public static Map<Integer,Skill> skills = new HashMap<>();
	
	public static void init() {
		skills.put(NONE, new Skill(NONE,"NONE","NONE"));
		skills.put(HATEFUL_SWING,Skill.getDamageSkill(
						HATEFUL_SWING,
						"Hateful Swing",
						"User performs slashing attack fueled with rage.",
						TargetType.SINGLE_TARGET,
						DamageType.SLASHING,
						new Effect[] {
								new Effect(
										EffectType.STATUS_INFLICTION,
										2,
										10,
										StatusInfliction.BURNING,
										null)
						},
						new Multiplier[] {
								new Multiplier(Proficiency.FAITH,0.8)
						},
						55,80,
						1,0,
						30,0,1));
		skills.put(WEAPON_THROW, Skill.getDamageSkill(
						WEAPON_THROW,
						"Weapon Throw",
						"User hurls his weapon.",
						TargetType.LINE,
						DamageType.SLASHING,
						new Effect[] {},
						new Multiplier[] {
								new Multiplier(Proficiency.PRECISION,0.3)
						},
						40,70,
						3,0,
						40,0,1));
		skills.put(FORCE_CHOKE, Skill.getDamageSkill(
						FORCE_CHOKE,
						"Force Choke",
						"User stuns target and inflicts damage.",
						TargetType.SINGLE_TARGET,
						DamageType.DARK,
						new Effect[] {
								new Effect(EffectType.STATUS_INFLICTION,1,0,StatusInfliction.STUNNED,null),
								new Effect(EffectType.STAT_CHANGE,1,0,null,new StatChange(
												DamageType.ALL,-0.2,null,0)),
								new Effect(EffectType.OBJECT_PULL,0,1,null,null)
						},
						new Multiplier[] {
								new Multiplier(Proficiency.FAITH,0.3)
						},
						30,100,
						3,0,
						50,0,1));
		skills.put(MERCILESS_MASSACRE, Skill.getEnhancementSkill(
						MERCILESS_MASSACRE,
						"Merciless Massacre",
						"User enters a state of rage and fury.",
						TargetType.SELF,
						new Effect[] {
								new Effect(EffectType.STATUS_INFLICTION,
										3,
										0,
										StatusInfliction.CURSED,
										null),
								new Effect(EffectType.STAT_CHANGE,
										3,
										0,
										null,
										new StatChange(
												DamageType.DARK,
												0.3,
												null,
												0)
										)
						},
						new Multiplier[] {},
						0,0,
						60,0,3));
		skills.put(RIGHTEOUS_SWING, Skill.getDamageSkill(
						RIGHTEOUS_SWING,
						"Righteous Swing",
						"",
						TargetType.SINGLE_TARGET,
						DamageType.SLASHING,
						new Effect[] {
								new Effect(EffectType.STATUS_INFLICTION,2,10,StatusInfliction.BURNING,null)
						},
						new Multiplier[] {
								new Multiplier(Proficiency.FAITH,0.8)
						},
						50,80,
						1,0,
						30,0,1));
		skills.put(FORCE_PUSH, Skill.getDamageSkill(
						FORCE_PUSH,
						"Force Push",
						"",
						TargetType.SURROUNDING,
						DamageType.LIGHT,
						new Effect[] {
								new Effect(EffectType.OBJECT_PUSH,0,1,null,null)
						},
						new Multiplier[] {
								new Multiplier(Proficiency.FAITH,0.2)
						},
						20,100,
						1,0,
						30,0,2));
		skills.put(FORCE_PROJECTION, Skill.getSummonSkill(
				FORCE_PROJECTION,
				"Force Projection",
				"",
				5,0,
				30,0,3,
				0));
		skills.put(SHOCKWAVE_JUMP, Skill.getDamageSkill(
				SHOCKWAVE_JUMP,
				"Shockwave Jump",
				"",
				TargetType.SINGLE_FREE,
				DamageType.SHOCK,
				new Effect[] {
					new Effect(EffectType.TELEPORT,0,0,null,null),
					new Effect(EffectType.STATUS_INFLICTION,1,1,StatusInfliction.PARALYSED,null)
				},
				new Multiplier[] {
					new Multiplier(Proficiency.FAITH,0.2)
				},
				30,100,
				3,1,
				100,0,3));
		skills.put(UNSTOPPABLE, Skill.getPassive(
				UNSTOPPABLE,
				"Unstoppable",
				"",
				null,
				new Effect[] {new Effect(EffectType.PROTECTION_FROM,-1,0,StatusInfliction.STUNNED,null),
						new Effect(EffectType.PROTECTION_FROM,-1,0,StatusInfliction.PARALYSED,null)},
				null,0,0,
				0,0,0));
		skills.put(CONDEMN, Skill.getEnhancementSkill(CONDEMN,
				"Condemn",
				"",
				TargetType.SINGLE_TARGET,
				new Effect[] {new Effect(EffectType.STATUS_INFLICTION,0,0,StatusInfliction.CLEAR,null),
						new Effect(EffectType.STATUS_INFLICTION,3,0,StatusInfliction.CURSED,null)},
				null,
				3,0,
				15,0,1));
		skills.put(LAST_RESERVE, Skill.getEnhancementSkill(
				LAST_RESERVE, 
				"Last Reserve", 
				"", 
				TargetType.SELF, 
				new Effect[] {new Effect(EffectType.STATUS_INFLICTION,3,0,StatusInfliction.INDESCTRUCTIBLE,null),
						new Effect(EffectType.STATUS_INFLICTION,3,0,StatusInfliction.CURSED,null)},
				null, 
				0, 0, 
				80, 0, 2));
		skills.put(FLAMETHROWER,Skill.getDamageSkill(FLAMETHROWER,
				"Flamethrower",
				"",
				TargetType.LINE,
				DamageType.BURNING,
				new Effect[] {new Effect(EffectType.STATUS_INFLICTION, 2, 5, StatusInfliction.BURNING, null),
								new Effect(EffectType.TERRAIN_ENHANCEMENT,2, 0, StatusInfliction.BURNING,null)},
				null, 
				30, 80, 
				3, 0, 
				30, 0, 1));
		skills.put(ROCKET,Skill.getDamageSkill(ROCKET,
				"Rocket",
				"",
				TargetType.SINGLE_TARGET,
				DamageType.BURNING,
				new Effect[] {new Effect(EffectType.TERRAIN_ENHANCEMENT,0,1,StatusInfliction.REMOVE_OBSTACLE,null)},null,
				35,100,
				4,1,
				40,0,1));
		skills.put(JETPACK_BOOST, Skill.getEnhancementSkill(JETPACK_BOOST, 
				"Jetpack Boost", 
				"", 
				TargetType.SINGLE_FREE, 
				new Effect[] {new Effect(EffectType.TELEPORT,0,0,null,null)}, null,
				3, 0, 
				30, 0, 2));
		skills.put(BACTA_PAD, Skill.getDamageSkill(
				BACTA_PAD,
				"Bacta Pad",
				"",
				TargetType.SINGLE_TARGET,
				DamageType.HEAL,
				null,null,
				30,100,
				1,0,
				20,0,3));
		
	}
	public static Skill getSkill(int id) {
		return skills.get(id);
	}
}
