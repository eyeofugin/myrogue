package util;

import rogue.framework.eventhandling.Event;
import rogue.framework.resources.Resources;
import rogue.graphics.InformationContainer;

public class IconRow extends InformationContainer{
	
	private static final int ICON_SIZE = 32;
	private int amntIcons;
	private int[] icons;
	private Event[] onClickEvents;
	private Event[] events;
	private int hMargin,vMargin;
	
	public IconRow(Event[] events, int[] icons, int xSpan, int ySpan) {
		
		this.amntIcons = icons.length;
		this.icons = icons;
		this.onClickEvents = events;
		this.width = round(xSpan);
		this.height = round(ySpan);
		calcMargins();
		this.events = new Event[events.length];
		this.pixels = new int[this.width*this.height];
		finish();
	}
	private void finish() {
		
		int xOffset=hMargin;
		int yOffset=vMargin;
		int eventCtr = 0;
		for(int b : icons) {
			int[] iconPixels = Resources.PORTRAITSx32.get(b);
			//print(iconPixels,32,32);
			fillWithGraphics(xOffset, xOffset+ICON_SIZE-1, yOffset, yOffset+ICON_SIZE-1, iconPixels, true);
			fillInEvents(xOffset, yOffset,eventCtr);
			xOffset+=hMargin;
			xOffset+=ICON_SIZE;
			eventCtr++;
		}
	}
	private int round(int i) {
		if(i%2!=0) {
			return i-1;
		}
		return i;
	}
	private void calcMargins() {
		this.hMargin = (this.width-ICON_SIZE*amntIcons) / (amntIcons+1);
		this.vMargin = (this.height-ICON_SIZE) / 2;
	}
	private void fillInEvents(int xOff, int yOff, int eventCtr) {
		Event e = this.onClickEvents[eventCtr];
		e.setX(xOff);
		e.setY(yOff);
		this.events[eventCtr] = e;
	}
	public Event[] getEvents() {
		return this.events;
	}
}
