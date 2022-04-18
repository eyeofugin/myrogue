package util;

import rogue.framework.resources.Property;

public enum Highlight {
	MVMNT_BLUE{
		@Override
		public int[] printHighlight(int[] p,int x, int y) {
			for(int i = 1; i < (Property.TILE_SIZE-1); i++) {
				
				p[((x*Property.TILE_SIZE)+i)+((y*Property.TILE_SIZE)+Property.MARKING_START)*Property.ROOM_SIZE] = MyColor.BLUE.VALUE;
				p[((x*Property.TILE_SIZE)+i)+((y*Property.TILE_SIZE)+Property.MARKING_END)*Property.ROOM_SIZE] = MyColor.BLUE.VALUE;
				p[((x*Property.TILE_SIZE)+Property.MARKING_START)+((y*Property.TILE_SIZE)+i)*Property.ROOM_SIZE] = MyColor.BLUE.VALUE;
				p[((x*Property.TILE_SIZE)+Property.MARKING_END)+((y*Property.TILE_SIZE)+i)*Property.ROOM_SIZE] = MyColor.BLUE.VALUE;
			}
			return p;
		}
	},
	CMBT_RED{
		@Override
		public int[] printHighlight(int[] p,int x, int y) {
			for(int i = 1; i < (Property.TILE_SIZE-1); i++) {
				
				p[((x*Property.TILE_SIZE)+i)+((y*Property.TILE_SIZE)+Property.MARKING_START)*Property.ROOM_SIZE] = MyColor.RED.VALUE;
				p[((x*Property.TILE_SIZE)+i)+((y*Property.TILE_SIZE)+Property.MARKING_END)*Property.ROOM_SIZE] = MyColor.RED.VALUE;
				p[((x*Property.TILE_SIZE)+Property.MARKING_START)+((y*Property.TILE_SIZE)+i)*Property.ROOM_SIZE] = MyColor.RED.VALUE;
				p[((x*Property.TILE_SIZE)+Property.MARKING_END)+((y*Property.TILE_SIZE)+i)*Property.ROOM_SIZE] = MyColor.RED.VALUE;
			}
			return p;
		}
	},
	
	SKLL_GREEN{
		@Override
		public int[] printHighlight(int[] p,int x, int y) {
			for(int i = 1; i < (Property.TILE_SIZE-1); i++) {
				
				//TODO check if in visible room
				
				p[((x*Property.TILE_SIZE)+i)+((y*Property.TILE_SIZE)+Property.MARKING_START)*Property.ROOM_SIZE] = MyColor.GREEN.VALUE;
				p[((x*Property.TILE_SIZE)+i)+((y*Property.TILE_SIZE)+Property.MARKING_END)*Property.ROOM_SIZE] = MyColor.GREEN.VALUE;
				p[((x*Property.TILE_SIZE)+Property.MARKING_START)+((y*Property.TILE_SIZE)+i)*Property.ROOM_SIZE] = MyColor.GREEN.VALUE;
				p[((x*Property.TILE_SIZE)+Property.MARKING_END)+((y*Property.TILE_SIZE)+i)*Property.ROOM_SIZE] = MyColor.GREEN.VALUE;
			}
			return p;
		}
	},
	SKILL_SELECT{
		@Override
		public int[] printHighlight(int[] p,int x, int y) {
			for(int i = 1; i < (Property.TILE_SIZE-1); i++) {
				
				//TODO check if in visible room
				
				p[((x*Property.TILE_SIZE)+i)+((y*Property.TILE_SIZE)+Property.MARKING_START)*Property.ROOM_SIZE] = MyColor.TRUEGREEN.VALUE;
				p[((x*Property.TILE_SIZE)+i)+((y*Property.TILE_SIZE)+Property.MARKING_END)*Property.ROOM_SIZE] = MyColor.TRUEGREEN.VALUE;
				p[((x*Property.TILE_SIZE)+Property.MARKING_START)+((y*Property.TILE_SIZE)+i)*Property.ROOM_SIZE] = MyColor.TRUEGREEN.VALUE;
				p[((x*Property.TILE_SIZE)+Property.MARKING_END)+((y*Property.TILE_SIZE)+i)*Property.ROOM_SIZE] = MyColor.TRUEGREEN.VALUE;
			}
			return p;
		}
	},
	
	SELECT_WHITE{
		@Override
		public int[] printHighlight(int[] p,int x, int y) {
			for(int i = 1; i < (Property.TILE_SIZE-1); i++) {
				
				//TODO check if in visible room
				
				p[((x*Property.TILE_SIZE)+i)  						+((y*Property.TILE_SIZE)+Property.MARKING_START) 	*Property.ROOM_SIZE] = MyColor.WHITE.VALUE;
				p[((x*Property.TILE_SIZE)+i)  						+((y*Property.TILE_SIZE)+Property.MARKING_END)    	*Property.ROOM_SIZE] = MyColor.WHITE.VALUE;
				p[((x*Property.TILE_SIZE)+Property.MARKING_START)  	+((y*Property.TILE_SIZE)+i)							*Property.ROOM_SIZE] = MyColor.WHITE.VALUE;
				p[((x*Property.TILE_SIZE)+Property.MARKING_END)		+((y*Property.TILE_SIZE)+i)							*Property.ROOM_SIZE] = MyColor.WHITE.VALUE;
			}
			return p;
		}
	};
	
	public abstract int[] printHighlight(int[] p,int x, int y);
}
