package util;

import rogue.framework.resources.Property;

public enum Highlight {
	MVMNT_BLUE{
		@Override
		public int[] printHighlight(int[] p,int x, int y, int xOffset, int yOffset) {
			for(int i = 1; i < (Property.TILE_SIZE-1); i++) {
				
				p[((x*Property.TILE_SIZE)+i+(-1)*xOffset*Property.TILE_SIZE)+((y*Property.TILE_SIZE)+Property.MARKING_START+(-1)*yOffset*Property.TILE_SIZE)*Property.ROOM_SIZE] = MyColor.BLUE.VALUE;
				p[((x*Property.TILE_SIZE)+i+(-1)*xOffset*Property.TILE_SIZE)+((y*Property.TILE_SIZE)+Property.MARKING_END+(-1)*yOffset*Property.TILE_SIZE)*Property.ROOM_SIZE] = MyColor.BLUE.VALUE;
				p[((x*Property.TILE_SIZE)+Property.MARKING_START+(-1)*xOffset*Property.TILE_SIZE)+((y*Property.TILE_SIZE)+i+(-1)*yOffset*Property.TILE_SIZE)*Property.ROOM_SIZE] = MyColor.BLUE.VALUE;
				p[((x*Property.TILE_SIZE)+Property.MARKING_END+(-1)*xOffset*Property.TILE_SIZE)+((y*Property.TILE_SIZE)+i+(-1)*yOffset*Property.TILE_SIZE)*Property.ROOM_SIZE] = MyColor.BLUE.VALUE;
			}
			return p;
		}
	},
	CMBT_RED{
		@Override
		public int[] printHighlight(int[] p,int x, int y, int xOffset, int yOffset) {
			for(int i = 1; i < (Property.TILE_SIZE-1); i++) {
				
				p[((x*Property.TILE_SIZE)+i+(-1)*xOffset*Property.TILE_SIZE)+((y*Property.TILE_SIZE)+Property.MARKING_START+(-1)*yOffset*Property.TILE_SIZE)*Property.ROOM_SIZE] = MyColor.RED.VALUE;
				p[((x*Property.TILE_SIZE)+i+(-1)*xOffset*Property.TILE_SIZE)+((y*Property.TILE_SIZE)+Property.MARKING_END+(-1)*yOffset*Property.TILE_SIZE)*Property.ROOM_SIZE] = MyColor.RED.VALUE;
				p[((x*Property.TILE_SIZE)+Property.MARKING_START+(-1)*xOffset*Property.TILE_SIZE)+((y*Property.TILE_SIZE)+i+(-1)*yOffset*Property.TILE_SIZE)*Property.ROOM_SIZE] = MyColor.RED.VALUE;
				p[((x*Property.TILE_SIZE)+Property.MARKING_END+(-1)*xOffset*Property.TILE_SIZE)+((y*Property.TILE_SIZE)+i+(-1)*yOffset*Property.TILE_SIZE)*Property.ROOM_SIZE] = MyColor.RED.VALUE;
			}
			return p;
		}
	},
	
	SKLL_GREEN{
		@Override
		public int[] printHighlight(int[] p,int x, int y, int xOffset, int yOffset) {
			for(int i = 1; i < (Property.TILE_SIZE-1); i++) {
				
				//TODO check if in visible room
				
				p[((x*Property.TILE_SIZE)+i+(-1)*xOffset*Property.TILE_SIZE)+((y*Property.TILE_SIZE)+Property.MARKING_START+(-1)*yOffset*Property.TILE_SIZE)*Property.ROOM_SIZE] = MyColor.GREEN.VALUE;
				p[((x*Property.TILE_SIZE)+i+(-1)*xOffset*Property.TILE_SIZE)+((y*Property.TILE_SIZE)+Property.MARKING_END+(-1)*yOffset*Property.TILE_SIZE)*Property.ROOM_SIZE] = MyColor.GREEN.VALUE;
				p[((x*Property.TILE_SIZE)+Property.MARKING_START+(-1)*xOffset*Property.TILE_SIZE)+((y*Property.TILE_SIZE)+i+(-1)*yOffset*Property.TILE_SIZE)*Property.ROOM_SIZE] = MyColor.GREEN.VALUE;
				p[((x*Property.TILE_SIZE)+Property.MARKING_END+(-1)*xOffset*Property.TILE_SIZE)+((y*Property.TILE_SIZE)+i+(-1)*yOffset*Property.TILE_SIZE)*Property.ROOM_SIZE] = MyColor.GREEN.VALUE;
			}
			return p;
		}
	};
	
	public abstract int[] printHighlight(int[] p,int x, int y, int xOffset, int yOffset);
}
