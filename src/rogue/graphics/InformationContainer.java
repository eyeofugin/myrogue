package rogue.graphics;

import rogue.framework.eventhandling.Connector;
import util.MyColor;
import util.TextAlignment;
import util.TextEditor;

public class InformationContainer {
	
	protected Connector connector;
	protected int[] pixels;
	
	protected int[] portrait;
	
	protected String name;
	protected int height,width;
	
	public InformationContainer() {
		
	}
	
	public InformationContainer(int[] portrait, String name, int width, int height, Connector connector) {
		this.portrait = portrait;
		this.name = name;
		this.width = width;
		this.height= height;
		this.connector = connector;
		this.pixels = new int[width*height];
	}
	
	protected int[] getTextLine(String text, int textWidth, int textHeight, int fontSize, MyColor fontColor) {
		int result[] = TextEditor.getTextLine(text, textWidth, textHeight, fontSize, TextAlignment.CENTER, MyColor.VOID, fontColor);
		return result;
	}
	protected void writeLine(String text, int xFrom, int xUntil, int yFrom, int yUntil) {
		writeLine(text,xFrom,xUntil,yFrom,yUntil,0,TextAlignment.CENTER,MyColor.BLACK,MyColor.WHITE);
	}
	protected void writeLine(String text, int xFrom, int xUntil, int yFrom, int yUntil, int fontSize, TextAlignment alignment, MyColor backGround, MyColor font) {
		int result[] = TextEditor.getTextLine(text, (xUntil-xFrom+1),(yUntil-yFrom+1),fontSize,alignment,backGround, font);
		
		int index = 0;
		for(int y = yFrom; y <= yUntil; y++) {
			for(int x = xFrom; x <= xUntil; x++) {
				pixels[x+y*width] = result[index];
				index++;
			}
		}
	}
	protected void horizontalLine(int xfrom, int xuntil, int height) {
		for(int i = xfrom; i <= xuntil; i++) {
			pixels[i+height*this.width] = -1;
		}
	}
	protected void fillWithGraphics(int xfrom, int xuntil, int yfrom, int yuntil, int[] graphics, boolean bordered) {
		
		if(bordered) {
			for (int i = xfrom - 1; i < xuntil + 2; i++) {
				pixels[i + (yfrom - 1) * this.width] = -1;
				pixels[i + (yuntil + 1) * this.width] = -1;
			}
			for (int i = yfrom - 1; i < yuntil + 2; i++) {
				pixels[(xfrom - 1) + i * this.width] = -1;
				pixels[(xuntil + 1) + i * this.width] = -1;
			}	
		}
		
		int index = 0;
		for(int i = yfrom; i <=yuntil; i++) {
			for(int j = xfrom; j <= xuntil; j++) {
			
				if(graphics[index]!=-12450784) {
					pixels[j + i * this.width] = graphics[index];
				}
				index++;
			}
		}
	}
	
	protected void writeBar(int xFrom, int xUntil, int yFrom, int yUntil, double percentage, MyColor color) {
		
		int filledSize = (int)((xUntil+1-xFrom) * percentage);
		
		for(int x = xFrom; x <= (xFrom+filledSize); x++) {
			for(int y = yFrom; y <= yUntil; y++) {
				pixels[x+y*width] = color.VALUE;
			}
		}
		
		for (int i = xFrom - 1; i < xUntil + 2; i++) {
			pixels[i + (yFrom - 1) * this.width] = -1;
			pixels[i + (yUntil + 1) * this.width] = -1;
		}
		for (int i = yFrom - 1; i < yUntil + 2; i++) {
			pixels[(xFrom - 1) + i * this.width] = -1;
			pixels[(xUntil + 1) + i * this.width] = -1;
		}	
	}
	
	protected void print(int[] p, int width, int height) {
		for(int x = 0; x < height; x++) {
			for(int y = 0; y < width; y++) {
				int i = p[y+x*width];
				if(i == 0) {
					System.out.print("0");
				}else {
					System.out.print("1");
				}
			}
			System.out.println();
		}
	}
	
	public int[] getPixels() {
		return this.pixels;
	}
}
