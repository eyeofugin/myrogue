package util;

import rogue.framework.eventhandling.Connector;
import rogue.framework.eventhandling.Event;
import rogue.framework.resources.Resources;
import rogue.graphics.InformationContainer;

public class IconRow extends InformationContainer{
	
	private static final int ICON_SIZE = 32;
	private int amntIcons;
	private byte[] icons;
	private Event[] onClickEvents;
	private Connector connector;
	private int hMargin,vMargin;
	
	public IconRow(Event[] events, byte[] icons, int xSpan, int ySpan, Connector connector) {
		
		this.amntIcons = icons.length;
		this.icons = icons;
		this.onClickEvents = events;
		this.connector = connector;
		this.width = round(xSpan);
		this.height = round(ySpan);
		calcMargins();
		this.pixels = new int[this.width*this.height];
		finish();
	}
	private void finish() {
		
		int xOffset=hMargin;
		int yOffset=vMargin;
		
		for(byte b : icons) {
			int[] iconPixels = Resources.PORTRAITSx32.get(b);
			//print(iconPixels,32,32);
			fillWithGraphics(xOffset, xOffset+ICON_SIZE-1, yOffset, yOffset+ICON_SIZE-1, iconPixels, true);
			xOffset+=hMargin;
			xOffset+=ICON_SIZE;
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
}
