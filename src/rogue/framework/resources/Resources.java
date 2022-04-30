package rogue.framework.resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import util.TextEditor;
import util.TextEditor.TextEditorConfig;

public class Resources {
	public static final int VOID = 0;
	public static final int BRICK = 1;
	public static final int WALL = 2;
	public static final int ENDWALL = 3;
	public static final int MEADOW = 4;
	public static final int TREE = 5;
	public static final int TALLGRASS = 6;
	public static final int SMOKE_SCREEN = 7;
	public static final int WATER = 8;
	public static final int HORCRUX = 9;
	public static final int RUNE_TRAP = 10;
	public static final int ENHANCEMENT_RUNE = 11;
	public static final int FROSTED = 12;
	public static final ArrayList<int[]> TEXTURES = new ArrayList<>();
	
	public static final int KNIGHT = 0;
	public static final int SKELETON = 1;
	public static final int SPONGEBOB= 2;
	public static final int DARTH_SION = 3;
	public static final int DARTH_VADER = 4;
	public static final int LUKE = 5;
	public static final int BOBA = 6;
	public static final int BALROG = 7;
	public static final int BATMAN = 8;
	public static final int BAUMBART = 9;
	public static final int CHINA = 10;
	public static final int DOBBY = 11;
	public static final int DUMBLEDORE = 12;
	public static final int GIMLI = 13;
	public static final int GRAESSLICH = 14;
	public static final int HAGRID = 15;
	public static final int HELLBOY = 16;
	public static final int LEGOLAS = 17;
	public static final int MOODY = 18;
	public static final int OBELIX = 19;
	public static final int PROFESSOR = 20;
	public static final int R2D2 = 21;
	public static final int RADAGAST = 22;
	public static final int SAMWISE = 23;
	public static final int SERPINE = 24;
	public static final int SKULDUGGERY = 25;
	public static final int SOLOMON = 26;
	public static final int TALZIN = 27;
	public static final int UMBRIDGE = 28;
	public static final int V = 29;
	public static final int VOLDEMORT = 30;
	public static final int WHITESCYTHE = 31;
	public static final int LUKE_ASTRAL = 32;
	public static final int ZOMBIE = 33;
	public static final int ARAGOG = 34;
	public static final int FLUFFY = 35;
	public static final int BASIC_SOLDIER = 36;
	public static final int WAMPA = 37;
	public static final int GREEDO = 38;
	public static final int YISAN = 39;
	public static final int MTG_SOLDIER = 40;
	public static final int REBEL = 41;
	public static final int WHITE_MAGE = 42;
	public static final int RED_MAGE = 43;
	public static final int BLUE_MAGE = 44;
	public static final int GREEN_MAGE = 45;
	public static final int BLACK_MAGE = 46;
	public static final int STORMTROOPER = 47;
	public static final int GOBLIN = 48;
	public static final int EWOK = 49;
	public static final int OOZE = 50;
	public static final int VAMPIRE = 51;
	public static final int FANATIC = 52;
	public static final int ROGUE = 53;
	public static final int GHOST = 54;
	public static final int GOBLINNPC = 55;
	public static final int GHOSTNPC = 56;
	
	public static final ArrayList<int[]> CHARACTERS = new ArrayList<>();
	
	public static final int DUMMY = 0;
	public static final int SKELETONMALE = 1;
	public static final int P_BOBA = 2;
	public static final int P_VADER = 3;
	public static final int P_D_SION = 4;
	public static final int P_LUKE = 5;
	public static final int P_BALROG = 6;
	public static final int P_BATMAN = 7;
	public static final int P_BAUMBART = 8;
	public static final int P_CHINA = 9;
	public static final int P_DOBBY = 10;
	public static final int P_DUMBLEDORE = 11;
	public static final int P_GIMLI = 12;
	public static final int P_GRAESSLICH = 13;
	public static final int P_HAGRID = 14;
	public static final int P_HELLBOY = 15;
	public static final int P_LEGOLAS = 16;
	public static final int P_MOODY = 17;
	public static final int P_OBELIX = 18;
	public static final int P_PROF_X = 19;
	public static final int P_R2D2 = 20;
	public static final int P_RADAGAST = 21;
	public static final int P_SAMWISE = 22;
	public static final int P_SERPINE = 23;
	public static final int P_SKULDUGGERY = 24;
	public static final int P_SOLOMON = 25;
	public static final int P_TALZIN = 26;
	public static final int P_UMBRIDGE = 27;
	public static final int P_V = 28;
	public static final int P_VOLDEMORT = 29;
	public static final int P_W_CLEAVER = 30;
	
	public static final ArrayList<int[]> PORTRAITSx64 = new ArrayList<>();
	
	//!!!!! -- 0-300 reserved for skills
	public static final int MOVEMENT_ACTION =301;
	public static final int ATTACK_ACTION =302;
	public static final int CONFIRM_ACTION = 303;
	public static final int END_TURN_ACTION = 304;
	public static final int CANCEL_ACTION = 305;
	public static final int UP = 306;
	public static final int DOWN = 307;
	public static final int SWITCH = 308;
	public static final int DELETE = 309;
	//!!!!! -- 0-300 reserved for skills
	public static final Map<Integer,int[]> ICONSx32 = new HashMap<>();
	
	public static final TextEditorConfig textEditorConfig = TextEditor.conf5x8;
	
}
