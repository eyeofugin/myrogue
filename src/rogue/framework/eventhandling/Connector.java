package rogue.framework.eventhandling;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class Connector {

	private int mapXFrom,mapXUntil;
	
	private Event[][] events;
	private List<Event> eventsList = new ArrayList<Event>();
	private int x,y;
	private int xOffset=0,yOffset=0;
	
	public Connector(int x, int y) {
		this.x=x;this.y=y;
		this.events = new Event[this.x][this.y];
	}
	public Connector(int x, int y,int mapXFrom, int mapXUntil) {
		this.x=x;this.y=y;
		this.events = new Event[this.x][this.y];
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
	
	public Event getEvent(MouseEvent e) {
		return this.events[e.getX()+xOffset*32][e.getY()+yOffset*32];
	}
	
	public void cleanAll() {
		this.events = new Event[this.x][this.y];
	}
	public void cleanMap() {
		for(int i = mapXFrom; i <= mapXUntil; i++ ) {
			for(int j = 0; j < y; j++) {
				this.events[i][j] = null;
			}
		}
	}
	public void clearMovement(String name) {
		for(int i = mapXFrom; i <= mapXUntil; i++ ) {
			for(int j = 0; j < y; j++) {
				if(this.events[i][j] != null &&
						this.events[i][j].getEventId().equals("selectPlayerEvent") &&
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

	public void setEvents(Event[][] events) {
		this.events = events;
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
		System.out.println(xOffset + " " + yOffset);
	}
}
