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
		Resources.TEXTURES.add(Resources.ENDWALL,new Sprite("res/textures/wall.png",Property.TILE_SIZE,Property.TILE_SIZE).getPixels());
		Resources.TEXTURES.add(Resources.MEADOW,new Sprite("res/textures/meadow.png",Property.TILE_SIZE,Property.TILE_SIZE).getPixels());
		Resources.TEXTURES.add(Resources.TREE,new Sprite("res/textures/tree.png",Property.TILE_SIZE,Property.TILE_SIZE).getPixels());
		
		Resources.CHARACTERS.add(Resources.KNIGHT,new Sprite("res/characters/knight.png",Property.TILE_SIZE,Property.TILE_SIZE).getPixels());
		Resources.CHARACTERS.add(Resources.SKELETON,new Sprite("res/characters/skeleton.png",Property.TILE_SIZE,Property.TILE_SIZE).getPixels());
		Resources.CHARACTERS.add(Resources.SPONGEBOB,new Sprite("res/characters/spongebob.png",Property.TILE_SIZE,Property.TILE_SIZE).getPixels());
		Resources.CHARACTERS.add(Resources.DARTH_SION,new Sprite("res/characters/sion.png",Property.TILE_SIZE,Property.TILE_SIZE).getPixels());
		Resources.CHARACTERS.add(Resources.DARTH_VADER,new Sprite("res/characters/vader.png",Property.TILE_SIZE,Property.TILE_SIZE).getPixels());
		Resources.CHARACTERS.add(Resources.LUKE,new Sprite("res/characters/luke.png",Property.TILE_SIZE,Property.TILE_SIZE).getPixels());
		Resources.CHARACTERS.add(Resources.BOBA,new Sprite("res/characters/boba.png",Property.TILE_SIZE,Property.TILE_SIZE).getPixels());
		
		Resources.PORTRAITSx64.add(Resources.DUMMY,new Sprite("res/skills/lockedskill.png",64,64).getPixels());
		Resources.PORTRAITSx64.add(Resources.SKELETONMALE,new Sprite("res/portraits/skeletonPortraitMale.png",64,64).getPixels());
		Resources.PORTRAITSx64.add(Resources.P_BOBA,new Sprite("res/portraits/p_boba.png",64,64).getPixels());
		Resources.PORTRAITSx64.add(Resources.P_VADER,new Sprite("res/portraits/p_vader.png",64,64).getPixels());
		Resources.PORTRAITSx64.add(Resources.P_D_SION,new Sprite("res/portraits/p_d_sion.png",64,64).getPixels());
		Resources.PORTRAITSx64.add(Resources.P_LUKE,new Sprite("res/portraits/p_luke.png",64,64).getPixels());
			
		Resources.PORTRAITSx32.add(Resources.MOVEMENT_ACTION,new Sprite("res/icons/movement.png",32,32).getPixels());
		Resources.PORTRAITSx32.add(Resources.ATTACK_ACTION,new Sprite("res/icons/attack.png",32,32).getPixels());
		Resources.PORTRAITSx32.add(Resources.CONFIRM_ACTION,new Sprite("res/icons/confirm.png",32,32).getPixels());
		Resources.PORTRAITSx32.add(Resources.END_TURN_ACTION,new Sprite("res/icons/endTurn.png",32,32).getPixels());
		Resources.PORTRAITSx32.add(Resources.CANCEL_ACTION,new Sprite("res/icons/cancel.png",32,32).getPixels());
		Resources.PORTRAITSx32.add(Resources.UP,new Sprite("res/icons/up.png",32,32).getPixels());
		Resources.PORTRAITSx32.add(Resources.DOWN,new Sprite("res/icons/down.png",32,32).getPixels());
		
		Resources.PORTRAITSx32.add(SkillLibrary.NONE,new Sprite("res/skills/lockedskill.png",32,32).getPixels());
		Resources.PORTRAITSx32.add(SkillLibrary.HATEFUL_SWING,new Sprite("res/skills/hatefulswing.png",32,32).getPixels());	
		Resources.PORTRAITSx32.add(SkillLibrary.WEAPON_THROW,new Sprite("res/skills/weaponthrow.png",32,32).getPixels());	
		Resources.PORTRAITSx32.add(SkillLibrary.FORCE_CHOKE,new Sprite("res/skills/forcechoke.png",32,32).getPixels());	
		Resources.PORTRAITSx32.add(SkillLibrary.MERCILESS_MASSACRE,new Sprite("res/skills/mercilessmassacre.png",32,32).getPixels());	
		Resources.PORTRAITSx32.add(SkillLibrary.RIGHTEOUS_SWING,new Sprite("res/skills/righteousswing.png",32,32).getPixels());	
		Resources.PORTRAITSx32.add(SkillLibrary.FORCE_PUSH,new Sprite("res/skills/forcepush.png",32,32).getPixels());	
		Resources.PORTRAITSx32.add(SkillLibrary.FORCE_PROJECTION,new Sprite("res/skills/forceprojection.png",32,32).getPixels());	
		Resources.PORTRAITSx32.add(SkillLibrary.SHOCKWAVE_JUMP,new Sprite("res/skills/shockwavejump.png",32,32).getPixels());	
		Resources.PORTRAITSx32.add(SkillLibrary.UNSTOPPABLE,new Sprite("res/skills/unstoppable.png",32,32).getPixels());	
		Resources.PORTRAITSx32.add(SkillLibrary.CONDEMN,new Sprite("res/skills/condemn.png",32,32).getPixels());	
		Resources.PORTRAITSx32.add(SkillLibrary.LAST_RESERVE,new Sprite("res/skills/lastreserver.png",32,32).getPixels());	
		Resources.PORTRAITSx32.add(SkillLibrary.FLAMETHROWER,new Sprite("res/skills/flamethrower.png",32,32).getPixels());	
		Resources.PORTRAITSx32.add(SkillLibrary.ROCKET,new Sprite("res/skills/rocket.png",32,32).getPixels());	
		Resources.PORTRAITSx32.add(SkillLibrary.JETPACK_BOOST,new Sprite("res/skills/jetpackboost.png",32,32).getPixels());	
		Resources.PORTRAITSx32.add(SkillLibrary.BACTA_PAD,new Sprite("res/skills/bactapad.png",32,32).getPixels());	
		
		
		
	}
}
