package rogue.framework.resources;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Loader {
	public static void load() {
		
		Resources.TEXTURES.add(Resources.VOID,new Sprite("res/textures/void.png",Property.TILE_SIZE,Property.TILE_SIZE).getPixels());
		Resources.TEXTURES.add(Resources.BRICK,new Sprite("res/textures/brick.png",Property.TILE_SIZE,Property.TILE_SIZE).getPixels());
		Resources.TEXTURES.add(Resources.WALL,new Sprite("res/textures/wall.png",Property.TILE_SIZE,Property.TILE_SIZE).getPixels());
		Resources.TEXTURES.add(Resources.KNIGHT,new Sprite("res/textures/knight.png",Property.TILE_SIZE,Property.TILE_SIZE).getPixels());
		Resources.TEXTURES.add(Resources.SKELETON,new Sprite("res/textures/skeleton.png",Property.TILE_SIZE,Property.TILE_SIZE).getPixels());
		Resources.TEXTURES.add(Resources.ENDWALL,new Sprite("res/textures/wall.png",Property.TILE_SIZE,Property.TILE_SIZE).getPixels());
		
		Resources.PORTRAITSx64.add(Resources.NONE,new Sprite("res/textures/lockedskill.png",64,64).getPixels());
		Resources.PORTRAITSx64.add(Resources.KNIGHTMALE,new Sprite("res/textures/knightPortraitMale.png",64,64).getPixels());
		Resources.PORTRAITSx64.add(Resources.SKELETONMALE,new Sprite("res/textures/skeletonPortraitMale.png",64,64).getPixels());
		
		Resources.PORTRAITSx32.add(Resources.LOCKED_SKILL,new Sprite("res/textures/lockedskill.png",32,32).getPixels());
	}
}
