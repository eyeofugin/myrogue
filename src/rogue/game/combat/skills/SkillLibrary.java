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
