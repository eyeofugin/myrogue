package util;

import rogue.framework.resources.Resources;

public enum MyColor {

	BLACK(0),
	WHITE(-1),
	DARKGREY(7829367),
	GREEN(8964864),
	TRUEGREEN(65280),
	RED(-65536),
	BLUE(-16776961),
	YELLOW(-16776961),
	VOID(-12450784);
	
	public final int VALUE;
	
	MyColor(int value){
		this.VALUE = value;
	}
	
	public static MyColor colorize(int target) {
		if(target<0) {return MyColor.RED;}
		else{return MyColor.GREEN;}
	}
	public static MyColor getMinimapColorForTiles(int id) {
		switch (id) {
		case Resources.BRICK:
			return DARKGREY;
		case Resources.ENDWALL:
			return WHITE;
		case Resources.VOID:
			return BLACK;
		case Resources.WALL:
			return WHITE;
		case Resources.MEADOW:
			return TRUEGREEN;
		case Resources.TREE:
			return GREEN;
		}
		return VOID;
	}
	public static MyColor getMinimapColorForTeam(int id) {
		//System.out.println(id);
		switch (id) {
		case 1:
			return BLUE;
		case 2:
			return RED;
		case 3:
			return YELLOW;
		}
		return VOID;
	}
}
