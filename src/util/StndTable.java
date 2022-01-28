package util;

import rogue.framework.resources.Property;
import rogue.graphics.InformationContainer;

public class StndTable extends InformationContainer{
	
	private int[] sizes;
	private int[] superSizes;
	private StndColumn[] columns;
	private StndColumn header;
	private StndColumn superHeader;
	private StndColumn footer;
	
	private int baseLineSize = 8;
	
	public StndTable(StndColumn[] columns, TextEditor editor,int[] sizes) {
		this.sizes = sizes;
		this.superSizes = sizes;
		this.columns = columns;
		this.editor = editor;
		this.baseLineSize=this.editor.charHeight;
		
		int sum = 0;
		for(int i : sizes) {sum+=i;}
		
		this.width = sum + 10;
		this.height = columns.length*(Property.BASELINEDISTANCE+baseLineSize)-Property.BASELINEDISTANCE > 0 ? columns.length*(Property.BASELINEDISTANCE+baseLineSize)-Property.BASELINEDISTANCE : 0;
	}
	public void addHeader(StndColumn header) {
		this.header = header;
		this.height+=(baseLineSize + Property.BASELINEDISTANCE*2) +1;
	}
	
	public void addFooter(StndColumn footer) {
		this.footer = footer;
		this.height+=(baseLineSize + Property.BASELINEDISTANCE*2) +1;
	}
	
	public void addSuperHeader(StndColumn superHeader) {
		this.superHeader = superHeader;
		this.height+=(baseLineSize + Property.BASELINEDISTANCE*2) +1;
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
				writeLine(superHeader.getEntries()[i], xOffset, xOffset+superSizes[i]-1, yOffset, yOffset+baseLineSize-1,  1, TextAlignment.CENTER, MyColor.BLACK, MyColor.WHITE);
				xOffset+=superSizes[i];
			}
			xOffset=5;
			yOffset+=baseLineSize+Property.BASELINEDISTANCE;
			horizontalLine(xOffset-5, this.width-1, yOffset);
			yOffset+=Property.BASELINEDISTANCE;
		}
		
		//header
		if(header!=null) {
			for(int i = 0; i<header.getEntries().length; i++) {
				writeLine(header.getEntries()[i], xOffset, xOffset+sizes[i]-1, yOffset, yOffset+baseLineSize-1, 1, TextAlignment.RIGHT, MyColor.BLACK, header.getColors()[i]);
				xOffset+=sizes[i];
			}
			xOffset=5;
			yOffset+=baseLineSize+Property.BASELINEDISTANCE;
			horizontalLine(xOffset-5, this.width-1, yOffset);
			yOffset+=Property.BASELINEDISTANCE;
		}
		//entries
		for(StndColumn column : columns) {
			for(int i = 0; i<column.getEntries().length; i++) {
				writeLine(column.getEntries()[i], xOffset, xOffset+sizes[i]-1, yOffset, yOffset+baseLineSize-1, 1, TextAlignment.RIGHT, MyColor.BLACK, column.getColors()[i]);
				xOffset+=sizes[i];
			}
			xOffset=5;
			yOffset+=baseLineSize+Property.BASELINEDISTANCE;
		}
		//footer
		if(footer!=null) {
			horizontalLine(xOffset-5, this.width-1, yOffset);
			yOffset+=Property.BASELINEDISTANCE;
			for(int i = 0; i<footer.getEntries().length; i++) {
				writeLine(footer.getEntries()[i], xOffset, xOffset+sizes[i]-1, yOffset, yOffset+baseLineSize-1, 1, TextAlignment.RIGHT, MyColor.BLACK, footer.getColors()[i]);
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
