package rogue.graphics;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import rogue.framework.eventhandling.Connector;
import rogue.framework.eventhandling.Event;
import rogue.framework.resources.Property;
import rogue.framework.resources.Resources;
import rogue.game.combat.skills.Skill;
import rogue.game.combat.skills.Skill.Effect;
import rogue.game.combat.skills.Skill.Effect.EffectType;
import rogue.game.combat.skills.Skill.Effect.StatChange;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.world.objects.Equipment;
import rogue.game.world.objects.entities.Entity;
import rogue.game.world.objects.entities.Entity.CharacterTab;
import rogue.game.world.objects.entities.PlayableCharacter;
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
	
	private static int STATUSTABLE_X_FROM			= 5;
	private static int STATUSTABLE_X_UNTIL			= 204;
	private static int STATUSTABLE_Y_FROM 			= 255;
	
	private static int STATTABLE_X_FROM				= 205;
	private static int STATTABLE_X_UNTIL			= 404;
	private static int STATTABLE_Y_FROM 			= 255;
	
	//skills
	private static int ICON_SIZE					= 32;
	private static int C_SKILLS_WIDTH 				= 420;
	private static int C_SKILLS_HEIGHT				= STANDARD_PADDING*2+ICON_SIZE;
	private static int C_SKILLS_ICONS_Y_FROM  		= 125;
	private static int C_SKILLS_ICONS_Y_UNTIL 		= C_SKILLS_ICONS_Y_FROM + C_SKILLS_HEIGHT-1;
	private static int C_SKILLS_ICONS_X_FROM  		= 0;
	private static int C_SKILLS_ICONS_X_UNTIL 		= C_SKILLS_ICONS_X_FROM+C_SKILLS_WIDTH-1;
	private static int NEW_ICON_X_FROM 				= 15;
	private static int NEW_DESCR_X_FROM 			= 55;
	
	public static final EntityInformationContainerConfig PLAYER_CONFIG = 
			new EntityInformationContainerConfig("MAIN_CANVAS",420, 600, 1500, 0, new CharacterTab[] {
					CharacterTab.STATS,
					CharacterTab.SKILLS,
//					CharacterTab.ITEMS,
//					CharacterTab.GEAR
			});
	public static final EntityInformationContainerConfig ENTITY_CONFIG = 
			new EntityInformationContainerConfig("SMALL_CANVAS",420, 480, 1500, 600, new CharacterTab[] {
					CharacterTab.STATS,
//					CharacterTab.SKILLS,
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
	public int[] render() {
		initialPrint(copy.getActiveTab());
		return this.pixels;
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
		writeBar(								HEADER_COLUMN1_X_FROM,HEADER_COLUMN1_X_UNTIL,HEADER_ROW2_Y_FROM,HEADER_ROW2_Y_UNTIL,copy.getCurrentResourcePercentage("life"),MyColor.GREEN);
		fillWithGraphics(						HEADER_COLUMN1_X_FROM,HEADER_COLUMN1_X_UNTIL,HEADER_ROW2_Y_FROM,HEADER_ROW2_Y_UNTIL,getTextLine(copy.getCurrentResourceString("life"), LIFEBAR_WIDTH, LIFEBAR_HEIGHT, 1, MyColor.WHITE),false,MyColor.VOID);
		writeBar(								HEADER_COLUMN1_X_FROM,HEADER_COLUMN1_X_UNTIL,HEADER_ROW3_Y_FROM,HEADER_ROW3_Y_UNTIL,copy.getCurrentResourcePercentage("mana"),MyColor.BLUE);
		fillWithGraphics(						HEADER_COLUMN1_X_FROM,HEADER_COLUMN1_X_UNTIL,HEADER_ROW3_Y_FROM,HEADER_ROW3_Y_UNTIL,getTextLine(copy.getCurrentResourceString("mana"), MANABAR_WIDTH, MANABAR_HEIGHT, 1, MyColor.WHITE),false,MyColor.VOID);
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
			click.setEntity(this.copy);
			
			this.connector.addEvent(startX+this.offsetLeft,TAB_Y_FROM+this.offsetTop,tabSize,TAB_HEIGHT,click);
			
			i++;
			startX+=tabSize;
			endX+=tabSize;
		}
	}
	private <K,V> StndTable setUpStatTable(Map<K,V> map, int[] sizes,String[] names) {
		List<StndColumn> lines = new ArrayList<>();
		for(Entry<K,V> entry : map.entrySet()) {
			StndColumn col = new StndColumn(new String[] {
					entry.getKey()+"",
					entry.getValue()+""
			});
			lines.add(col);
		}
		StndTable table = new StndTable(array(lines),this.editor,sizes);
		table.addHeader(new StndColumn(names));
		table.finish();
		
		return table;
	}
	private void printTable(StndTable table, int yFrom, int xFrom, int xUntil) {
		int tableHeight= table.getHeight();
		int tableIndex = 0;
		for(int y = yFrom; y < (yFrom + tableHeight); y++) {
			for(int x = xFrom; x <= xUntil; x++) {
				pixels[x+y*this.width] = table.getPixels()[tableIndex];
				tableIndex++;
			}
		}	
	}
	private StndTable setUpEffectTable(List<Effect> effects,EffectType effect, int[] sizes, String[] names) {
		List<StndColumn> lines = new ArrayList<>();
		for(Effect e : this.copy.getCurrentEffects()) {
			if(e.getType().equals(effect)) {
				String turns = e.getTurns()>=0 ? e.getTurns()+"" : " ";
				if(e.getType().equals(EffectType.STATUS_INFLICTION)) {
					StndColumn column = new StndColumn(new String[] {
							e.getStatus().value(),
							e.getIntensity()+"",
							turns
						});
						lines.add(column);
				}else if(e.getType().equals(EffectType.STAT_CHANGE)){
					StatChange sc = e.getStatChange();
					String[] entries = new String[3];
					if(sc.getProf()!=null) {
						entries[0] = sc.getProf().value();
						entries[1] = sc.getAmnt()+"";
						entries[2] = turns;	
					}
					if(sc.getStat()!=null) {
						entries[0] = sc.getStat().value();
						entries[1] = sc.getAmnt()+"";
						entries[2] = turns;	
					}
					StndColumn column = new StndColumn(entries);
						lines.add(column);
				}
				
			}
		}
		StndTable table = new StndTable(array(lines),this.editor,sizes);
		table.addHeader(new StndColumn(names));
		table.finish();
		return table;
	}
	private void printStats() {
		if(!this.copy.getName().equals("dummy")) {
//			
			StndTable profTable = setUpStatTable(this.copy.getProficiencies(),new int[] {90,30},new String[] {"Proficiency","amnt"});
			printTable(profTable,PROFTABLE_Y_FROM,PROFTABLE_X_FROM,PROFTABLE_X_UNTIL);
			
			StndTable resistTable = setUpStatTable(this.copy.getResistances(),new int[] {90,30},new String[] {"Resistance","amnt"});
			printTable(resistTable,RESISTTABLE_Y_FROM,RESISTTABLE_X_FROM,RESISTTABLE_X_UNTIL);
			
			StndTable multTable = setUpStatTable(this.copy.getMultipliersForTable(),new int[] {90,30},new String[] {"Multiplier","amnt"});
			printTable(multTable,MULTTABLE_Y_FROM,MULTTABLE_X_FROM,MULTTABLE_X_UNTIL);
				
			
			StndTable statusTable = setUpEffectTable(this.copy.getCurrentEffects(), EffectType.STATUS_INFLICTION, new int[] {90,50,50},new String[] {"Status","Power","Turns"});
			printTable(statusTable,STATUSTABLE_Y_FROM,STATUSTABLE_X_FROM,STATUSTABLE_X_UNTIL);
			
			StndTable statTable = setUpEffectTable(this.copy.getCurrentEffects(),EffectType.STAT_CHANGE,new int[] {90,50,50},new String[] {"Stat","Change","Turns"});
			printTable(statTable,STATTABLE_Y_FROM,STATTABLE_X_FROM,STATTABLE_X_UNTIL);
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
		List<Skill> characterSkills = this.copy.getSkills();
		if(characterSkills!=null) {
			int xf = NEW_ICON_X_FROM;
			int xu = NEW_ICON_X_FROM+ICON_SIZE;
			int yf = C_SKILLS_ICONS_Y_FROM;
			int yu = C_SKILLS_ICONS_Y_UNTIL;
			for(Skill s : characterSkills) {
				fillWithGraphics(xf, xu-1, yf, yf+ICON_SIZE-1, Resources.getIcon(s.getId()), true);
				writeLine(s.getName(), NEW_DESCR_X_FROM, C_SKILLS_ICONS_X_UNTIL, yf, yf+STANDARD_PADDING-1,1,TextAlignment.LEFT,MyColor.BLACK,MyColor.WHITE);
				writeLine(s.getDescription(),NEW_DESCR_X_FROM, C_SKILLS_ICONS_X_UNTIL,yf+STANDARD_PADDING,yf+(STANDARD_PADDING*2),1,TextAlignment.LEFT, MyColor.BLACK,MyColor.WHITE);
				if(!s.isPassive()) {
					this.connector.addEvent(xf+this.offsetLeft,+yf+this.offsetTop, ICON_SIZE, ICON_SIZE, s.getEvent());
				}else {
					this.connector.removeEvent(xf+this.offsetLeft,+yf+this.offsetTop, ICON_SIZE, ICON_SIZE);
				}
				yf+=ICON_SIZE;
				
				int furtherDescriptionXfrom = NEW_DESCR_X_FROM;
				if(s.isPassive()) {
					//anything?
				}else {
					if(s.getPower()!=0) {
						String powerString = "Power " + s.getSkillDamage(this.copy);
						writeLine(powerString,furtherDescriptionXfrom,furtherDescriptionXfrom+65,yf,yf+STANDARD_PADDING-1,1,TextAlignment.LEFT,MyColor.BLACK,MyColor.WHITE);
						furtherDescriptionXfrom+=65;
					}
					if(s.getAccuracy()!=100) {
						String accuracyStrig = "Accuracy " + s.getAccuracy();
						writeLine(accuracyStrig,furtherDescriptionXfrom,furtherDescriptionXfrom+85,yf,yf+STANDARD_PADDING-1,1,TextAlignment.LEFT,MyColor.BLACK,MyColor.WHITE);
						furtherDescriptionXfrom+=85;						
					}
					if(s.getManaCost()!=0) {
						String string = "Mana " + s.getManaCost();
						writeLine(string,furtherDescriptionXfrom,furtherDescriptionXfrom+60,yf,yf+STANDARD_PADDING-1,1,TextAlignment.LEFT,MyColor.BLACK,MyColor.WHITE);
						furtherDescriptionXfrom+=60;
					}
					if(s.getActionCost()!=0) {
						String string = "Action " + s.getActionCost();
						writeLine(string,furtherDescriptionXfrom,furtherDescriptionXfrom+65,yf,yf+STANDARD_PADDING-1,1,TextAlignment.LEFT,MyColor.BLACK,MyColor.WHITE);
						furtherDescriptionXfrom+=65;
					}
					if(s.getEffects()!=null&& !s.getEffects().isEmpty()) {
						for(Effect e : s.getEffects()) {
							if(e.getType().equals(EffectType.STATUS_INFLICTION)) {
								String intensity = e.getIntensity()!=0?e.getIntensity()+" ":" ";
								String turns = e.getTurns()!=-1?e.getTurns()+"":"";
								String effectString = e.getStatus().value()+" "+intensity+turns;
								writeLine(effectString, furtherDescriptionXfrom, C_SKILLS_ICONS_X_UNTIL, yf, yf+STANDARD_PADDING,1,TextAlignment.LEFT,MyColor.BLACK,MyColor.WHITE);
								furtherDescriptionXfrom+=60;
							}
							
						}
					}

					
					yf+=ICON_SIZE;
				}
			}
//			int[] skillIds = new int[characterSkills.size()];
//			Event[] skillEvents = new Event[characterSkills.size()];
//			for(int i = 0; i < characterSkills.size(); i++) {
//				if(characterSkills[i].isBlocked()) {
//					skillIds[i]=SkillLibrary.NONE;
//					skillEvents[i]=new Event();
//				}else {
//					skillIds[i] = characterSkills[i].getId();
//					skillEvents[i] = characterSkills[i].getEvent();
//				}
//			}
//			
//			IconRow characterSkillIconRow = new IconRow(skillEvents, skillIds, C_SKILLS_WIDTH, C_SKILLS_HEIGHT);
//			int iconRowIndex=0;
//			//print(characterSkillIconRow.getPixels(),C_SKILLS_WIDTH,C_SKILLS_HEIGHT);
//			for(int y = C_SKILLS_ICONS_Y_FROM; y <= C_SKILLS_ICONS_Y_UNTIL; y++) {
//				for(int x = C_SKILLS_ICONS_X_FROM; x <= C_SKILLS_ICONS_X_UNTIL; x++) {
//					pixels[x+y*this.width] = characterSkillIconRow.getPixels()[iconRowIndex];
//					iconRowIndex++;
//				}
//			}
//			for(Event e : characterSkillIconRow.getEvents()) {
//				this.connector.addEvent(C_SKILLS_ICONS_X_FROM+e.getX()+this.offsetLeft,C_SKILLS_ICONS_Y_FROM+e.getY()+this.offsetTop, ICON_SIZE, ICON_SIZE, e);
//			}
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
		copy.setLifeRegain(c.getLifeRegain());
		copy.setManaRegain(c.getManaRegain());
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
		copy.setCurrentEffects(c.getCurrentEffects());
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
