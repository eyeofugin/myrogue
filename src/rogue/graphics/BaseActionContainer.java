package rogue.graphics;

import java.util.HashMap;
import java.util.Map;

import rogue.framework.eventhandling.Connector;
import rogue.framework.eventhandling.Event;
import rogue.framework.resources.Property;
import rogue.framework.resources.Resources;
import util.IconRow;

public class BaseActionContainer extends InformationContainer{
	
	public static final String MOVEMENT = "movement";
	public static final String ATTACK = "attack";
	public static final String CONFIRM = "confirm";
	public static final String END_TURN = "endTurn";
	public static final String CANCEL = "cancel";

	private Map<String,Event> events = new HashMap<>();
	private Map<String,Integer> icons = new HashMap<>();;
	
	
	public BaseActionContainer(Connector connector) {
		super(null,null,new int[]{Property.BUTTON_PANEL_WIDTH,Property.BUTTON_PANEL_HEIGHT},Resources.textEditorConfig,connector);
		init();
		paint();
	}
	private void init() {
		
		Event att = new Event();
		att.setEventId(this.connector.SHOW_ATTACK);
		events.put(BaseActionContainer.ATTACK, att);
		icons.put(BaseActionContainer.ATTACK, Resources.ATTACK_ACTION);
		Event move = new Event();
		move.setEventId(this.connector.SHOW_MOVEMENT);
		events.put(BaseActionContainer.MOVEMENT,move);
		icons.put(BaseActionContainer.MOVEMENT,Resources.MOVEMENT_ACTION);
		Event endTurn = new Event();
		endTurn.setEventId(this.connector.END_TURN);
		events.put(BaseActionContainer.END_TURN,endTurn);
		icons.put(BaseActionContainer.END_TURN,Resources.END_TURN_ACTION);
		
	}
	private void paint() {
		Event[] events = new Event[this.events.size()];
		int[] icons = new int[events.length];
		int i = 0;
		if(this.events.containsKey(BaseActionContainer.ATTACK)) {
			events[i] = this.events.get(BaseActionContainer.ATTACK);
			icons[i] = this.icons.get(BaseActionContainer.ATTACK);
			i++;
		}
		if(this.events.containsKey(BaseActionContainer.MOVEMENT)) {
			events[i] = this.events.get(BaseActionContainer.MOVEMENT);
			icons[i] = this.icons.get(BaseActionContainer.MOVEMENT);
			i++;
		}
		if(this.events.containsKey(BaseActionContainer.CONFIRM)) {
			events[i] = this.events.get(BaseActionContainer.CONFIRM);
			icons[i] = this.icons.get(BaseActionContainer.CONFIRM);
			i++;
		}
		if(this.events.containsKey(BaseActionContainer.CANCEL)) {
			events[i] = this.events.get(BaseActionContainer.CANCEL);
			icons[i] = this.icons.get(BaseActionContainer.CANCEL);
			i++;
		}
		if(this.events.containsKey(BaseActionContainer.END_TURN)) {
			events[i] = this.events.get(BaseActionContainer.END_TURN);
			icons[i] = this.icons.get(BaseActionContainer.END_TURN);
			i++;
		}
		IconRow row = new IconRow(events, icons, this.width, this.height);
		for(int j = 0; j < row.getPixels().length;j++) {
			this.pixels[j]=row.getPixels()[j];
		}
		this.connector.cleanButtonPanel();
		for(Event e : row.getEvents()) {
			this.connector.addEvent(Property.BUTTON_PANEL_X_FROM+e.getX()+this.offsetLeft,Property.BUTTON_PANEL_Y_FROM+e.getY()+this.offsetTop, Property.TILE_SIZE, Property.TILE_SIZE, e);
		}
	}
	public void addEvent(String key, Event e, int icon) {
		this.events.put(key, e);
		this.icons.put(key,icon);
		paint();
	}
	public void removeEvent(String key) {
		if(this.events.containsKey(key)) {
			this.events.remove(key);
			paint();
		}
	}
}
