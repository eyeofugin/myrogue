package rogue.game.world.objects;

import java.util.ArrayList;
import java.util.List;

import rogue.framework.eventhandling.Connector;
import rogue.framework.eventhandling.Event;
import rogue.framework.resources.Property;
import rogue.framework.resources.Resources;
import rogue.graphics.InformationContainer;
import util.MyColor;
import util.TextAlignment;
import util.TextEditor.TextEditorConfig;

public class BattleLog extends InformationContainer{
	
	private int amntRows;
	private List<String> allRows = new ArrayList<>();
	private int split=0;
	private int charHeight;
	private int charWidth;
	private int from=0;
	private int maxScroll=0;
	
	private static final int BUFFER = 5;
	
	public BattleLog(TextEditorConfig conf,Connector connector) {
		super(getDimensions(),conf,connector);
		this.charHeight=conf.getCharHeight();
		this.charWidth=conf.getCharWidth();
		initLogRows();
		//this.split=this.width;
		this.split=getSplit();
		render();
	}
	
	public void render() {
		this.pixels = new int[this.width*this.height];
		int yCurrent = 0;
		for(int i = from; i < Math.min(allRows.size(),this.from+this.amntRows);i++) {
			writeLine(this.allRows.get(i), 0, this.width, yCurrent, yCurrent+Property.BASELINEDISTANCE+this.charHeight, 0, TextAlignment.LEFT,MyColor.BLACK,MyColor.WHITE);
			yCurrent+=Property.BASELINEDISTANCE+this.charHeight;
		}
		renderButtons();
	}
	
	public void addRow(String s) {
		String[] splittedOnRows = splitOnRows(s);
		for(String ss : splittedOnRows) {
			allRows.add(ss);
		}
		update();
	}
	public void formulateUse(String skill, String user) {
		addRow(user+" used " +skill +".");
	}
	public void formulateEffect(String target, int damage) {
		addRow(target + " was dealt " + damage + " damage.");
	}
	public void formulateHeal(String target, int damage) {
		addRow(target + " healed " + damage + " damage.");
	}
	public void formulateMiss() {
		addRow("Missed.");
	}
	public void formulateIndesctructible(String target) {
		addRow(target + " is indesctructible.");
	}
	public void formulate(String target, String condition) {
		addRow(target +" is " + condition + ".");
	}
	public void formulateDeath(String target) {
		addRow(target + " died.");
	}
	private static int[] getDimensions() {
		return new int[] {Property.LOG_WIDTH,Property.LOG_HEIGHT};
	}
	private void initLogRows() {
		int sizeOfOneRow = Property.BASELINEDISTANCE+this.charHeight;
		this.amntRows = this.height/sizeOfOneRow;
	}
	
	

	private String[] splitOnRows(String s) {
		int stringWidth = getStringWidth(s);
		if(stringWidth>this.split) {
			return splitAt(this.split,s);
		}else {
			return new String[] {s};
		}
	}
	private int getStringWidth(String s) {
		return s.length()*(Property.BASECHARDISTANCE+this.charWidth) + BUFFER;//5 is gui buffer
	}
	private String[] splitAt(int split, String s) {
		
		List<String> result = new ArrayList<>();
		String[] splittedWhole = s.split(" ");
		while(hasEntries(splittedWhole)) {
			result.add(getNextRow(splittedWhole,split));
		}
		return getArray(result);

	}
	private String getNextRow(String[] splitted, int split) {
		String newS = "";
		int index = getFirstFilled(splitted);
		while(getStringWidth(newS) + getStringWidth(splitted[index]) + BUFFER < split) {
			newS+=splitted[index]+" ";
			splitted[index]=" ";
			index++;
			if(index==splitted.length) {
				return newS;
			}
		}
		return newS;
	}
	private boolean hasEntries(String[] splitted) {
		for(String s : splitted) {
			if(!s.equals(" ")) {
				return true;
			}
		}
		return false;
	}
	private int getFirstFilled(String[] split) {
		for(int i = 0; i < split.length; i++) {
			if(!split[i].equals(" ")) {
				return i;
			}
		}
		return 0;
	}
	private String[] getArray(List<String> list) {
		String[] result = new String[list.size()];
		for(int i = 0; i < list.size(); i++) {
			result[i] = list.get(i);
		}
		return result;
	}
	private void update() {
		this.from=Math.max(this.allRows.size()-this.amntRows,0);
		this.maxScroll = this.from;
		render();
	}
	private int getSplit() {
		return this.width - 60;
	}
	private void renderButtons() {
		Event up = new Event();
		up.setEventId(this.connector.LOG_UP);
		Event down = new Event();
		down.setEventId(this.connector.LOG_DOWN);
		
		fillWithGraphics(Property.LOG_BUTTON_X_FROM, Property.LOG_BUTTON_X_UNTIL-1, Property.LOG_BUTTON_UP_Y_FROM, Property.LOG_BUTTON_UP_Y_UNTIL-1, Resources.PORTRAITSx32.get(Resources.UP), true);
		this.connector.addEvent(Property.LOG_X_FROM+Property.LOG_BUTTON_X_FROM, Property.LOG_Y_FROM+Property.LOG_BUTTON_UP_Y_FROM, Property.LOG_BUTTON_WIDTH, Property.LOG_BUTTON_HEIGHT, up);
		
		fillWithGraphics(Property.LOG_BUTTON_X_FROM, Property.LOG_BUTTON_X_UNTIL-1, Property.LOG_BUTTON_DOWN_Y_FROM, Property.LOG_BUTTON_DOWN_Y_UNTIL-1, Resources.PORTRAITSx32.get(Resources.DOWN), true);
		this.connector.addEvent(Property.LOG_X_FROM+Property.LOG_BUTTON_X_FROM,Property.LOG_Y_FROM+Property.LOG_BUTTON_DOWN_Y_FROM, Property.LOG_BUTTON_WIDTH, Property.LOG_BUTTON_HEIGHT, down);
	}
	public void mouseClicked(Event e) {
		if(e.getEventId().equals(this.connector.LOG_UP)) {
			if(this.from>0) {
				this.from--;
				render();
			}
		}else if(e.getEventId().equals(this.connector.LOG_DOWN)) {
			if(this.from<this.maxScroll) {
				this.from++;
				render();
			}
		}
	}
}
