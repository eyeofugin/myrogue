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
	
	public static final ArrayList<int[]> CHARACTERS = new ArrayList<>();
	
	public static final int DUMMY = 0;
	public static final int SKELETONMALE = 1;
	public static final int P_BOBA = 2;
	public static final int P_VADER = 3;
	public static final int P_D_SION = 4;
	public static final int P_LUKE = 5;
	
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
