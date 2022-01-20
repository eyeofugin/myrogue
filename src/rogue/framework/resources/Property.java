package rogue.framework.resources;

public class Property {

	public static final int TILE_SIZE = 64;
	public static final int BASELINEDISTANCE = 4;
	public static final int BASECHARDISTANCE = 1;
	
	public static final int ROOM_SIZE = 1024;
	
	public static final int START_OF_X = 0;
	public static final int END_OF_X = 1920;
	public static final int START_OF_Y = 0;
	public static final int END_OF_Y = 1080;
	
	public static final int START_OF_ROOM_Y = 28;
	public static final int END_OF_ROOM_Y = 1052;
	public static final int START_OF_ROOM_X = 448;
	public static final int END_OF_ROOM_X = 1472;
	
	public static final int START_OF_ROOM_BACKGROUND_Y = 0;
	public static final int END_OF_ROOM_BACKGROUND_Y = 1080;
	public static final int START_OF_ROOM_BACKGROUND_X = 420;
	public static final int END_OF_ROOM_BACKGROUND_X = 1500;
	
	public static final int START_OF_ACTIVE_CHAR_Y = 0;
	public static final int END_OF_ACTIVE_CHAR_Y = 660;
	public static final int START_OF_ACTIVE_CHAR_X = 1500;
	public static final int END_OF_ACTIVE_CHAR_X = 1920;
	public static final int ACTIVE_CHAR_WIDTH = 420;
	public static final int ACTIVE_CHAR_HEIGHT = 660;
	
	public static final int START_OF_ACTIVE_NPC_Y = 660;
	public static final int END_OF_ACTIVE_NPC_Y = 980;
	public static final int START_OF_ACTIVE_NPC_X = 1500;
	public static final int END_OF_ACTIVE_NPC_X = 1920;
	public static final int ACTIVE_NPC_WIDTH = 420;
	public static final int ACTIVE_NPC_HEIGHT = 320;
	
	public static final int BUTTON_PANEL_X_FROM = 1500;
	public static final int BUTTON_PANEL_X_UNTIL = 1920;
	public static final int BUTTON_PANEL_Y_FROM = 980;
	public static final int BUTTON_PANEL_Y_UNTIL = 1080;
	public static final int BUTTON_PANEL_WIDTH = 420;
	public static final int BUTTON_PANEL_HEIGHT = 100;
	
	public static final int END_BUTTON_X_FROM = 1000;
	public static final int END_BUTTON_X_UNTIL = 1099;
	public static final int END_BUTTON_Y_FROM = 1067;
	public static final int END_BUTTON_Y_UNTIL = 1079;
	public static final int END_BUTTON_WIDTH=100;
	public static final int END_BUTTON_HEIGHT=13;
	
	public static final int MINIMAP_WIDTH=256;
	public static final int MINIMAP_HEIGHT=256;
	
	public static final int MINIMAP_X_FROM=100;
	public static final int MINIMAP_Y_FROM=800;
	public static final int MINIMAP_X_UNTIL=356;
	public static final int MINIMAP_Y_UNTIL=1056;
	
	public static final int LOG_WIDTH = 400;
	public static final int LOG_HEIGHT= 500;
	
	public static final int LOG_Y_FROM = 24;
	public static final int LOG_Y_UNTIL= 524;
	public static final int LOG_X_FROM = 10;
	public static final int LOG_X_UNTIL= 410;
	
	public static final int LOG_BUTTON_WIDTH = 32;
	public static final int LOG_BUTTON_HEIGHT = 32;
	
	public static final int LOG_BUTTON_X_FROM = 363;
	public static final int LOG_BUTTON_X_UNTIL= 395;
	public static final int LOG_BUTTON_UP_Y_FROM =431;
	public static final int LOG_BUTTON_UP_Y_UNTIL =463;
	public static final int LOG_BUTTON_DOWN_Y_FROM=463;
	public static final int LOG_BUTTON_DOWN_Y_UNTIL=495;
	
	
	public static final int ROOM_VIEW_TILE_COUNT = 16;
	
	public static final int BUTTON_NORMAL_HEIGHT = 20;
	public static final int BUTTON_NORMAL_WIDTH = 80;
	
	public static final int MARKING_START = 1;
	public static final int MARKING_END = 62;
	
	public static final int CHANGE_CONFIRM_X_FROM  = 600;
	public static final int CHANGE_CONFIRM_X_UNTIL = CHANGE_CONFIRM_X_FROM+BUTTON_NORMAL_WIDTH-1;
	public static final int CHANGE_CONFIRM_Y_FROM  = 500;
	public static final int CHANGE_CONFIRM_Y_UNTIL = CHANGE_CONFIRM_Y_FROM+BUTTON_NORMAL_HEIGHT-1;
	
	public static final int TEAM_NEUTRAL=0;
	public static final int TEAM_1=1;
	public static final int TEAM_2=2;
}
