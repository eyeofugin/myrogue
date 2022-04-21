package util;
import rogue.framework.eventhandling.Connector;
import rogue.framework.eventhandling.Event;
import rogue.framework.resources.Resources;
import rogue.game.world.objects.entities.Entity;
import rogue.graphics.InformationContainer;
public class CharacterCard extends InformationContainer{
	
	public static int CARD_WIDTH = 256;
	public static int CARD_HEIGHT = 400;
	private static int ICON_X = 0;
	private static int ICON_Y = 22;
	private static int ICON_SIZE = 256;
	private Entity entity;
	private Event event;

	public CharacterCard(Entity e,int xanch, int yanch, Connector connector) {
		this.xanchor=xanch;
		this.yanchor=yanch;
		this.connector=connector;
		this.width=CARD_WIDTH;
		this.height=CARD_HEIGHT;
		this.pixels=new int[this.width*this.height];
		this.entity=e;
		this.editor=new TextEditor(Resources.textEditorConfig);
		
	}
	public void finish() {
		if(this.entity!=null) {
			name();
			icon();
			tier();
			border();
			event();
		}
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
	private void name() {
		writeLine(this.entity.getName(), 5, 140, 2, 20,0,TextAlignment.LEFT,MyColor.BLACK,MyColor.WHITE);
	}
	private void icon() {
		int[] characterSprite = Resources.CHARACTERS.get(this.entity.getId());

		fillWithGraphics(ICON_X, ICON_X+ICON_SIZE-1, ICON_Y, ICON_Y+ICON_SIZE-1, resize64to256(characterSprite), true,MyColor.DARKGREY);
	}
	private int[] resize64to256(int[] p) {
		int[] resized = new int[256*256];
		for(int y = 0; y < 256; y++) {
			for(int x = 0; x < 256; x++) {
				resized[x+y*256]=p[(x/4)+(y/4)*64];
			}
		}
		return resized;
	}
	private void tier() {
		writeLine(this.entity.getTierString(), 145, 251, 2, 20,0,TextAlignment.RIGHT,MyColor.BLACK,MyColor.WHITE);
	}
	private void event() {
		Event e = new Event();
		e.setEventId(Connector.CHOOSE_CARD);
		e.setEntity(this.entity);
		this.connector.addEvent(xanchor, yanchor, this.width, this.height, e);
	}
	public Event getEvent() {
		return this.event;
	}
}
