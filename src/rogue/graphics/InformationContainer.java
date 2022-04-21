package rogue.graphics;

import java.awt.Point;

import rogue.framework.eventhandling.Connector;
import rogue.framework.resources.Property;
import util.MyColor;
import util.TextAlignment;
import util.TextEditor;
import util.TextEditor.TextEditorConfig;

public class InformationContainer {
	
	//general
	private int totalWidth;
	private int totalHeight;
	
	protected int offsetLeft;
	protected int offsetTop;
	
	
	//header
	protected int PORTRAIT_X_FROM;
	protected int PORTRAIT_X_UNTIL;
	protected int PORTRAIT_Y_FROM;
	protected int PORTRAIT_Y_UNTIL;
	protected int LIFEBAR_WIDTH;
	protected int LIFEBAR_HEIGHT;
	protected int MANABAR_WIDTH;
	protected int MANABAR_HEIGHT;
	protected int HEADER_ROW1_Y_FROM;
	protected int HEADER_ROW1_Y_UNTIL;
	protected int HEADER_ROW2_Y_FROM ;
	protected int HEADER_ROW2_Y_UNTIL;
	protected int HEADER_ROW3_Y_FROM;
	protected int HEADER_ROW3_Y_UNTIL;
	protected int HEADER_COLUMN1_X_FROM;
	protected int HEADER_COLUMN1_X_UNTIL;
	protected int HEADER_COLUMN2_X_FROM;
	protected int HEADER_COLUMN2_X_UNTIL;
	
	//tabs
	protected int TAB_Y_FROM;
	protected int TAB_Y_UNTIL;
	protected int TAB_WIDTH	;
	protected int TAB_HEIGHT;
	
	protected int TAB_INFO_X_FROM;
	protected int TAB_INFO_X_UNTIL;
	protected int TAB_INFO_Y_FROM;
	protected int TAB_INFO_Y_UNTIL;
	
	//stats
	protected int STATSTABLE_X_FROM	;
	protected int STATSTABLE_X_UNTIL;
	protected int STATSTABLE_Y_FROM;
	
	//skills
	private int ICON_SIZE;
	private int C_SKILLS_WIDTH;
	private int C_SKILLS_HEIGHT;
	private int C_SKILLS_ICONS_X_FROM;
	private int C_SKILLS_ICONS_X_UNTIL;
	private int C_SKILLS_ICONS_Y_FROM;
	private int C_SKILLS_ICONS_Y_UNTIL;
	
	
	protected Connector connector;
	protected int[] pixels;
	
	protected int[] portrait;
	
	protected String name;
	protected int height,width;
	protected int xanchor,yanchor;
	
	protected TextEditor editor;
	
	public InformationContainer() {
		
	}
	
	public InformationContainer(int[] portrait, String name, int[] dimensions,TextEditorConfig txtEditConf, Connector connector) {
		this.portrait = portrait;
		this.name = name;
		this.width = dimensions[0];
		this.height= dimensions[1];
		this.connector = connector;
		this.editor = new TextEditor(txtEditConf);
		this.pixels = new int[width*height];
	}
	public InformationContainer(int[] dimensions, TextEditorConfig conf, Connector connector) {
		this.width=dimensions[0];
		this.height=dimensions[1];
		this.editor = new TextEditor(conf);
		this.connector=connector;
		this.pixels=new int[width*height];
	}
	
	protected int[] getTextLine(String text, int textWidth, int textHeight, int fontSize, MyColor fontColor) {
		int result[] = editor.getTextLine(text, textWidth, textHeight, fontSize, TextAlignment.CENTER, MyColor.VOID, fontColor);
		return result;
	}
	protected void writeLine(String text, int xFrom, int xUntil, int yFrom, int yUntil) {
		writeLine(text,xFrom,xUntil,yFrom,yUntil,0,TextAlignment.CENTER,MyColor.BLACK,MyColor.WHITE);
	}
	protected void writeLine(String text, int xFrom, int xUntil, int yFrom, int yUntil, int fontSize, TextAlignment alignment, MyColor backGround, MyColor font) {
		
		int result[] = editor.getTextLine(text, (xUntil-xFrom+1),(yUntil-yFrom+1),fontSize,alignment,backGround, font);
		if(text.equals("this is a test of my current battlelog"))
			print(result,(xUntil-xFrom+1),(yUntil-yFrom+1));
//		print(result,(xUntil-xFrom+1),(yUntil-yFrom+1));
		int index = 0;
		for(int y = yFrom; y <= yUntil; y++) {
			for(int x = xFrom; x <= xUntil; x++) {
				this.pixels[x+y*width] = result[index];
				index++;
			}
		}
	}
	public static void writeLineExt(String text, int xFrom, int xUntil, int yFrom, int yUntil, int fontSize, TextAlignment alignment, MyColor backGround, MyColor font,int[] pixels, int width) {
		int result[] = new TextEditor(TextEditor.conf8x7).getTextLine(text, (xUntil-xFrom+1),(yUntil-yFrom+1),fontSize,alignment,backGround, font);
		int index = 0;
		for(int y = yFrom; y <= yUntil; y++) {
			for(int x = xFrom; x <= xUntil; x++) {
				pixels[x+y*width] = result[index];
				index++;
			}
		}
	}
	public void writeButton(String text, int xFrom, int yFrom) {
		writeButton(text,xFrom,yFrom, MyColor.BLACK, MyColor.WHITE);
	}
	
	public void writeButton(String label,int xfrom, int yfrom, MyColor backGroundColor, MyColor fontColor) {
		
		int button[] = editor.getButtonLine(label, Property.BUTTON_NORMAL_WIDTH, Property.BUTTON_NORMAL_HEIGHT,backGroundColor,fontColor);
		
		int yUntil = yfrom+Property.BUTTON_NORMAL_HEIGHT;
		int xUntil = xfrom+Property.BUTTON_NORMAL_WIDTH;
		int index = 0;
		for(int y = yfrom; y < yUntil; y++)  {
			for(int x = xfrom; x < xUntil; x++) {
				pixels[x+y*width] = button[index];
				index++;
			}
		}
	}
	
	protected void horizontalLine(int xfrom, int xuntil, int height) {
		for(int i = xfrom; i <= xuntil; i++) {
			pixels[i+height*this.width] = -1;
		}
	}
	protected void fillWithGraphics(int xfrom, int xuntil, int yfrom, int yuntil, int[] graphics, boolean bordered, MyColor backgroundColor) {
		
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
				}else {
					pixels[j + i * this.width] = backgroundColor.VALUE;
				}
				index++;
			}
		}
	}
	protected void fillWithGraphics(int xfrom, int xuntil, int yfrom, int yuntil, int[] graphics, boolean bordered) {
		fillWithGraphics(xfrom,xuntil,yfrom,yuntil,graphics,bordered,MyColor.BLACK);
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
					System.out.print(". ");
				}else {
					System.out.print("# ");
				}
			}
			System.out.println();
		}
	}
	
	protected void green() {
		for(int i = 0; i < this.pixels.length; i++) {
			this.pixels[i] = MyColor.GREEN.VALUE;
		}
	}
	private void clearTabInfo(int width) {
		for(int x = TAB_INFO_X_FROM; x <= TAB_INFO_X_UNTIL; x++) {
			for(int y = TAB_INFO_Y_FROM; y <= TAB_INFO_Y_UNTIL; y++) {
				this.pixels[x+y*totalWidth] = MyColor.GREEN.VALUE;
			}
		}
	}
	protected void clear() {
		this.pixels = new int[this.pixels.length];
	}
	public int[] getPixels() {
		return this.pixels;
	}
	public Point getAnchor() {
		return new Point(xanchor,yanchor);
	}
}
