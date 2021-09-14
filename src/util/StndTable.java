package util;

import rogue.graphics.InformationContainer;

public class StndTable extends InformationContainer{
	
	private int[] sizes;
	private int[] superSizes;
	private StndColumn[] columns;
	private StndColumn header;
	private StndColumn superHeader;
	private StndColumn footer;
	
	private static final int BASELINEDISTANCE = 4;
	private static final int BASELINESIZE = 7;
	
	public StndTable(StndColumn[] columns, int[] sizes) {
		this.sizes = sizes;
		this.superSizes = sizes;
		this.columns = columns;
		
		int sum = 0;
		for(int i : sizes) {sum+=i;}
		
		this.width = sum + 10;
		this.height = columns.length*(BASELINEDISTANCE+BASELINESIZE)-BASELINEDISTANCE;
	}
	public void addHeader(StndColumn header) {
		this.header = header;
		this.height+=(BASELINESIZE + BASELINEDISTANCE*2) +1;
	}
	
	public void addFooter(StndColumn footer) {
		this.footer = footer;
		this.height+=(BASELINESIZE + BASELINEDISTANCE*2) +1;
	}
	
	public void addSuperHeader(StndColumn superHeader) {
		this.superHeader = superHeader;
		this.height+=(BASELINESIZE + BASELINEDISTANCE*2) +1;
	}
	
	public void addSuperSizes(int[] superSizes) {
		this.superSizes = superSizes;
	}
	
	public void finish() {
		
		this.pixels = new int[this.width*this.height];
		
		int yOffset=0;
		int xOffset=5;
		
		//superHeader
		if(superHeader!=null) {
			for(int i = 0; i < superHeader.getEntries().length; i++) {
				writeLine(superHeader.getEntries()[i], xOffset, xOffset+superSizes[i]-1, yOffset, yOffset+BASELINESIZE-1,  1, TextAlignment.CENTER, MyColor.BLACK, MyColor.WHITE);
				xOffset+=superSizes[i];
			}
			xOffset=5;
			yOffset+=BASELINESIZE+BASELINEDISTANCE;
			horizontalLine(xOffset-5, this.width-1, yOffset);
			yOffset+=BASELINEDISTANCE;
		}
		
		//header
		if(header!=null) {
			for(int i = 0; i<header.getEntries().length; i++) {
				writeLine(header.getEntries()[i], xOffset, xOffset+sizes[i]-1, yOffset, yOffset+BASELINESIZE-1, 1, TextAlignment.RIGHT, MyColor.BLACK, header.getColors()[i]);
				xOffset+=sizes[i];
			}
			xOffset=5;
			yOffset+=BASELINESIZE+BASELINEDISTANCE;
			horizontalLine(xOffset-5, this.width-1, yOffset);
			yOffset+=BASELINEDISTANCE;
		}
		//entries
		for(StndColumn column : columns) {
			for(int i = 0; i<column.getEntries().length; i++) {
				writeLine(column.getEntries()[i], xOffset, xOffset+sizes[i]-1, yOffset, yOffset+BASELINESIZE-1, 1, TextAlignment.RIGHT, MyColor.BLACK, column.getColors()[i]);
				xOffset+=sizes[i];
			}
			xOffset=5;
			yOffset+=BASELINESIZE+BASELINEDISTANCE;
		}
		//footer
		if(footer!=null) {
			horizontalLine(xOffset-5, this.width-1, yOffset);
			yOffset+=BASELINEDISTANCE;
			for(int i = 0; i<footer.getEntries().length; i++) {
				writeLine(footer.getEntries()[i], xOffset, xOffset+sizes[i]-1, yOffset, yOffset+BASELINESIZE-1, 1, TextAlignment.RIGHT, MyColor.BLACK, footer.getColors()[i]);
				xOffset+=sizes[i];
			}
		}
	}
	public int getDialogMinWidth() {
		return this.width;
	}
	public int getHeight() {
		return this.height;
	}
}
