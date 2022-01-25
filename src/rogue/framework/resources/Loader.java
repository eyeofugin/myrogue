package rogue.framework.resources;

import rogue.game.combat.skills.SkillLibrary;
import rogue.game.pvp.CharacterLibrary;

public class Loader {
	public static void load() {
		
		SkillLibrary.init();
		CharacterLibrary.init();
		
		Resources.TEXTURES.add(Resources.VOID,new Sprite("res/textures/void.png",Property.TILE_SIZE,Property.TILE_SIZE).getPixels());
		Resources.TEXTURES.add(Resources.BRICK,new Sprite("res/textures/brick.png",Property.TILE_SIZE,Property.TILE_SIZE).getPixels());
		Resources.TEXTURES.add(Resources.WALL,new Sprite("res/textures/wall.png",Property.TILE_SIZE,Property.TILE_SIZE).getPixels());
		Resources.TEXTURES.add(Resources.KNIGHT,new Sprite("res/textures/knight.png",Property.TILE_SIZE,Property.TILE_SIZE).getPixels());
		Resources.TEXTURES.add(Resources.SKELETON,new Sprite("res/textures/skeleton.png",Property.TILE_SIZE,Property.TILE_SIZE).getPixels());
		Resources.TEXTURES.add(Resources.ENDWALL,new Sprite("res/textures/wall.png",Property.TILE_SIZE,Property.TILE_SIZE).getPixels());
		Resources.TEXTURES.add(Resources.SPONGEBOB,new Sprite("res/textures/spongebob.png",Property.TILE_SIZE,Property.TILE_SIZE).getPixels());
		Resources.TEXTURES.add(Resources.DARTH_SION,new Sprite("res/textures/sion.png",Property.TILE_SIZE,Property.TILE_SIZE).getPixels());
		Resources.TEXTURES.add(Resources.DARTH_VADER,new Sprite("res/textures/vader.png",Property.TILE_SIZE,Property.TILE_SIZE).getPixels());
		Resources.TEXTURES.add(Resources.LUKE,new Sprite("res/textures/luke.png",Property.TILE_SIZE,Property.TILE_SIZE).getPixels());
		Resources.TEXTURES.add(Resources.BOBA,new Sprite("res/textures/boba.png",Property.TILE_SIZE,Property.TILE_SIZE).getPixels());
		
		
		Resources.PORTRAITSx64.add(Resources.NONE,new Sprite("res/textures/lockedskill.png",64,64).getPixels());
		Resources.PORTRAITSx64.add(Resources.KNIGHTMALE,new Sprite("res/textures/knightPortraitMale.png",64,64).getPixels());
		Resources.PORTRAITSx64.add(Resources.SKELETONMALE,new Sprite("res/textures/skeletonPortraitMale.png",64,64).getPixels());
		Resources.PORTRAITSx64.add(Resources.P_VADER,new Sprite("res/textures/p_vader.png",64,64).getPixels());
		Resources.PORTRAITSx64.add(Resources.P_D_SION,new Sprite("res/textures/p_d_sion.png",64,64).getPixels());
		Resources.PORTRAITSx64.add(Resources.P_LUKE,new Sprite("res/textures/p_luke.png",64,64).getPixels());
		Resources.PORTRAITSx64.add(Resources.P_BOBA,new Sprite("res/textures/p_boba.png",64,64).getPixels());
			
		Resources.PORTRAITSx32.add(Resources.MOVEMENT_ACTION,new Sprite("res/textures/movement.png",32,32).getPixels());
		Resources.PORTRAITSx32.add(Resources.ATTACK_ACTION,new Sprite("res/textures/attack.png",32,32).getPixels());
		Resources.PORTRAITSx32.add(Resources.CONFIRM_ACTION,new Sprite("res/textures/confirm.png",32,32).getPixels());
		Resources.PORTRAITSx32.add(Resources.END_TURN_ACTION,new Sprite("res/textures/endTurn.png",32,32).getPixels());
		Resources.PORTRAITSx32.add(Resources.CANCEL_ACTION,new Sprite("res/textures/cancel.png",32,32).getPixels());
		Resources.PORTRAITSx32.add(Resources.UP,new Sprite("res/textures/up.png",32,32).getPixels());
		Resources.PORTRAITSx32.add(Resources.DOWN,new Sprite("res/textures/down.png",32,32).getPixels());
		
		Resources.PORTRAITSx32.add(SkillLibrary.NONE,new Sprite("res/textures/lockedskill.png",32,32).getPixels());
		Resources.PORTRAITSx32.add(SkillLibrary.HATEFUL_SWING,new Sprite("res/textures/hatefulswing.png",32,32).getPixels());	
		Resources.PORTRAITSx32.add(SkillLibrary.WEAPON_THROW,new Sprite("res/textures/weaponthrow.png",32,32).getPixels());	
		Resources.PORTRAITSx32.add(SkillLibrary.FORCE_CHOKE,new Sprite("res/textures/forcechoke.png",32,32).getPixels());	
		Resources.PORTRAITSx32.add(SkillLibrary.MERCILESS_MASSACRE,new Sprite("res/textures/mercilessmassacre.png",32,32).getPixels());	
		Resources.PORTRAITSx32.add(SkillLibrary.RIGHTEOUS_SWING,new Sprite("res/textures/righteousswing.png",32,32).getPixels());	
		Resources.PORTRAITSx32.add(SkillLibrary.FORCE_PUSH,new Sprite("res/textures/forcepush.png",32,32).getPixels());	
		Resources.PORTRAITSx32.add(SkillLibrary.FORCE_PROJECTION,new Sprite("res/textures/forceprojection.png",32,32).getPixels());	
		Resources.PORTRAITSx32.add(SkillLibrary.SHOCKWAVE_JUMP,new Sprite("res/textures/shockwavejump.png",32,32).getPixels());	
		Resources.PORTRAITSx32.add(SkillLibrary.UNSTOPPABLE,new Sprite("res/textures/unstoppable.png",32,32).getPixels());	
		Resources.PORTRAITSx32.add(SkillLibrary.CONDEMN,new Sprite("res/textures/condemn.png",32,32).getPixels());	
		Resources.PORTRAITSx32.add(SkillLibrary.LAST_RESERVE,new Sprite("res/textures/lastreserver.png",32,32).getPixels());	
		Resources.PORTRAITSx32.add(SkillLibrary.FLAMETHROWER,new Sprite("res/textures/flamethrower.png",32,32).getPixels());	
		Resources.PORTRAITSx32.add(SkillLibrary.ROCKET,new Sprite("res/textures/rocket.png",32,32).getPixels());	
		Resources.PORTRAITSx32.add(SkillLibrary.JETPACK_BOOST,new Sprite("res/textures/jetpackboost.png",32,32).getPixels());	
		Resources.PORTRAITSx32.add(SkillLibrary.BACTA_PAD,new Sprite("res/textures/bactapad.png",32,32).getPixels());	
		
		
		
	}
}
