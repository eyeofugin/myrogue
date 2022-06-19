package util;
import rogue.framework.eventhandling.Connector;
import rogue.framework.eventhandling.Event;
import rogue.framework.resources.Resources;
import rogue.game.combat.skills.Skill;
import rogue.game.world.objects.entities.Entity;
import rogue.graphics.InformationContainer;
public class CharacterCard extends InformationContainer{
	
	public static int CARD_WIDTH = 256;
	public static int CARD_HEIGHT = 400;
	private static int ICON_X = 0;
	private static int ICON_Y = 22;
	private static int ICON_SMALL = 32;
	private static int ICON_SIZE = 256;
	private static int TIER_BORDER_X = 225;
	private static int INFO_BORDER_Y = 300;
	private static int COLORS_X_FROM = 140;
	private static int COLORS_X_UNTIL = 251;
	private static int COLORS_Y_FROM = 273;
	private static int COLORS_Y_UNTIL = 296;
	
	private static int SKILL_X_FROM = 10;
	private static int SKILL_X_UNTIL = 246;
	private static int SKILL_Y_FROM = 310;
	
	private static int SKILLS_X_FROM_1 = 10;
	private static int SKILLS_X_UNTIL_1 = 127;
	private static int SKILLS_X_FROM_2 = 128;
	private static int SKILLS_X_UNTIL_2 = 246;
	private static int SKILLS_Y_FROM_1 = 310;
	private static int SKILLS_Y_UNTIL_1 = 349;
	private static int SKILLS_Y_FROM_2 = 350;
	private static int SKILLS_Y_UNTIL_2= 389;
	
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
			colors();
			skills();
			cardBorders();
			
			event();
		}
	}
	private void name() {
		writeLine(this.entity.getName(), 5, 224, 2, 20,0,TextAlignment.LEFT,MyColor.BLACK,MyColor.WHITE);
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
		writeLine(this.entity.getTier()+"", 226, 255, 2, 20,0,TextAlignment.CENTER,MyColor.BLACK,MyColor.WHITE);
	}
	private void colors() {
		writeLine(this.entity.getColorIcons()+"", COLORS_X_FROM, COLORS_X_UNTIL, COLORS_Y_FROM, COLORS_Y_UNTIL,0,TextAlignment.RIGHT,MyColor.VOID,MyColor.WHITE);
	}
	private void skills() {
		int yoff = SKILL_Y_FROM;
		for(Skill s : this.entity.getSkills()) {
			writeLine(s.getDescription(),SKILL_X_FROM,SKILL_X_UNTIL,yoff,yoff+12,1,TextAlignment.LEFT,MyColor.BLACK,MyColor.WHITE);
			yoff+=15;
		}
//		if(this.entity.getSkills().size()>0) {
//			Skill s = this.entity.getSkills().get(0);
//			fillWithGraphics(SKILLS_X_FROM_1,SKILLS_X_FROM_1+ICON_SMALL-1,SKILLS_Y_FROM_1,SKILLS_Y_FROM_1+ICON_SMALL-1,Resources.ICONSx32.get(s.getId()),true);
//		}
//		if(this.entity.getSkills().size()>1) {
//			Skill s = this.entity.getSkills().get(1);
//			fillWithGraphics(SKILLS_X_FROM_2,SKILLS_X_FROM_2+ICON_SMALL-1,SKILLS_Y_FROM_1,SKILLS_Y_FROM_1+ICON_SMALL-1,Resources.ICONSx32.get(s.getId()),true);
//		}
//		if(this.entity.getSkills().size()>2) {
//			Skill s = this.entity.getSkills().get(2);
//			fillWithGraphics(SKILLS_X_FROM_1,SKILLS_X_FROM_1+ICON_SMALL-1,SKILLS_Y_FROM_2,SKILLS_Y_FROM_2+ICON_SMALL-1,Resources.ICONSx32.get(s.getId()),true);
//		}
//		if(this.entity.getSkills().size()>3) {
//			Skill s = this.entity.getSkills().get(3);
//			fillWithGraphics(SKILLS_X_FROM_2,SKILLS_X_FROM_2+ICON_SMALL-1,SKILLS_Y_FROM_2,SKILLS_Y_FROM_2+ICON_SMALL-1,Resources.ICONSx32.get(s.getId()),true);
//		}
	}
	private void cardBorders() {
		for(int i = 0; i < this.width; i++) {
			this.pixels[i]=MyColor.WHITE.VALUE;
			this.pixels[i+(this.height-1)*this.width]=MyColor.WHITE.VALUE;
		}
		for(int i = 0; i < this.height; i++) {
			this.pixels[i*this.width]=MyColor.WHITE.VALUE;
			this.pixels[this.width-1+i*this.width]=MyColor.WHITE.VALUE;
		}
		for(int y = 0; y < ICON_Y; y++) {
			this.pixels[TIER_BORDER_X+y*this.width] = MyColor.WHITE.VALUE;
		}
		for(int x = 0; x < this.width; x++) {
			this.pixels[x+INFO_BORDER_Y*this.width] = MyColor.WHITE.VALUE;
		}
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
