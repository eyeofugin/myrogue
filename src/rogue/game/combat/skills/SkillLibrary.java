package rogue.game.combat.skills;

import java.util.HashMap;
import java.util.Map;

import rogue.framework.resources.Resources;
import rogue.game.combat.skills.Skill.DamageType;
import rogue.game.combat.skills.Skill.TargetType;
import rogue.game.combat.skills.Skill.Effect;
import rogue.game.combat.skills.Skill.Multiplier;
import rogue.game.combat.skills.Skill.Effect.EffectType;
import rogue.game.combat.skills.Skill.Effect.StatChange;
import rogue.game.combat.skills.Skill.Effect.StatusInfliction;
import rogue.game.world.objects.entities.Entity.Proficiency;
import rogue.game.world.objects.ObjectLibrary;

public class SkillLibrary {
	
	public static int NONE = 0;
	public static int ZOMBIE_MINIONS = 1;
	public static int HEART_SHOT = 2;
	public static int STURDY = 3;
	public static int FIEND_FYRE = 4;
	public static int MURDER_FEST = 5;
	public static int PSYSHOCK = 6;
	public static int STUN = 7;
	public static int HATEFUL_SWING = 8;
	public static int WEAPON_THROW = 9;
	public static int FORCE_CHOKE = 10;
	public static int MERCILESS_MASSACRE = 11;
	public static int RIGHTEOUS_SWING = 12;
	public static int FORCE_PUSH = 13;
	public static int FORCE_VISION = 14;
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
	public static int FELL_IN_THE_POT = 54;
	public static int FERRETFORMATION = 55;
	public static int FIRE_ARMOR = 56;
	public static int FIREBALL = 57;
	public static int FIRE_RING = 58;
	public static int FLAME_WHIP = 59;
	public static int FLUFFY = 60;
	public static int GIANT_HAWK = 61;
	public static int GRAPPLING_HOOK = 62;
	public static int GRAWP = 63;
	public static int GUARD_AREA = 64;
	public static int HIPPOGRYFF = 65;
	public static int HOLLOW_MAN = 66;
	public static int HORCRUX = 67;
	public static int ILLUSION = 68;
	public static int IMPERIO = 69;
	public static int I_SHALL_NOT_TELL_LIES = 70;
	public static int LIGHTNING_STRIKE = 71;
	public static int LORD_VILLE = 72;
	public static int FIST_BARRAGE = 73;
	public static int NORMAL_FORM = 74;
	public static int OCCULT_KNOWLEDGE = 75;
	public static int PETRIFY = 76;
	public static int PHOENIX_TELEPORTATION = 77;
	public static int PLANT = 78;
	public static int RABBIT_SLED = 79;
	public static int ROCK_THROW = 80;
	public static int ROPES = 81;
	public static int RUNE_TRAP = 82;
	public static int SCAN = 83;
	public static int SEND_IDEFIX = 84;
	public static int SHADOW_ARMOR = 85;
	public static int SHADOW_SPEARS = 86;
	public static int SHADOW_WALK = 87;
	public static int SHADOW_WALL = 88;
	public static int SHADOW_WAVE = 89;
	public static int SKULLDUGGERY = 90;
	public static int SMOKE_SCREEN = 91;
	public static int STUPOR = 92;
	public static int TALL_GRASS = 93;
	public static int TARNING = 94;
	public static int TAUNT = 95;
	public static int TELEPORT = 96;
	public static int TEND_THE_GARDEN = 97;
	public static int THROW_GRANADE = 98;
	public static int TOSSIN = 99;
	public static int TRACKING = 100;
	public static int TRUE_VISION = 101;
	public static int VOODOO_SHIT = 102;
	public static int WEAPON_SWING = 103;
	public static int WIND_WALL = 104;
	public static int WOOD_WALK = 105;


	public static Map<Integer,Skill> skills = new HashMap<>();
	
	public static void init() {
		skills.put(NONE, new Skill(NONE,"NONE","NONE"));
		skills.put(HATEFUL_SWING,Skill.getDamageSkill(
						HATEFUL_SWING,
						"Hateful Swing",
						"User performs slashing attack fueled with rage.",
						TargetType.SINGLE_TARGET,
						DamageType.NORMAL,
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
						DamageType.NORMAL,
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
												DamageType.ALL,0.8,null,0)),
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
												null,
												0,
												Proficiency.STRENGTH,
												30)
										),
								new Effect(EffectType.STAT_CHANGE,
										3,
										0,
										null,
										new StatChange(
												null,
												0,
												Proficiency.FAITH,
												20)
										)
						},
						new Multiplier[] {},
						0,0,
						60,0,3));
		skills.put(RIGHTEOUS_SWING, Skill.getDamageSkill(
						RIGHTEOUS_SWING,
						"Righteous Swing",
						"User strikes with determination.",
						TargetType.SINGLE_TARGET,
						DamageType.NORMAL,
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
						"Pushes Obstacles and Enemies.",
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
		skills.put(FORCE_VISION, Skill.getVisionSkill(
				FORCE_VISION,
				"Force Projection",
				"Creates an Illusion of Itself.",
				5,1,
				30,0,3));
		skills.put(SHOCKWAVE_JUMP, Skill.getDamageSkill(
				SHOCKWAVE_JUMP,
				"Shockwave Jump",
				"Jumps to Spot and inflicts Surrounding Damage.",
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
				"Can not be stopped.",
				null,
				null,
				null,0,0,
				0,0,0));
		skills.put(CONDEMN, Skill.getEnhancementSkill(CONDEMN,
				"Condemn",
				"Curses Target.",
				TargetType.SINGLE_TARGET,
				new Effect[] {new Effect(EffectType.STATUS_INFLICTION,0,0,StatusInfliction.CLEAR,null),
						new Effect(EffectType.STATUS_INFLICTION,3,0,StatusInfliction.CURSED,null)},
				null,
				3,0,
				15,0,1));
		skills.put(LAST_RESERVE, Skill.getEnhancementSkill(
				LAST_RESERVE, 
				"Last Reserve", 
				"User summons their last Strength at a cost.", 
				TargetType.SELF, 
				new Effect[] {new Effect(EffectType.STATUS_INFLICTION,3,0,StatusInfliction.INDESCTRUCTIBLE,null),
						new Effect(EffectType.STATUS_INFLICTION,3,0,StatusInfliction.CURSED,null)},
				null, 
				0, 0, 
				80, 50, 2));
		skills.put(FLAMETHROWER,Skill.getDamageSkill(FLAMETHROWER,
				"Flamethrower",
				"A burst of Fire.",
				TargetType.LINE_PIERCING,
				DamageType.BURNING,
				new Effect[] {new Effect(EffectType.STATUS_INFLICTION, 2, 5, StatusInfliction.BURNING, null),
								new Effect(EffectType.TERRAIN_ENHANCEMENT,2, 0, StatusInfliction.BURNING,null)},
				null, 
				30, 80, 
				3, 0, 
				30, 0, 1));
		skills.put(ROCKET,Skill.getDamageSkill(ROCKET,
				"Rocket",
				"Inflicts Damage on Impact.",
				TargetType.SINGLE_TARGET,
				DamageType.BURNING,
				new Effect[] {new Effect(EffectType.TERRAIN_ENHANCEMENT,0,1,StatusInfliction.REMOVE_OBSTACLE,null)},null,
				35,100,
				4,1,
				40,0,1));
		skills.put(JETPACK_BOOST, Skill.getEnhancementSkill(JETPACK_BOOST, 
				"Jetpack Boost", 
				"Flying to a Spot.", 
				TargetType.SINGLE_FREE, 
				new Effect[] {new Effect(EffectType.TELEPORT,0,0,null,null)}, null,
				3, 0, 
				30, 0, 2));
		skills.put(BACTA_PAD, Skill.getHealSkill(
				BACTA_PAD,
				"Bacta Pad",
				"Healing Damage.",
				TargetType.SINGLE_TARGET,
				null,null,
				30,100,
				1,0,
				20,0,3));
		skills.put(ALL_TERRAIN, Skill.getPassive(
				ALL_TERRAIN,
				"All terrain",
				"Can cross minor obstacles.",
				DamageType.NONE,
				null,null, 0, 0, 0, 0, 0));
		skills.put(ALLY_HEAL, Skill.getHealSkill(ALLY_HEAL,
				"Ally Heal",
				"Heals an Ally.",
				TargetType.SINGLE_TARGET,
				null,
				null,
				20, 100, 
				2, 0, 20, 0, 1));
		skills.put(ALLY_SHIELD, Skill.getEnhancementSkill(ALLY_SHIELD,
				"Ally Shield",
				"Shields an ally.",
				TargetType.SINGLE_TARGET,
				new Effect[] {
						new Effect(EffectType.STATUS_INFLICTION,2,0,StatusInfliction.INDESCTRUCTIBLE,null)
				},
				null,
				2, 0,
				30, 0, 2));
		skills.put(ARAGOG, Skill.getSummonSkill(ARAGOG, "Aragog", "Hagrid summons Aragog as NPC.", 1, 0, 20, 0, 2, Resources.ARAGOG));
		skills.put(AREAFIRE, Skill.getDamageSkill(AREAFIRE, "Area Fire", "Let Ground burst in Flames.", 
				TargetType.SINGLE_TARGET, DamageType.BURNING, 
				new Effect[] {new Effect(EffectType.STATUS_INFLICTION,1,10,StatusInfliction.BURNING,null)}, 
				new Multiplier[] {
						new Multiplier(Proficiency.STRENGTH,0.3)
				}, 
				30, 100, 4, 1, 40, 0, 3));
		skills.put(ARROW_BARRAGE, Skill.getDamageSkill(ARROW_BARRAGE, "Arrow Barrage", "Multiple Arrows on an Area.", 
				TargetType.SINGLE_TARGET, DamageType.NORMAL, 
				null,
				new Multiplier[] {
						new Multiplier(Proficiency.PRECISION,0.3)
				},
				20, 100, 4, 1, 40, 0, 3));
		skills.put(ARROW_SHOTS,Skill.getDamageSkill(ARROW_SHOTS,
				"Arrow Shots","A quick succession of Arrows.", 
				TargetType.LINE,DamageType.NORMAL, 
				null, new Multiplier[] {new Multiplier(Proficiency.PRECISION,0.2)},
				20, 90, 3, 0, 30, 0, 2));
		skills.put(AVADA_KEDAVRA,null);
		skills.put(BATARANG,Skill.getDamageSkill(BLUDGER,
				"Batarang",
				"Throws a Batarang.",
				TargetType.LINE_PIERCING,
				DamageType.NORMAL,
				new Effect[] {new Effect(EffectType.STATUS_INFLICTION,2,10,StatusInfliction.BLEEDING,null)},
				new Multiplier[] {
						new Multiplier(Proficiency.LETHALITY,0.3)
				},
				20, 60, 5, 0, 10, 0, 1));
		skills.put(BEHEAD,null);
		skills.put(BE_TOSSED,null);
		skills.put(BLIND,null);
		skills.put(BLUDGER,Skill.getDamageSkill(BLUDGER,
				"Bludger",
				"Bewitch a Bludger to Strike a Foe.",
				TargetType.LINE,
				DamageType.NORMAL,
				new Effect[] {new Effect(EffectType.STATUS_INFLICTION,2,1,StatusInfliction.PARALYSED,null)},
				new Multiplier[] {
						new Multiplier(Proficiency.INTELLIGENCE,0.3)
				},
				20, 60, 5, 0, 10, 0, 1));
		skills.put(BONDING,null);
		skills.put(CAMOUFLAGE,Skill.getPassive(CAMOUFLAGE, "Camouflage",
				"Are invisible in tall grass.",
				null,
				null,
				null,0,0,
				0,0,0));
		skills.put(CEREBRO,null);
		skills.put(COUNT_KILLS,null);
		skills.put(CLAW_TO_LIFE,null);
		skills.put(CROW_SCOUT,Skill.getVisionSkill(CROW_SCOUT, "Crow Scout", "Reveals an area.", 6, 2, 60, 0, 2));
		skills.put(CRUCIO,null);
		skills.put(DAGGER_THROW,null);
		skills.put(DEMONIC_FORM,null);
		skills.put(DISARM,null);
		skills.put(DISGUISE,Skill.getEnhancementSkill(DISGUISE,
				"Disguise","Appear as another ally.",
				TargetType.SELF,
				new Effect[] {new Effect(EffectType.TRANSFORMATION,2,0,null,null,-1)}, null,
				1, 0,
				30, 0, 1));
		skills.put(EDUCATIONAL_DECREE_NO_4,null);
		skills.put(EMPTY_REVOLVER,null);
		skills.put(ENERGY_BIND,null);
		skills.put(ENHANCEMENT_RUNE,null);
		skills.put(EQUIPMENT_UPGRADE,Skill.getEnhancementSkill(EQUIPMENT_UPGRADE,
				"Equipment Upgrade","Boost power for a short moment.",
				TargetType.SINGLE_TARGET,
				new Effect[] {new Effect(EffectType.STAT_CHANGE,2,0,null,new StatChange(null, 0.0, Proficiency.STRENGTH, 20))}, null,
				1, 0,
				30, 0, 1));
		skills.put(EVASIVE,null);
		skills.put(EVERYTHING_BURNS,null);
		skills.put(FELL_IN_THE_POT,null);
		skills.put(FERRETFORMATION,null);
		skills.put(FIRE_ARMOR,Skill.getPassive(FIRE_ARMOR, "Fire Armor", "Enemies take damage when near you.",
				null, null, null, 0, 0, 0, 0, 0));
		skills.put(FIREBALL,null);
		skills.put(FIRE_RING,Skill.getDamageSkill(FIRE_RING, "Fire Ring", "Summon Flames surrounding you.",
				TargetType.SURROUNDING, DamageType.BURNING,
				new Effect[] {new Effect(EffectType.STATUS_INFLICTION,3,5,StatusInfliction.BURNING,null)}, 
				new Multiplier[] {
						new Multiplier(Proficiency.INTELLIGENCE,0.1)},
				35, 90, 1, 0, 30, 0, 3));
		skills.put(FLAME_WHIP,Skill.getDamageSkill(FLAME_WHIP, "Flame Whip", "Whip your foes with licking Flames.", 
				TargetType.LINE_PIERCING, DamageType.BURNING, 
				new Effect[] {new Effect(EffectType.STATUS_INFLICTION,1,0,StatusInfliction.STUNNED,null)},
				new Multiplier[] {
						new Multiplier(Proficiency.STRENGTH,0.5)
				},
				15, 75, 4, 0, 40, 0, 3));
		skills.put(FLUFFY,Skill.getSummonSkill(FLUFFY, "Fluffy", "Hagrid summons Fluffy as a NPC.", 1, 0, 20, 0, 2, Resources.FLUFFY));
		skills.put(GIANT_HAWK,null);
		skills.put(GRAPPLING_HOOK,Skill.getMovementSkill(GRAPPLING_HOOK, "Grappling Hook", "Pulls towards Spot", null, null, 1, 0, 0, 1));
		skills.put(GRAWP,null);
		skills.put(GUARD_AREA,null);
		skills.put(HIPPOGRYFF,null);
		skills.put(HOLLOW_MAN,null);
		skills.put(HORCRUX,Skill.getSummonObjSkill(HORCRUX, "Horcrux", "Hide a part of your Soul.", 
				1, 0, 20, 5, 1, Resources.HORCRUX));
		skills.put(ILLUSION,null);
		skills.put(IMPERIO,null);
		skills.put(I_SHALL_NOT_TELL_LIES,Skill.getEnhancementSkill(I_SHALL_NOT_TELL_LIES,"I shall not tell lies","All your enemies will bleed.",
				TargetType.ALL_ENEMY,
				new Effect[] {new Effect(EffectType.STATUS_INFLICTION,2,15,StatusInfliction.BLEEDING,null)}, null,
				0, 0,
				40, 0, 3));
		skills.put(LIGHTNING_STRIKE,null);
		skills.put(LORD_VILLE,null);
		skills.put(FIST_BARRAGE,null);
		skills.put(NORMAL_FORM,null);
		skills.put(OCCULT_KNOWLEDGE,null);
		skills.put(PETRIFY,null);
		skills.put(PHOENIX_TELEPORTATION,Skill.getDamageSkill(PHOENIX_TELEPORTATION,"Phoenix Teleportation","Teleport to Spot and inflict Surrounding Damage.",
				TargetType.SINGLE_FREE,DamageType.BURNING,
				new Effect[] {
					new Effect(EffectType.TELEPORT,0,0,null,null),
					new Effect(EffectType.STATUS_INFLICTION,1,5,StatusInfliction.BURNING,null)
				},
				new Multiplier[] {
					new Multiplier(Proficiency.INTELLIGENCE,0.2)
				},
				30,100,
				3,1,
				100,0,3));
		skills.put(PLANT,Skill.getSummonObjSkill(PLANT, "Plant Tree", "Plant a Tree.", 
				1, 0, 20, 5, 1, Resources.TREE));
		skills.put(RABBIT_SLED,null);
		skills.put(ROCK_THROW,null);
		skills.put(ROPES,Skill.getEnhancementSkill(ROPES,"Ropes","Immobilize your Opponent.",
				TargetType.LINE,
				new Effect[] {new Effect(EffectType.STATUS_INFLICTION,3,0,StatusInfliction.ROOTED,null)}, null,
				0, 0,
				40, 0, 2));
		skills.put(RUNE_TRAP,null);
		skills.put(SCAN,Skill.getVisionSkill(SCAN, "Scan", "Reveal an Area.", 6, 2, 60, 0, 2));
		skills.put(SEND_IDEFIX,null);
		skills.put(SHADOW_ARMOR,null);
		skills.put(SHADOW_SPEARS,null);
		skills.put(SHADOW_WALK,null);
		skills.put(SHADOW_WALL,null);
		skills.put(SHADOW_WAVE,null);
		skills.put(SKULLDUGGERY,null);
		skills.put(SMOKE_SCREEN,Skill.getSummonObjSkill(
				SMOKE_SCREEN,
				"Smoke Screen",
				"Summon thick smoke.",
				3, 1,
				20, 0, 1,
				Resources.SMOKE_SCREEN));
		skills.put(STUPOR,Skill.getDamageSkill(STUPOR, "Stupor", "Stun a foe.",
				TargetType.LINE, DamageType.MAGICAL,
				new Effect[] {new Effect(EffectType.STATUS_INFLICTION,1,0,StatusInfliction.STUNNED,null)}, 
				new Multiplier[] {
						new Multiplier(Proficiency.INTELLIGENCE,0.1)
				},
				30, 90, 3, 0, 30, 0, 3));
		skills.put(TALL_GRASS,Skill.getSummonObjSkill(TALL_GRASS, "TALL_GRASS", "Summon Tall Grass.", 
				2, 1, 20, 5, 1, Resources.TALLGRASS));
		skills.put(TARNING,Skill.getPassive(TARNING, "Tarning", "Be hard to see.",
				null, null, null, 0, 0, 0, 0, 0));
		skills.put(TAUNT,null);
		skills.put(TELEPORT, Skill.getEnhancementSkill(TELEPORT, 
				"Teleport", 
				"Teleport to a spot.", 
				TargetType.SINGLE_FREE, 
				new Effect[] {new Effect(EffectType.TELEPORT,0,0,null,null)}, null,
				4, 0, 
				30, 0, 2));
		skills.put(TEND_THE_GARDEN,Skill.getHealSkill(TEND_THE_GARDEN,"Tend the garden", "Baumbart heals himself", 
				TargetType.SELF, null, null, 
				20, 100, 0, 0, 20, 0, 2));
		skills.put(THROW_GRANADE,null);
		skills.put(TOSSIN,null);
		skills.put(TRACKING,null);
		skills.put(TRUE_VISION,Skill.getPassive(TRUE_VISION, "True Vision",
				"User can see it all.",
				null,
				null,
				null,0,0,
				0,0,0));
		skills.put(VOODOO_SHIT, Skill.getEnhancementSkill(VOODOO_SHIT,
				"Voodoo Shit",
				"Curse your foes.",
				TargetType.ALL_ENEMY,
				new Effect[] {new Effect(EffectType.STATUS_INFLICTION,3,10,StatusInfliction.CURSED,null)}, null,
				0, 0,
				40, 0, 3));
		skills.put(WEAPON_SWING,Skill.getDamageSkill(WEAPON_SWING, "Weapon Swing", "Hurl your Weapon around you.",
				TargetType.SURROUNDING, DamageType.NORMAL,
				new Effect[] {new Effect(EffectType.STATUS_INFLICTION,3,5,StatusInfliction.BLEEDING,null)}, 
				new Multiplier[] {
						new Multiplier(Proficiency.STRENGTH,0.1)},
				30, 90, 1, 0, 30, 0, 3));
		skills.put(WIND_WALL,null);
		skills.put(WOOD_WALK,Skill.getPassive(WOOD_WALK, "Wood walk",
				"Faster on Tall Grass.",
				null,
				null,
				null,0,0,
				0,0,0));
		skills.put(ZOMBIE_MINIONS,Skill.getSummonSkill(ZOMBIE_MINIONS,
				"Zombie Infestation",
				"Summon a Zombie Army.",
				3, 1,
				30, 0, 4,
				Resources.ZOMBIE));
		skills.put(HEART_SHOT,Skill.getDamageSkill(HEART_SHOT, "Heart Shot", "A perfectly aimed shot.",
				TargetType.SINGLE_TARGET, DamageType.NORMAL, 
				new Effect[] {new Effect(EffectType.STATUS_INFLICTION,3,1,StatusInfliction.PARALYSED,null)},
				new Multiplier[] {
						new Multiplier(Proficiency.PRECISION,0.8)
				},
				20, 75, 5, 0, 20, 0, 1));
		skills.put(STURDY, Skill.getEnhancementSkill(
				STURDY,
				"Sturdy",
				"Increases your Resistance.",
				TargetType.SELF,
				new Effect[] {
						new Effect(EffectType.STATUS_INFLICTION,
								3,
								0,
								StatusInfliction.INDESCTRUCTIBLE,
								null),
						new Effect(EffectType.STAT_CHANGE,
								3,
								0,
								null,
								new StatChange(
										DamageType.NORMAL,
										0.2,
										null,
										0)
								)
				},
				null,
				0,0,
				60,0,3));
		skills.put(FIEND_FYRE,Skill.getDamageSkill(FIEND_FYRE,"Fiend Fyre","Powerful Demonic Flames.",
				TargetType.SINGLE_TARGET,
				DamageType.DARK,
				new Effect[] {
						new Effect(
								EffectType.STATUS_INFLICTION,
								2,
								10,
								StatusInfliction.BURNING,
								null),
						new Effect(
								EffectType.STATUS_INFLICTION,
								2,
								5,
								StatusInfliction.CURSED,
								null)
				},
				new Multiplier[] {
						new Multiplier(Proficiency.FAITH,0.8)
				},
				55,80,
				1,0,
				30,0,1));
		skills.put(MURDER_FEST,Skill.getDamageSkill(MURDER_FEST, "Murder Fest", "Voldemort Strikes Surrounding Foes.",
				TargetType.SURROUNDING, DamageType.MAGICAL,
				new Effect[] {new Effect(EffectType.STATUS_INFLICTION,3,5,StatusInfliction.CURSED,null),
						new Effect(EffectType.OBJECT_PUSH,0,1,null,null)}, 
				new Multiplier[] {
						new Multiplier(Proficiency.INTELLIGENCE,0.1)},
				35, 90, 1, 0, 30, 0, 3));
	}
	public static Skill getSkill(int id) {
		Skill copy = new Skill();
		copy.setAccuracy(skills.get(id).getAccuracy());
		copy.setActionCost(skills.get(id).getActionCost());
		copy.setBlocked(skills.get(id).isBlocked());
		copy.setDamageType(skills.get(id).getDamageType());
		copy.setDescription(skills.get(id).getDescription());
		copy.setDistance(skills.get(id).getDistance());
		copy.setEffects(skills.get(id).getEffects());
		copy.setEvent(skills.get(id).getEvent());
		copy.setId(skills.get(id).getId());
		copy.setLifeCost(skills.get(id).getLifeCost());
		copy.setManaCost(skills.get(id).getManaCost());
		copy.setMultipliers(skills.get(id).getMultipliers());
		copy.setName(skills.get(id).getName());
		copy.setPower(skills.get(id).getPower());
		copy.setRadius(skills.get(id).getRadius());
		copy.setSummonedId(skills.get(id).getSummonedId());
		copy.setTarget(skills.get(id).getTarget());
		copy.setType(skills.get(id).getType());
		return copy;
	}
}
