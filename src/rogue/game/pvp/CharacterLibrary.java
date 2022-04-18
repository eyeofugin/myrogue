package rogue.game.pvp;

import java.util.HashMap;

import rogue.framework.resources.Resources;
import rogue.game.pvp.individualcharacters.Balrog;
import rogue.game.pvp.individualcharacters.Baumbart;
import rogue.game.pvp.individualcharacters.BobaFett;
import rogue.game.pvp.individualcharacters.DarthSion;
import rogue.game.pvp.individualcharacters.DarthVader;
import rogue.game.pvp.individualcharacters.Dobby;
import rogue.game.pvp.individualcharacters.DoloresUmbridge;
import rogue.game.pvp.individualcharacters.Dumbledore;
import rogue.game.pvp.individualcharacters.Gimli;
import rogue.game.pvp.individualcharacters.Hagrid;
import rogue.game.pvp.individualcharacters.Legolas;
import rogue.game.pvp.individualcharacters.LukeSkywalker;
import rogue.game.pvp.individualcharacters.Moody;
import rogue.game.pvp.individualcharacters.MotherTalzin;
import rogue.game.pvp.individualcharacters.R2D2;
import rogue.game.pvp.individualcharacters.Radagast;
import rogue.game.pvp.individualcharacters.Sam;
import rogue.game.pvp.individualcharacters.Voldemort;
import rogue.game.world.objects.entities.PlayableCharacter;

public class CharacterLibrary {

	
	public static HashMap<Integer,PlayableCharacter> characters = new HashMap<>();
	
	
	public static void init() {
		characters.put(Resources.DARTH_VADER,new DarthVader());
		characters.put(Resources.DARTH_SION, new DarthSion());
		characters.put(Resources.LUKE, new LukeSkywalker());
		characters.put(Resources.BOBA, new BobaFett());
		characters.put(Resources.TALZIN, new MotherTalzin());
		characters.put(Resources.R2D2, new R2D2());
		characters.put(Resources.GIMLI, new Gimli());
		characters.put(Resources.RADAGAST, new Radagast());
		characters.put(Resources.SAMWISE, new Sam());
		characters.put(Resources.LEGOLAS, new Legolas());
		characters.put(Resources.BAUMBART, new Baumbart());
		characters.put(Resources.BALROG, new Balrog());
		characters.put(Resources.DOBBY, new Dobby());
		characters.put(Resources.UMBRIDGE, new DoloresUmbridge());
		characters.put(Resources.MOODY, new Moody());
		characters.put(Resources.HAGRID, new Hagrid());
		characters.put(Resources.DUMBLEDORE, new Dumbledore());
		characters.put(Resources.VOLDEMORT, new Voldemort());
		
		
//		characters.put(Resources.DARTH_VADER,new PlayableCharacter(
//				Resources.DARTH_VADER,"Darth Vader",Resources.P_VADER,0,
//				100,5,50,10,3,3,1,
//				getSkills(SkillLibrary.HATEFUL_SWING,SkillLibrary.FORCE_CHOKE,SkillLibrary.WEAPON_THROW,SkillLibrary.MERCILESS_MASSACRE,SkillLibrary.NONE,SkillLibrary.NONE),
//				DamageType.SLASHING,
//				Proficiency.STRENGTH,
//				resistance(50, 20, 80, 70, 40, 50, 30, 10, 50),
//				multipliers(1.0, 0.4, 1.7, 0.2, 0.0, 1.0, 0.0, 0.0, 1.2,1.0),
//				proficiencies(75, 85, 20, 0, 30)));
//		characters.put(Resources.DARTH_SION,new PlayableCharacter(
//				Resources.DARTH_SION,"Darth Sion",Resources.P_D_SION,0,
//				140,20,50,5,3,2,1,
//				getSkills(SkillLibrary.HATEFUL_SWING,SkillLibrary.CONDEMN,SkillLibrary.UNSTOPPABLE,SkillLibrary.LAST_RESERVE,SkillLibrary.NONE,SkillLibrary.NONE),
//				DamageType.SLASHING,
//				Proficiency.STRENGTH,
//				resistance(70, 80, 90, 50, 40, 65, 35, 85, 90),
//				multipliers(1.0, 0.5, 1.4, 0.2, 0.0, 1.0, 0.0, 1.1, 1.1,1.0),
//				proficiencies(65, 75, 15, 0, 15)));
//		characters.put(Resources.LUKE,new PlayableCharacter(
//				Resources.LUKE,"Luke Skywalker",Resources.P_LUKE,0,
//				50,15,60,18,4,3,1,
//				getSkills(SkillLibrary.RIGHTEOUS_SWING,SkillLibrary.FORCE_VISION,SkillLibrary.FORCE_PUSH,SkillLibrary.SHOCKWAVE_JUMP,SkillLibrary.NONE,SkillLibrary.NONE),
//				DamageType.SLASHING,
//				Proficiency.FAITH,
//				resistance(50, 45, 75, 40, 80, 35, 20, 60, 35),
//				multipliers(1.0, 0.2, 0.0, 0.2, 1.6, 1.0, 0.0, 1.2, 1.2,1.0),
//				proficiencies(55, 95, 10, 0, 15)));
//		characters.put(Resources.BOBA,new PlayableCharacter(
//				Resources.BOBA,"Boba Fett",Resources.P_BOBA,0,
//				50,5,40,5,4,3,3,
//				getSkills(SkillLibrary.FLAMETHROWER,SkillLibrary.BACTA_PAD,SkillLibrary.JETPACK_BOOST,SkillLibrary.ROCKET,SkillLibrary.NONE,SkillLibrary.NONE),
//				DamageType.BURNING,
//				Proficiency.PRECISION,
//				resistance(90, 75, 40, 55, 40, 90, 20, 60, 95),
//				multipliers(1.0, 1.0, 0.0, 0.0, 1.0, 1.0, 0.0, 1.0, 1.0,1.0),
//				proficiencies(55, 95, 10, 0, 15)));
//		characters.put(Resources.BALROG,null);
//		characters.put(Resources.BATMAN,null);
//		characters.put(Resources.BAUMBART,null);
//		characters.put(Resources.CHINA,null);
//		characters.put(Resources.DOBBY,null);
//		characters.put(Resources.DUMBLEDORE,null);
//		characters.put(Resources.GIMLI,null);
//		characters.put(Resources.GRAESSLICH,null);
//		characters.put(Resources.HAGRID,null);
//		characters.put(Resources.HELLBOY,null);
//		characters.put(Resources.LEGOLAS,null);
//		characters.put(Resources.MOODY,null);
//		characters.put(Resources.OBELIX,null);
//		characters.put(Resources.PROFESSOR,null);
//		characters.put(Resources.R2D2,new PlayableCharacter(
//				Resources.R2D2,"R2D2",Resources.P_BOBA,0,
//				20,5,60,10,4,2,1,
//				getSkills(SkillLibrary.SCAN,SkillLibrary.ALL_TERRAIN,SkillLibrary.EQUIPMENT_UPGRADE,SkillLibrary.SMOKE_SCREEN,SkillLibrary.NONE,SkillLibrary.NONE),
//				DamageType.SHOCK,
//				Proficiency.INTELLIGENCE,
//				resistance(20,30,20,20,30,15,20,5,35),
//				multipliers(1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.1,1.0,1.0),
//				proficiencies(10,0,35,0,5)));
//		characters.put(Resources.RADAGAST,null);
//		characters.put(Resources.SAMWISE,null);
//		characters.put(Resources.SERPINE,null);
//		characters.put(Resources.SKULDUGGERY,null);
//		characters.put(Resources.SOLOMON,null);
//		characters.put(Resources.TALZIN,new PlayableCharacter(
//				Resources.TALZIN,"Mother Talzin",Resources.P_LUKE,0,
//				20,5,60,10,4,2,1,
//				getSkills(SkillLibrary.ALLY_HEAL,SkillLibrary.ALLY_SHIELD,SkillLibrary.ZOMBIE_MINIONS,SkillLibrary.VOODOO_SHIT,SkillLibrary.NONE,SkillLibrary.NONE),
//				DamageType.SHOCK,
//				Proficiency.INTELLIGENCE,
//				resistance(20,30,20,20,30,15,20,5,35),
//				multipliers(1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.1,1.0,1.0),
//				proficiencies(10,0,35,0,5)));
//		characters.put(Resources.UMBRIDGE,null);
//		characters.put(Resources.V,null);
//		characters.put(Resources.VOLDEMORT,null);
//		characters.put(Resources.WHITESCYTHE,null);
	}
	public static PlayableCharacter get(int id) {
		return characters.get(id);
	}
}
