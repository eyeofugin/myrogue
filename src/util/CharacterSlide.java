package util;

import rogue.framework.eventhandling.Connector;
import rogue.framework.eventhandling.Event;
import rogue.framework.resources.Resources;
import rogue.game.world.objects.entities.Entity;
import rogue.game.world.objects.entities.PlayableCharacter;
import rogue.graphics.InformationContainer;

public class CharacterSlide extends InformationContainer{
	private static int SLIDE_WIDTH = 396;
	private static int SLIDE_HEIGHT = 66;
	private static int ICON_SIZE = 64;
	private static int NAME_X = 68;
	private static int NAME_Y = 3;
	private static int NAME_WIDTH = 300;
	private static int NAME_HEIGHT = 25;
	private static int COLORS_X = 70;
	private static int COLORS_Y = 30;
	private static int COLORS_WIDTH=70;
	private static int COLORS_HEIGHT=30;
	private static int TIER_X = 140;
	private static int TIER_Y = 36;
	private static int TIER_WIDTH = 75;
	private static int TIER_HEIGHT = 27;
	private static int SMALL_ICON_X=370;
	private static int SWITCH_Y=37;
	private static int SMALL_ICON_SIZE=16;
	private static int DELETE_Y=11;
	
	private PlayableCharacter e;
	private int id;
	
	public CharacterSlide(int id,PlayableCharacter e,Connector connector) {
		this.id=id;		
		this.width = SLIDE_WIDTH;
		this.height = SLIDE_HEIGHT;
		this.pixels = new int[this.width*this.height];
		this.e = e;
		this.editor= new TextEditor(Resources.textEditorConfig);
		this.connector=connector;
	}
	public CharacterSlide(int id,PlayableCharacter e,int xanch,int yanch,Connector connector) {
		this.id = id;
		this.connector=connector;
		this.xanchor=xanch;
		this.yanchor=yanch;
		this.width = SLIDE_WIDTH;
		this.height = SLIDE_HEIGHT;
		this.pixels = new int[this.width*this.height];
		this.e = e;
		this.editor= new TextEditor(Resources.textEditorConfig);
	}
	public void finish() {
		icon();
		name();
		colors();
		tier();
		arrow();
		delete();
		border();
	}
	private void name() {
		writeLine(this.e.getName(), NAME_X, NAME_X-1+NAME_WIDTH, NAME_Y, NAME_Y-1+NAME_HEIGHT,0,TextAlignment.LEFT,MyColor.BLACK,MyColor.WHITE);
	}
	private void colors() {
		writeLine(this.e.getColorIcons(),COLORS_X,COLORS_X-1+COLORS_WIDTH,COLORS_Y,COLORS_Y-1+COLORS_HEIGHT,0,TextAlignment.LEFT,MyColor.BLACK,MyColor.WHITE);
	}
	private void tier() {
		writeLine(this.e.getTierString(), TIER_X, TIER_X-1+TIER_WIDTH, TIER_Y, TIER_Y-1+TIER_HEIGHT,2,TextAlignment.LEFT,MyColor.BLACK,MyColor.WHITE);
	}
	private void icon() {
		int[] characterSprite = Resources.CHARACTERS.get(this.e.getId());
		fillWithGraphics(1,ICON_SIZE,1,ICON_SIZE,characterSprite,false,MyColor.DARKGREY);
	}
	private void arrow() {
		int[] arrowSprite = Resources.ICONSx32.get(Resources.SWITCH);
		fillWithGraphics(SMALL_ICON_X,SMALL_ICON_X-1+SMALL_ICON_SIZE,SWITCH_Y,SWITCH_Y-1+SMALL_ICON_SIZE,arrowSprite,true);
		Event e = new Event();
		e.setCardnr(this.id);
		e.setEventId(Connector.MOVE_TEAM_MEMBER);
		this.connector.addEvent(this.xanchor+SMALL_ICON_X, this.yanchor+SWITCH_Y, SMALL_ICON_SIZE, SMALL_ICON_SIZE, e);
	}
	private void delete() {
		int[] deleteSprite = Resources.ICONSx32.get(Resources.DELETE);
		fillWithGraphics(SMALL_ICON_X,SMALL_ICON_X-1+SMALL_ICON_SIZE,DELETE_Y,DELETE_Y-1+SMALL_ICON_SIZE,deleteSprite,true);
		Event deleteCharacterSlide = new Event();
		deleteCharacterSlide.setCardnr(this.id);
		deleteCharacterSlide.setEventId(Connector.DELETE_CHARACTER_SLIDE);
		Event requestConfirmation = new Event();
		requestConfirmation.setEventId(Connector.REQUEST_CONFIRMATION);
		requestConfirmation.setAfterConfirmEvent(deleteCharacterSlide);
		this.connector.addEvent(this.xanchor+SMALL_ICON_X, this.yanchor+DELETE_Y, SMALL_ICON_SIZE, SMALL_ICON_SIZE,requestConfirmation);
		
	}
	private void border() {
		for(int i = 0; i < this.width; i++) {
			this.pixels[i]=MyColor.WHITE.VALUE;
			this.pixels[i+(this.height-1)*this.width]=MyColor.WHITE.VALUE;
		}
		for(int i = 0; i < this.height; i++) {
			this.pixels[i*this.width]=MyColor.WHITE.VALUE;
			this.pixels[this.width-1+i*this.width]=MyColor.WHITE.VALUE;
		}
	}
	public void setXanch(int xanch) {
		this.xanchor=xanch;
	}
	public void setYanch(int yanch) {
		this.yanchor=yanch;
	}
	public int getId() {
		return this.id;
	}
	public PlayableCharacter getPC() {
		return this.e;
	}
}
