package rogue.framework.resources;

import java.util.ArrayList;

import util.TextEditor;
import util.TextEditor.TextEditorConfig;

public class Resources {
	public static final byte VOID = 0;
	public static final byte BRICK = 1;
	public static final byte WALL = 2;
	public static final byte KNIGHT = 3;
	public static final byte SKELETON = 4;
	public static final byte ENDWALL = 5;
	public static final byte SPONGEBOB= 6;
	public static final byte DARTH_SION = 7;
	public static final byte DARTH_VADER = 8;
	public static final byte LUKE = 9;
	public static final byte BOBA = 10;
	public static final ArrayList<int[]> TEXTURES = new ArrayList<>();
	
	public static final byte NONE = 0;
	public static final byte KNIGHTMALE = 1;
	public static final byte SKELETONMALE = 2;
	public static final ArrayList<int[]> PORTRAITSx64 = new ArrayList<>();
	
	public static final byte LOCKED_SKILL = 0;
	public static final byte SLASH_SKILL = 1;
	public static final byte MOVEMENT_ACTION =2;
	public static final byte ATTACK_ACTION =3;
	public static final byte CONFIRM_ACTION = 4;
	public static final byte END_TURN_ACTION = 5;
	public static final byte CANCEL_ACTION = 6;
	public static final byte UP = 7;
	public static final byte DOWN = 8;
	public static final ArrayList<int[]> PORTRAITSx32 = new ArrayList<>();
	
	public static final TextEditorConfig textEditorConfig = TextEditor.conf5x8;
	
}
