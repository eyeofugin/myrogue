package rogue.framework.eventhandling;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import rogue.framework.resources.Property;

public class Connector {
	
	public static final String MOVE_PLAYER = "MOV";
	public static final String SHOW_MOVEMENT = "SMV";
	public static final String SHOW_ATTACK = "SAT";
	public static final String ATTACK = "ATK";
	public static final String END_TURN ="END";
	public static final String SELECT_PLAYER = "SCT";
	public static final String INFO_OBJECT = "IEN";
	public static final String TAB_CHANGE ="TAB";
	public static final String SKILL_CHOSEN ="SCH";
	public static final String TARGET_CHOSEN ="TCH";
	public static final String CONFIRM_SKILL = "COS";
	public static final String CANCEL_SKILL = "CAS";
	public static final String LOG_UP = "LUP";
	public static final String LOG_DOWN = "LDO";

	private int mapXFrom,mapXUntil;
	
	private Event[][] events;
	private Event[][] context;
	private List<Event> eventsList = new ArrayList<Event>();
	private int x,y;
	private int xOffset=0,yOffset=0;
	
	public Connector(int x, int y) {
		this.x=x;this.y=y;
		this.events = new Event[this.x][this.y];
		this.context = new Event[this.x][this.y];
	}
	public Connector(int x, int y,int mapXFrom, int mapXUntil) {
		this.x=x;this.y=y;
		this.events = new Event[this.x][this.y];
		this.context = new Event[this.x][this.y];
		this.mapXFrom = mapXFrom;
		this.mapXUntil = mapXUntil;
	}
	
	public void addEvent(int xFrom, int yFrom, int xSize, int ySize, Event e) {
		for(int x = xFrom; x < (xFrom+xSize); x++) {
			for(int y = yFrom; y < (yFrom+ySize); y++) {
				events[x][y] = e;
			}
		}
		eventsList.add(e);
	}
	public void removeEvent(String s) {
		for(int x = 0; x < this.events.length; x++) {
			for(int y = 0; y < this.events[0].length; y++) {
				if(this.events[x][y]!=null&&this.events[x][y].getEventId().equals(s)) {
					this.eventsList.remove(this.events[x][y]);
					this.events[x][y] =null;
					return;
				}
			}
		}
	}
	public void addContext(int xFrom, int yFrom, int xSize, int ySize, Event e) {
		for(int x = xFrom; x < (xFrom+xSize); x++) {
			for(int y = yFrom; y < (yFrom+ySize); y++) {
				context[x][y] = e;
			}
		}
	}
	public void removeContextOf(String object) {
		for(int x = 0; x < context.length; x++) {
			for(int y = 0; y < context[0].length; y++) {
				if(this.context[x][y] != null &&
						this.context[x][y].getObject().getName().equals(object)) {
					this.context[x][y] = null;	
				}
			}
		}
	}
	
	public Event getEvent(MouseEvent e) {
		if(e.getX()>Property.START_OF_ROOM_X && e.getX()<Property.END_OF_ROOM_X&&
				e.getY()>Property.START_OF_ROOM_Y && e.getY()<Property.END_OF_ROOM_Y) {
			return this.events[e.getX()+xOffset*Property.TILE_SIZE][e.getY()+yOffset*Property.TILE_SIZE];
		}
		return this.events[e.getX()][e.getY()];
	}
	public Event getContext(MouseEvent e) {
		return this.context[e.getX()+xOffset*Property.TILE_SIZE][e.getY()+yOffset*Property.TILE_SIZE];
	}
	
	public void cleanAll() {
		this.events = new Event[this.x][this.y];
		this.context = new Event[this.x][this.y];
	}
	public void cleanMap() {
		for(int i = mapXFrom; i <= mapXUntil; i++ ) {
			for(int j = 0; j < y; j++) {
				this.events[i][j] = null;
				this.context[i][j] = null;
			}
		}
	}
	public void cleanButtonPanel() {
		for(int i = Property.BUTTON_PANEL_X_FROM; i < Property.BUTTON_PANEL_X_UNTIL; i++ ) {
			for(int j = Property.BUTTON_PANEL_Y_FROM; j < Property.BUTTON_PANEL_Y_UNTIL; j++) {
				this.events[i][j] = null;
				this.context[i][j] = null;
			}
		}
	}
	public void cleanMapEvents() {
		for(int i = mapXFrom; i <= mapXUntil; i++ ) {
			for(int j = 0; j < y; j++) {
				this.events[i][j] = null;
			}
		}
	}
	
	public void clearMovement(String name) {
		for(int i = mapXFrom; i <= mapXUntil; i++ ) {
			for(int j = 0; j < y; j++) {
				if(this.events[i][j] != null && this.events[i][j].getObject()!=null&&
						this.events[i][j].getObject().getName().equals(name)) {
					this.events[i][j] = null;	
				}
			}
		}
	}
	
	//getters Setters

	public Event[][] getEvents() {
		return events;
	}
	public List<Event> getEventsList(){
		return eventsList;
	}
	public Event[][] getContext() {
		return context;
	}
	public void setEvents(Event[][] events) {
		this.events = events;
	}
	public void setContext(Event[][] events) {
		this.context = events;
	}
	public void incrementYOffset() {
		this.yOffset++;
	}
	public void incrementXOffset() {
		this.xOffset++;
	}
	public void decrementYOffset() {
		this.yOffset--;
	}
	public void decrementXOffset() {
		this.xOffset--;
	}
	private void writeOffset() {
		//System.out.println(xOffset + " " + yOffset);
	}
}
