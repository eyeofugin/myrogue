package rogue.graphics;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import rogue.framework.eventhandling.Connector;
import rogue.framework.eventhandling.Event;
import rogue.framework.resources.Resources;
import rogue.game.combat.skills.Skill;
import rogue.game.combat.skills.Skill.DamageType;
import rogue.game.world.objects.Entity;
import rogue.game.world.objects.Entity.CharacterTab;
import rogue.game.world.objects.Entity.Proficiency;
import rogue.game.world.objects.Equipment;
import rogue.game.world.objects.PlayableCharacter;
import util.IconRow;
import util.MyColor;
import util.StndColumn;
import util.StndTable;
import util.TextAlignment;
import util.TextEditor.TextEditorConfig;

public class EntityInformationContainer extends InformationContainer{
	
	//general
	
	private static int STANDARD_PADDING				= 15;
	
	//header
	private static int PORTRAIT_X_FROM  			= 10;
	private static int PORTRAIT_X_UNTIL 			= 73;
	private static int PORTRAIT_Y_FROM 				= 10;
	private static int PORTRAIT_Y_UNTIL				= 73;
	private static int LIFEBAR_WIDTH				= 200;
	private static int LIFEBAR_HEIGHT				= 20;
	private static int MANABAR_WIDTH				= 200;
	private static int MANABAR_HEIGHT				= 20;
	private static int HEADER_ROW1_Y_FROM			= 10;
	private static int HEADER_ROW1_Y_UNTIL			= 29;
	private static int HEADER_ROW2_Y_FROM 			= 32;
	private static int HEADER_ROW2_Y_UNTIL			= 51;
	private static int HEADER_ROW3_Y_FROM 			= 54;
	private static int HEADER_ROW3_Y_UNTIL			= 73;
	private static int HEADER_COLUMN1_X_FROM 		= 100;
	private static int HEADER_COLUMN1_X_UNTIL		= 299;
	private static int HEADER_COLUMN2_X_FROM 		= 320;
	private static int HEADER_COLUMN2_X_UNTIL		= 419;
	
	//tabs
	private static int TAB_Y_FROM					= 90;
	private static int TAB_Y_UNTIL					= 109;
	private static int TAB_HEIGHT					= 20;
	
	private static int TAB_INFO_X_FROM				= 5;
	private static int TAB_INFO_X_UNTIL				= 414;
	private static int TAB_INFO_Y_FROM				= 125;
	private static int TAB_INFO_Y_UNTIL				= 654;
	
	//stats
	private static int PROFTABLE_X_FROM				= 5;
	private static int PROFTABLE_X_UNTIL			= 134;
	private static int PROFTABLE_Y_FROM				= 125;
	
	private static int RESISTTABLE_X_FROM			= 135;
	private static int RESISTTABLE_X_UNTIL			= 264;
	private static int RESISTTABLE_Y_FROM 			= 125;
	
	private static int MULTTABLE_X_FROM				= 265;
	private static int MULTTABLE_X_UNTIL			= 394;
	private static int MULTTABLE_Y_FROM 			= 125;
	
	//skills
	private static int ICON_SIZE					= 32;
	private static int C_SKILLS_WIDTH 				= 420;
	private static int C_SKILLS_HEIGHT				= STANDARD_PADDING*2+ICON_SIZE;
	private static int C_SKILLS_ICONS_Y_FROM  		= 125;
	private static int C_SKILLS_ICONS_Y_UNTIL 		= C_SKILLS_ICONS_Y_FROM + C_SKILLS_HEIGHT-1;
	private static int C_SKILLS_ICONS_X_FROM  		= 0;
	private static int C_SKILLS_ICONS_X_UNTIL 		= C_SKILLS_ICONS_X_FROM+C_SKILLS_WIDTH-1;
	
	public static final EntityInformationContainerConfig PLAYER_CONFIG = 
			new EntityInformationContainerConfig("MAIN_CANVAS",420, 660, 1500, 0, new CharacterTab[] {
					CharacterTab.STATS,
					CharacterTab.SKILLS,
					CharacterTab.ITEMS,
					CharacterTab.GEAR
			});
	public static final EntityInformationContainerConfig ENTITY_CONFIG = 
			new EntityInformationContainerConfig("SMALL_CANVAS",420, 420, 1500, 660, new CharacterTab[] {
					CharacterTab.STATS,
					CharacterTab.SKILLS,
			});
	
	private String prefix;
	private Entity copy = new PlayableCharacter();
	private CharacterTab[] tabs; 
	
	public EntityInformationContainer(Entity original,EntityInformationContainerConfig config,TextEditorConfig conf, Connector connector) {
		super(Resources.PORTRAITSx64.get(original.getPortraitId()),original.getName(),getDimensions(config),conf,connector);
		applyConfig(config);
		setActiveCharacter(original);
		initialPrint(CharacterTab.STATS);
	}
	public void checkUdate(Entity currentActive) {
		setActiveCharacter(currentActive);
		initialPrint(currentActive.getActiveTab());
	}
	private void initialPrint(CharacterTab tab)	{
		clear();
		printPortrait();
		printHeader();
		printTabs(tab);
	}
	private void thisGreen() {
		for(int i =0; i < this.pixels.length;i++) {
			this.pixels[i] = MyColor.TRUEGREEN.VALUE;
		}
	}
	private void printPortrait() {
		int portraitIndex = 0;
		for(int i = PORTRAIT_Y_FROM; i <= PORTRAIT_Y_UNTIL; i++) {
			for(int j = PORTRAIT_X_FROM; j <= PORTRAIT_X_UNTIL; j++) {
				if(portraitIndex > this.portrait.length)
					break;
				if(portrait[portraitIndex] != -12450784)
					this.pixels[j+i*this.width] = this.portrait[portraitIndex];
				portraitIndex++;
			}
		}
		for (int i = PORTRAIT_X_FROM - 1; i < PORTRAIT_X_UNTIL + 2; i++) {
			pixels[i + (PORTRAIT_Y_FROM - 1) * this.width] = -1;
			pixels[i + (PORTRAIT_Y_UNTIL + 1) * this.width] = -1;
		}
		for (int i = PORTRAIT_Y_FROM - 1; i < PORTRAIT_Y_UNTIL + 2; i++) {
			pixels[(PORTRAIT_X_FROM - 1) + i * this.width] = -1;
			pixels[(PORTRAIT_X_UNTIL + 1) + i * this.width] = -1;
		}
	}
	private void printHeader() {
		writeLine(this.copy.getName(), 			HEADER_COLUMN1_X_FROM,HEADER_COLUMN1_X_UNTIL,HEADER_ROW1_Y_FROM,HEADER_ROW1_Y_UNTIL,1,TextAlignment.LEFT,MyColor.BLACK,MyColor.WHITE);
		writeLine(this.copy.getLevelString(),	HEADER_COLUMN2_X_FROM,HEADER_COLUMN2_X_UNTIL,HEADER_ROW1_Y_FROM,HEADER_ROW1_Y_UNTIL,1,TextAlignment.LEFT,MyColor.BLACK,MyColor.WHITE);
		writeBar(								HEADER_COLUMN1_X_FROM,HEADER_COLUMN1_X_UNTIL,HEADER_ROW2_Y_FROM,HEADER_ROW2_Y_UNTIL,copy.getCurrentResourcePercentage("life"),MyColor.TRUEGREEN);
		fillWithGraphics(						HEADER_COLUMN1_X_FROM,HEADER_COLUMN1_X_UNTIL,HEADER_ROW2_Y_FROM,HEADER_ROW2_Y_UNTIL,getTextLine(copy.getCurrentResourceString("life"), LIFEBAR_WIDTH, LIFEBAR_HEIGHT, 1, MyColor.BLACK),false);
		writeBar(								HEADER_COLUMN1_X_FROM,HEADER_COLUMN1_X_UNTIL,HEADER_ROW3_Y_FROM,HEADER_ROW3_Y_UNTIL,copy.getCurrentResourcePercentage("mana"),MyColor.BLUE);
		fillWithGraphics(						HEADER_COLUMN1_X_FROM,HEADER_COLUMN1_X_UNTIL,HEADER_ROW3_Y_FROM,HEADER_ROW3_Y_UNTIL,getTextLine(copy.getCurrentResourceString("mana"), MANABAR_WIDTH, MANABAR_HEIGHT, 1, MyColor.BLACK),false);
		writeLine(this.copy.getCurrentResourceString("movement"),HEADER_COLUMN2_X_FROM,HEADER_COLUMN2_X_UNTIL,HEADER_ROW2_Y_FROM,HEADER_ROW2_Y_UNTIL,1,TextAlignment.LEFT,MyColor.BLACK,MyColor.WHITE);
		writeLine(this.copy.getCurrentResourceString("action"),HEADER_COLUMN2_X_FROM,HEADER_COLUMN2_X_UNTIL,HEADER_ROW3_Y_FROM,HEADER_ROW3_Y_UNTIL,1,TextAlignment.LEFT,MyColor.BLACK,MyColor.WHITE);
	}
	private void printTabs(CharacterTab tab) {
		MyColor[] tabColors = new MyColor[] {
				 MyColor.DARKGREY,
				 MyColor.DARKGREY,
				 MyColor.DARKGREY,
				 MyColor.DARKGREY
		};
		switch(tab) {
		case STATS:
			tabColors[0] = MyColor.BLACK;
			//clearTabInfo();
			printStats();
			break;
		case SKILLS:
			tabColors[1] = MyColor.BLACK;
//			clearTabInfo();
			printSkills();
			break;
		case ITEMS:
			tabColors[2] = MyColor.BLACK;
//			clearTabInfo();
			break;
		case GEAR:
			tabColors[3] = MyColor.BLACK;
//			clearTabInfo();
			break;
		default:
			break;
		}
		
		int tabSize = this.width / this.tabs.length;
		int startX = 0;
		int endX = startX + tabSize -1;
		int i = 0;
		
		for(CharacterTab t: this.tabs) {
			//System.out.println("tab " + (i+1) + " ; x:" + startX + ",xbis:"+endX);
			writeLine(t.name(),startX,endX,TAB_Y_FROM,TAB_Y_UNTIL,1,TextAlignment.CENTER,tabColors[i],MyColor.WHITE);
			
			Event click = new Event();
			click.setEventId(this.prefix+this.connector.TAB_CHANGE);
			click.setTab(this.tabs[i]);
			click.setObject(this.copy);
			
			this.connector.addEvent(startX+this.offsetLeft,TAB_Y_FROM+this.offsetTop,tabSize,TAB_HEIGHT,click);
			
			i++;
			startX+=tabSize;
			endX+=tabSize;
		}
	}
	private void printStats() {
		if(!this.copy.getName().equals("dummy")) {
			List<StndColumn> profLines = new ArrayList<>();
			for(Entry<Proficiency,Integer> entry : this.copy.getProficiencies().entrySet()) {
				StndColumn col = new StndColumn(new String[] {
						entry.getKey().value(),
						entry.getValue()+""
				});
				profLines.add(col);
			}
			StndTable profTable = new StndTable(array(profLines),this.editor,new int[] {90,30});
			profTable.addHeader(new StndColumn(new String[] {"Proficiency","amnt"}));
			profTable.finish();
			
			List<StndColumn> resistLines = new ArrayList<>();
			for(Entry<DamageType,Integer> entry : this.copy.getResistances().entrySet()) {
				StndColumn col = new StndColumn(new String[] {
						entry.getKey().value(),
						entry.getValue()+""
				});
				resistLines.add(col);
			}
			StndTable resistTable = new StndTable(array(resistLines),this.editor,new int[] {90,30});
			resistTable.addHeader(new StndColumn(new String[] {"Resistance","amnt"}));
			resistTable.finish();
			
			List<StndColumn> multLines = new ArrayList<>();
			for(Entry<DamageType,Double> entry : this.copy.getMultipliers().entrySet()) {
				StndColumn col = new StndColumn(new String[] {
						entry.getKey().value(),
						entry.getValue()+""
				});
				multLines.add(col);
			}
			StndTable multTable = new StndTable(array(multLines),this.editor,new int[] {90,30});
			multTable.addHeader(new StndColumn(new String[] {"Multiplier","amnt"}));
			multTable.finish();
			
			int tableHeight= profTable.getHeight();
			int tableIndex = 0;
			for(int y = PROFTABLE_Y_FROM; y < (PROFTABLE_Y_FROM + tableHeight); y++) {
				for(int x = PROFTABLE_X_FROM; x <= PROFTABLE_X_UNTIL; x++) {
					pixels[x+y*this.width] = profTable.getPixels()[tableIndex];
					tableIndex++;
				}
			}	
			
			tableHeight= resistTable.getHeight();
			tableIndex = 0;
			for(int y = RESISTTABLE_Y_FROM; y < (RESISTTABLE_Y_FROM + tableHeight); y++) {
				for(int x = RESISTTABLE_X_FROM; x <= RESISTTABLE_X_UNTIL; x++) {
					pixels[x+y*this.width] = resistTable.getPixels()[tableIndex];
					tableIndex++;
				}
			}	
			
			tableHeight= multTable.getHeight();
			tableIndex = 0;
			for(int y = MULTTABLE_Y_FROM; y < (MULTTABLE_Y_FROM + tableHeight); y++) {
				for(int x = MULTTABLE_X_FROM; x <= MULTTABLE_X_UNTIL; x++) {
					pixels[x+y*this.width] = multTable.getPixels()[tableIndex];
					tableIndex++;
				}
			}	
		}
	}
	private StndColumn[] array(List<StndColumn> columns) {
		StndColumn[] asArray = new StndColumn[columns.size()];
		for(int i = 0; i < columns.size(); i++) {
			asArray[i] = columns.get(i);
		}
		return asArray;
	}
	private void printSkills() {
		Skill[] characterSkills = this.copy.getSkills();
		if(characterSkills!=null) {
			int[] skillIds = new int[characterSkills.length];
			Event[] skillEvents = new Event[characterSkills.length];
			for(int i = 0; i < characterSkills.length; i++) {
				skillIds[i] = characterSkills[i].getId();
				skillEvents[i] = characterSkills[i].getEvent();
			}
			IconRow characterSkillIconRow = new IconRow(skillEvents, skillIds, C_SKILLS_WIDTH, C_SKILLS_HEIGHT);
			int iconRowIndex=0;
			//print(characterSkillIconRow.getPixels(),C_SKILLS_WIDTH,C_SKILLS_HEIGHT);
			for(int y = C_SKILLS_ICONS_Y_FROM; y <= C_SKILLS_ICONS_Y_UNTIL; y++) {
				for(int x = C_SKILLS_ICONS_X_FROM; x <= C_SKILLS_ICONS_X_UNTIL; x++) {
					pixels[x+y*this.width] = characterSkillIconRow.getPixels()[iconRowIndex];
					iconRowIndex++;
				}
			}
			for(Event e : characterSkillIconRow.getEvents()) {
				this.connector.addEvent(C_SKILLS_ICONS_X_FROM+e.getX()+this.offsetLeft,C_SKILLS_ICONS_Y_FROM+e.getY()+this.offsetTop, ICON_SIZE, ICON_SIZE, e);
			}
		}
	}
	
	private void printItems() {
		List<Equipment> equipments = this.copy.getEquipments();
	
		for(int i = 0; i < equipments.size(); i++) {
			
		}
		
	}
	private void setActiveCharacter(Entity c) {
		//System.out.println(c);
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
		copy.setProficiencies(c.getProficiencies());
		copy.setResistances(c.getResistances());
		copy.setMultipliers(c.getMultipliers());
		this.portrait = Resources.PORTRAITSx64.get(c.getPortraitId());
	}
	private static int[] getDimensions(EntityInformationContainerConfig e){
		return new int[] {e.getWidth(),e.getHeight()};
	}
	private void applyConfig(EntityInformationContainerConfig config) {
	
		this.prefix = config.prefix;
		this.tabs = config.getTabs();
		this.offsetLeft = config.getOffsetLeft();
		this.offsetTop = config.getOffsetTop();
	}
	private void clearTabInfo() {
		for(int x = TAB_INFO_X_FROM; x <= TAB_INFO_X_UNTIL; x++) {
			for(int y = TAB_INFO_Y_FROM; y <= TAB_INFO_Y_UNTIL; y++) {
				this.pixels[x+y*this.width] = MyColor.GREEN.VALUE;
			}
		}
	}
	public static class EntityInformationContainerConfig {
		
		private final int width;
		private final int height;
		private final int offsetLeft;
		private final int offsetTop;
		private final String prefix;
		private final CharacterTab[] tabs;
		
		public EntityInformationContainerConfig(String prefix, int width, int height, int offsetLeft, int offsetTop, CharacterTab[] tabs) {
			super();
			this.width = width;
			this.height = height;
			this.offsetLeft = offsetLeft;
			this.offsetTop = offsetTop;
			this.tabs = tabs;
			this.prefix = prefix;
		}

		public int getWidth() {
			return width;
		}

		public int getHeight() {
			return height;
		}

		public int getOffsetLeft() {
			return offsetLeft;
		}

		public int getOffsetTop() {
			return offsetTop;
		}

		public CharacterTab[] getTabs() {
			return tabs;
		}
	}
	


	public String getPrefix() {
		return this.prefix;
	}
}
