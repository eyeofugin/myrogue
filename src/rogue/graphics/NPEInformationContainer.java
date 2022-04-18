package rogue.graphics;

import rogue.framework.eventhandling.Connector;
import rogue.framework.resources.Resources;
import rogue.game.world.objects.entities.Entity;
import util.TextEditor.TextEditorConfig;

public class NPEInformationContainer extends InformationContainer{

	//general
	private static int TOTAL_WIDTH 			= 420;
	private static int TOTAL_HEIGHT 		= 420;
	
	private static int OFFSET_LEFT 			= 1500;
	private static int OFFSET_TOP			= 660;
	
	private static int STANDARD_PADDING 	= 15;
	
	//tabs
	private static int TAB1_X_FROM			= 0;
	private static int TAB1_X_UNTIL			= 83;
	private static int TAB2_X_FROM			= 84;
	private static int TAB2_X_UNTIL			= 167;
	private static int TAB3_X_FROM			= 168;
	private static int TAB3_X_UNTIL			= 251;
	private static int TAB4_X_FROM			= 252;
	private static int TAB4_X_UNTIL			= 335;
	private static int TAB5_X_FROM			= 336;
	private static int TAB5_X_UNTIL			= 419;
	
	//skills
	private static int ICON_SIZE				= 32;
	private static int C_SKILLS_WIDTH			= TOTAL_WIDTH;
	private static int C_SKILLS_HEIGHT			= STANDARD_PADDING*2+ICON_SIZE;
	private static int C_SKILLS_ICONS_X_FROM  	= 0;
	private static int C_SKILLS_ICONS_X_UNTIL 	= C_SKILLS_ICONS_X_FROM+C_SKILLS_WIDTH-1;
	private static int C_SKILLS_ICONS_Y_FROM  	= 125;
	private static int C_SKILLS_ICONS_Y_UNTIL 	= C_SKILLS_ICONS_Y_FROM + C_SKILLS_HEIGHT-1;
	
	private Entity copy = new Entity();
	
	public NPEInformationContainer(Entity original,TextEditorConfig conf, Connector connector) {
		super(Resources.PORTRAITSx64.get(original.getPortraitId()),original.getName(),new int[0],conf,connector);
		setActiveEntity(original);
		//initialPrint();
	}
	private void initialPrint() {
		//green();
		//printPortrait();
		//printHeader(copy);
		//printTabs(CharacterTab.STATS);
	}
	
	
	
	private void setActiveEntity(Entity e){
		copy.setName(e.getName());
		copy.setLevel(e.getLevel());
		copy.setActiveTab(e.getActiveTab());
		copy.setCurrentLife(e.getCurrentLife());
		copy.setMaxLife(e.getMaxLife());
		copy.setCurrentActions(e.getCurrentActions());
		copy.setMaxActions(e.getMaxActions());
		copy.setCurrentMana(e.getCurrentMana());
		copy.setCurrentMovement(e.getCurrentMovement());
		copy.setMaxMana(e.getMaxMana());
		copy.setMaxMovement(e.getMaxMovement());
		copy.setSkills(e.getSkills());
		this.portrait = Resources.PORTRAITSx64.get(e.getPortraitId());
	}
	
}
