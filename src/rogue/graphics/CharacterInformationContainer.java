package rogue.graphics;

import java.util.Arrays;

import rogue.framework.eventhandling.Connector;
import rogue.framework.eventhandling.Event;
import rogue.framework.resources.Resources;
import rogue.game.world.objects.PlayableCharacter;
import rogue.game.world.objects.PlayableCharacter.CharacterTab;
import rogue.game.world.objects.Skill;
import util.IconRow;
import util.MyColor;
import util.StndColumn;
import util.StndTable;
import util.TextAlignment;

public class CharacterInformationContainer extends InformationContainer{
	
	//general
	private static final int TOTAL_WIDTH 			= 420;
	private static final int TOTAL_HEIGHT 			= 660;
	
	private static final int OFFSET_LEFT			= 1500;
	private static final int OFFSET_TOP				= 0;
	
	private static final int STANDARD_PADDING		= 15;
	
	//header
	private static final int PORTRAIT_X_FROM  		= 10;
	private static final int PORTRAIT_X_UNTIL 		= 73;
	private static final int PORTRAIT_Y_FROM 		= 10;
	private static final int PORTRAIT_Y_UNTIL		= 73;
	private static final int LIFEBAR_WIDTH			= 200;
	private static final int LIFEBAR_HEIGHT			= 20;
	private static final int MANABAR_WIDTH			= 200;
	private static final int MANABAR_HEIGHT			= 20;
	private static final int HEADER_ROW1_Y_FROM		= 10;
	private static final int HEADER_ROW1_Y_UNTIL	= 29;
	private static final int HEADER_ROW2_Y_FROM 	= 32;
	private static final int HEADER_ROW2_Y_UNTIL	= 51;
	private static final int HEADER_ROW3_Y_FROM 	= 54;
	private static final int HEADER_ROW3_Y_UNTIL	= 73;
	private static final int HEADER_COLUMN1_X_FROM 	= 100;
	private static final int HEADER_COLUMN1_X_UNTIL	= 299;
	private static final int HEADER_COLUMN2_X_FROM 	= 320;
	private static final int HEADER_COLUMN2_X_UNTIL	= 419;
	
	//tabs
	private static final int TAB_Y_FROM				= 90;
	private static final int TAB_Y_UNTIL			= 109;
	private static final int TAB1_X_FROM			= 0;
	private static final int TAB1_X_UNTIL			= 83;
	private static final int TAB2_X_FROM			= 84;
	private static final int TAB2_X_UNTIL			= 167;
	private static final int TAB3_X_FROM			= 168;
	private static final int TAB3_X_UNTIL			= 251;
	private static final int TAB4_X_FROM			= 252;
	private static final int TAB4_X_UNTIL			= 335;
	private static final int TAB5_X_FROM			= 336;
	private static final int TAB5_X_UNTIL			= 419;
	private static final int TAB_WIDTH				= 84;
	private static final int TAB_HEIGHT				= 20;
	
	private static final int TAB_INFO_X_FROM		= 5;
	private static final int TAB_INFO_X_UNTIL		= 414;
	private static final int TAB_INFO_Y_FROM		= 125;
	private static final int TAB_INFO_Y_UNTIL		= 654;
	
	//stats
	private static final int STATSTABLE_X_FROM		= 5;
	private static final int STATSTABLE_X_UNTIL		= 414;
	private static final int STATSTABLE_Y_FROM		= 125;
	
	//skills
	private static final int ICON_SIZE				= 32;
	private static final int C_SKILLS_WIDTH			= TOTAL_WIDTH;
	private static final int C_SKILLS_HEIGHT		= STANDARD_PADDING*2+ICON_SIZE;
	private static final int C_SKILLS_ICONS_X_FROM  = 0;
	private static final int C_SKILLS_ICONS_X_UNTIL = C_SKILLS_ICONS_X_FROM+C_SKILLS_WIDTH-1;
	private static final int C_SKILLS_ICONS_Y_FROM  = 125;
	private static final int C_SKILLS_ICONS_Y_UNTIL = C_SKILLS_ICONS_Y_FROM + C_SKILLS_HEIGHT-1;
	
	
	private PlayableCharacter copy = new PlayableCharacter();
	
	
	public CharacterInformationContainer(PlayableCharacter original,Connector connector) {
		super(Resources.PORTRAITSx64.get(original.getPortraitId()),original.getName(),TOTAL_WIDTH,TOTAL_HEIGHT,connector);
		setActiveCharacter(original);
		initialPrint();
	}
	public void checkUdate(PlayableCharacter currentActive) {
		if(!copy.getName().equals(currentActive.getName())) {
			setActiveCharacter(currentActive);
			initialPrint();
		}
		if(!copy.getActiveTab().equals(currentActive.getActiveTab())) {
			printTabs(currentActive.getActiveTab());
			copy.setActiveTab(currentActive.getActiveTab());
		}
	}
	private void initialPrint()	{
		green();
		printPortrait();
		printHeader();
		printTabs(CharacterTab.STATS);
	}
	private void green() {
		for(int i = 0; i < this.pixels.length; i++) {
			this.pixels[i] = MyColor.GREEN.VALUE;
		}
	}
	private void printPortrait() {
		int portraitIndex = 0;
		for(int i = PORTRAIT_Y_FROM; i <= PORTRAIT_Y_UNTIL; i++) {
			for(int j = PORTRAIT_X_FROM; j <= PORTRAIT_X_UNTIL; j++) {
				if(portraitIndex > this.portrait.length)
					break;
				if(portrait[portraitIndex] != -12450784)
					this.pixels[j+i*TOTAL_WIDTH] = this.portrait[portraitIndex];
				portraitIndex++;
			}
		}
		for (int i = PORTRAIT_X_FROM - 1; i < PORTRAIT_X_UNTIL + 2; i++) {
			pixels[i + (PORTRAIT_Y_FROM - 1) * this.TOTAL_WIDTH] = -1;
			pixels[i + (PORTRAIT_Y_UNTIL + 1) * this.TOTAL_WIDTH] = -1;
		}
		for (int i = PORTRAIT_Y_FROM - 1; i < PORTRAIT_Y_UNTIL + 2; i++) {
			pixels[(PORTRAIT_X_FROM - 1) + i * this.TOTAL_WIDTH] = -1;
			pixels[(PORTRAIT_X_UNTIL + 1) + i * this.TOTAL_WIDTH] = -1;
		}	
	}
	private void printHeader() {
		writeLine(copy.getName(), 			HEADER_COLUMN1_X_FROM,HEADER_COLUMN1_X_UNTIL,HEADER_ROW1_Y_FROM,HEADER_ROW1_Y_UNTIL,1,TextAlignment.LEFT,MyColor.BLACK,MyColor.WHITE);
		writeLine(copy.getLevelString(),	HEADER_COLUMN2_X_FROM,HEADER_COLUMN2_X_UNTIL,HEADER_ROW1_Y_FROM,HEADER_ROW1_Y_UNTIL,1,TextAlignment.LEFT,MyColor.BLACK,MyColor.WHITE);
		writeBar(							HEADER_COLUMN1_X_FROM,HEADER_COLUMN1_X_UNTIL,HEADER_ROW2_Y_FROM,HEADER_ROW2_Y_UNTIL,copy.getCurrentResourcePercentage("life"),MyColor.TRUEGREEN);
		fillWithGraphics(					HEADER_COLUMN1_X_FROM,HEADER_COLUMN1_X_UNTIL,HEADER_ROW2_Y_FROM,HEADER_ROW2_Y_UNTIL,getTextLine(copy.getCurrentResourceString("life"), LIFEBAR_WIDTH, LIFEBAR_HEIGHT, 1, MyColor.WHITE),false);
		writeBar(							HEADER_COLUMN1_X_FROM,HEADER_COLUMN1_X_UNTIL,HEADER_ROW3_Y_FROM,HEADER_ROW3_Y_UNTIL,copy.getCurrentResourcePercentage("mana"),MyColor.BLUE);
		fillWithGraphics(					HEADER_COLUMN1_X_FROM,HEADER_COLUMN1_X_UNTIL,HEADER_ROW3_Y_FROM,HEADER_ROW3_Y_UNTIL,getTextLine(copy.getCurrentResourceString("mana"), MANABAR_WIDTH, MANABAR_HEIGHT, 1, MyColor.WHITE),false);
		writeLine(copy.getCurrentResourceString("movement"),HEADER_COLUMN2_X_FROM,HEADER_COLUMN2_X_UNTIL,HEADER_ROW2_Y_FROM,HEADER_ROW2_Y_UNTIL,1,TextAlignment.LEFT,MyColor.BLACK,MyColor.WHITE);
		writeLine(copy.getCurrentResourceString("action"),HEADER_COLUMN2_X_FROM,HEADER_COLUMN2_X_UNTIL,HEADER_ROW3_Y_FROM,HEADER_ROW3_Y_UNTIL,1,TextAlignment.LEFT,MyColor.BLACK,MyColor.WHITE);
	}
	private void printTabs(CharacterTab tab) {
		MyColor skillsBackground = MyColor.DARKGREY;
		MyColor itemsBackground = MyColor.DARKGREY;
		MyColor statsBackground = MyColor.DARKGREY;
		MyColor gearBackground = MyColor.DARKGREY;
		switch(tab) {
		case SKILLS:
			skillsBackground = MyColor.BLACK;
			clearTabInfo();
			printSkills();
			break;
		case ITEMS:
			itemsBackground = MyColor.BLACK;
			clearTabInfo();
			break;
		case STATS:
			statsBackground = MyColor.BLACK;
			clearTabInfo();
			printStats();
			break;
		case GEAR:
			gearBackground = MyColor.BLACK;
			clearTabInfo();
			break;
		default:
			break;
		}
		writeLine("stats", 		TAB1_X_FROM, TAB1_X_UNTIL, TAB_Y_FROM, TAB_Y_UNTIL,1,TextAlignment.CENTER,statsBackground,MyColor.WHITE);
		writeLine("skills", 	TAB2_X_FROM, TAB2_X_UNTIL, TAB_Y_FROM, TAB_Y_UNTIL,1,TextAlignment.CENTER,skillsBackground,MyColor.WHITE);
		writeLine("items", 		TAB3_X_FROM, TAB3_X_UNTIL, TAB_Y_FROM, TAB_Y_UNTIL,1,TextAlignment.CENTER,itemsBackground,MyColor.WHITE);
		writeLine("gear",		TAB4_X_FROM, TAB4_X_UNTIL, TAB_Y_FROM, TAB_Y_UNTIL,1,TextAlignment.CENTER,gearBackground,MyColor.WHITE);
		writeLine("tab5", 		TAB5_X_FROM, TAB5_X_UNTIL, TAB_Y_FROM, TAB_Y_UNTIL,1,TextAlignment.CENTER,MyColor.DARKGREY,MyColor.WHITE);
	
		Event clickStats = new Event();
		clickStats.setEventId("tabChange");
		clickStats.setTab(CharacterTab.STATS);
		
		Event clickSkills = new Event();
		clickSkills.setEventId("tabChange");
		clickSkills.setTab(CharacterTab.SKILLS);
		
		Event clickItems = new Event();
		clickItems.setEventId("tabChange");
		clickItems.setTab(CharacterTab.ITEMS);
		
		Event clickGear = new Event();
		clickGear.setEventId("tabChange");
		clickGear.setTab(CharacterTab.GEAR);
		
		this.connector.addEvent(TAB1_X_FROM+OFFSET_LEFT, TAB_Y_FROM+OFFSET_TOP, TAB_WIDTH, TAB_HEIGHT, clickStats);
		this.connector.addEvent(TAB2_X_FROM+OFFSET_LEFT, TAB_Y_FROM+OFFSET_TOP, TAB_WIDTH, TAB_HEIGHT, clickSkills);
		this.connector.addEvent(TAB3_X_FROM+OFFSET_LEFT, TAB_Y_FROM+OFFSET_TOP, TAB_WIDTH, TAB_HEIGHT, clickItems);
		this.connector.addEvent(TAB4_X_FROM+OFFSET_LEFT, TAB_Y_FROM+OFFSET_TOP, TAB_WIDTH, TAB_HEIGHT, clickGear);
	}
	private void printStats() {
		StndColumn meelee1 = new StndColumn(new String[] {
					"meeleeAtk1",
					Short.toString(copy.getMeeleeAtk1()),
					"meeleeDef1",
					Short.toString(copy.getMeeleeDef1())});
		StndColumn meelee2 = new StndColumn(new String[] {
					"meeleeAtk2",
					Short.toString(copy.getMeeleeAtk2()),
					"meeleeDef2",
					Short.toString(copy.getMeeleeDef2())});
		StndColumn ranged1 = new StndColumn(new String[] {
					"rangedAtk1",
					Short.toString(copy.getRangedAtk1()),
					"rangedDef1",
					Short.toString(copy.getRangedDef1())});
		StndColumn ranged2 = new StndColumn(new String[] {
					"rangedAtk2",
					Short.toString(copy.getRangedAtk2()),
					"rangedDef2",
					Short.toString(copy.getRangedDef2())});
		StndColumn magic1 = new StndColumn(new String[] {
					"magicAtk1",
					Short.toString(copy.getMagicAtk1()),
					"magicDef1",
					Short.toString(copy.getMagicDef1())});
		StndColumn magic2 = new StndColumn(new String[] {
					"magicAtk2",
					Short.toString(copy.getMagicAtk2()),
					"magicDef2",
					Short.toString(copy.getMagicDef2())});
		StndTable statsTable = new StndTable(new StndColumn[]{
					meelee1,meelee2,ranged1,ranged2,magic1,magic2
										}, new int[] {
				    100,100,100,100});
		statsTable.finish();
		int tableHeight= statsTable.getHeight();
		int tableIndex = 0;
		for(int y = STATSTABLE_Y_FROM; y < (STATSTABLE_Y_FROM + tableHeight); y++) {
			for(int x = STATSTABLE_X_FROM; x <= STATSTABLE_X_UNTIL; x++) {
				pixels[x+y*TOTAL_WIDTH] = statsTable.getPixels()[tableIndex];
				tableIndex++;
			}
		}
	}
	private void printSkills() {
		Skill[] characterSkills = this.copy.getSkills();
		byte[] skillIds = new byte[characterSkills.length];
		Event[] skillEvents = new Event[characterSkills.length];
		for(int i = 0; i < characterSkills.length; i++) {
			skillIds[i] = characterSkills[i].getId();
			skillEvents[i] = characterSkills[i].getEvent();
		}
		IconRow characterSkillIconRow = new IconRow(skillEvents, skillIds, C_SKILLS_WIDTH, C_SKILLS_HEIGHT, connector);
		int iconRowIndex=0;
		//print(characterSkillIconRow.getPixels(),C_SKILLS_WIDTH,C_SKILLS_HEIGHT);
		for(int y = C_SKILLS_ICONS_Y_FROM; y <= C_SKILLS_ICONS_Y_UNTIL; y++) {
			for(int x = C_SKILLS_ICONS_X_FROM; x <= C_SKILLS_ICONS_X_UNTIL; x++) {
				pixels[x+y*TOTAL_WIDTH] = characterSkillIconRow.getPixels()[iconRowIndex];
				iconRowIndex++;
			}
		}
	}

	private void setActiveCharacter(PlayableCharacter c) {
		copy.setName(c.getName());
		copy.setLevel(c.getLevel());
		copy.setActiveTab(c.getActiveTab());
		copy.setCurrentLife(c.getCurrentLife());
		copy.setMaxLife(c.getMaxLife());
		copy.setCurrentActions(c.getCurrentActions());
		copy.setMaxActions(c.getMaxActions());
		copy.setCurrentMana(c.getCurrentMana());
		copy.setCurrentMovement(c.getCurrentMovement());
		copy.setMaxMana(c.getMaxMana());
		copy.setMaxMovement(c.getMaxMovement());
		copy.setSkills(c.getSkills());
	}
	private void clearTabInfo() {
		for(int x = TAB_INFO_X_FROM; x <= TAB_INFO_X_UNTIL; x++) {
			for(int y = TAB_INFO_Y_FROM; y <= TAB_INFO_Y_UNTIL; y++) {
				this.pixels[x+y*TOTAL_WIDTH] = MyColor.GREEN.VALUE;
			}
		}
	}
}