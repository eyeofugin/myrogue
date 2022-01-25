package rogue.framework.resources;

import java.util.ArrayList;

import util.TextEditor;
import util.TextEditor.TextEditorConfig;

public class Resources {
	public static final int VOID = 0;
	public static final int BRICK = 1;
	public static final int WALL = 2;
	public static final int KNIGHT = 3;
	public static final int SKELETON = 4;
	public static final int ENDWALL = 5;
	public static final int SPONGEBOB= 6;
	public static final int DARTH_SION = 7;
	public static final int DARTH_VADER = 8;
	public static final int LUKE = 9;
	public static final int BOBA = 10;
	public static final ArrayList<int[]> TEXTURES = new ArrayList<>();
	
	public static final int NONE = 0;
	public static final int KNIGHTMALE = 1;
	public static final int SKELETONMALE = 2;
	public static final int P_VADER = 3;
	public static final int P_D_SION = 4;
	public static final int P_LUKE = 5;
	public static final int P_BOBA = 6;
	
	public static final ArrayList<int[]> PORTRAITSx64 = new ArrayList<>();
	
	public static final int MOVEMENT_ACTION =0;
	public static final int ATTACK_ACTION =1;
	public static final int CONFIRM_ACTION = 2;
	public static final int END_TURN_ACTION = 3;
	public static final int CANCEL_ACTION = 4;
	public static final int UP = 5;
	public static final int DOWN = 6;
	//reserve 7 - 299 for skills
	public static final ArrayList<int[]> PORTRAITSx32 = new ArrayList<>();
	
	public static final TextEditorConfig textEditorConfig = TextEditor.conf5x8;
	
}
