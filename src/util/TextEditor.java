package util;

import java.util.HashMap;

import util.MyColor;
import util.TextAlignment;

public class TextEditor {

	//TODO: TextEditor als static umsetzen
	
	private static HashMap<String, int[]> symbols = fillSymbols();

	private static HashMap<String,int[]> fillSymbols() {
		
		HashMap<String,int[]> symbols = new HashMap<String,int[]>();
		
		symbols.put("1", Symbol.one.pixels);
		symbols.put("2", Symbol.two.pixels);
		symbols.put("3", Symbol.three.pixels);
		symbols.put("4", Symbol.four.pixels);
		symbols.put("5", Symbol.five.pixels);
		symbols.put("6", Symbol.six.pixels);
		symbols.put("7", Symbol.seven.pixels);
		symbols.put("8", Symbol.eight.pixels);
		symbols.put("9", Symbol.nine.pixels);
		symbols.put("0", Symbol.zero.pixels);
		symbols.put("a", Symbol.a.pixels);
		symbols.put("b", Symbol.b.pixels);
		symbols.put("c", Symbol.c.pixels);
		symbols.put("d", Symbol.d.pixels);
		symbols.put("e", Symbol.e.pixels);
		symbols.put("f", Symbol.f.pixels);
		symbols.put("g", Symbol.g.pixels);
		symbols.put("h", Symbol.h.pixels);
		symbols.put("i", Symbol.i.pixels);
		symbols.put("j", Symbol.j.pixels);
		symbols.put("k", Symbol.k.pixels);
		symbols.put("l", Symbol.l.pixels);
		symbols.put("m", Symbol.m.pixels);
		symbols.put("n", Symbol.n.pixels);
		symbols.put("o", Symbol.o.pixels);
		symbols.put("p", Symbol.p.pixels);
		symbols.put("q", Symbol.q.pixels);
		symbols.put("r", Symbol.r.pixels);
		symbols.put("s", Symbol.s.pixels);
		symbols.put("t", Symbol.t.pixels);
		symbols.put("u", Symbol.u.pixels);
		symbols.put("v", Symbol.v.pixels);
		symbols.put("w", Symbol.w.pixels);
		symbols.put("x", Symbol.x.pixels);
		symbols.put("y", Symbol.y.pixels);
		symbols.put("z", Symbol.z.pixels);
		symbols.put(".", Symbol.point.pixels);
		symbols.put("/", Symbol.slash.pixels);
		
		return symbols;
	}
	
	public static int[] getTextLine(String text, int targetWidth, int targetHeight) {
		
		return getTextLine(text,targetWidth,targetHeight,
				0,TextAlignment.CENTER,
				MyColor.BLACK, MyColor.WHITE);
	}
	public static int[] getTextLine(String text, int targetWidth, int targetHeight,int fontsize,TextAlignment alignment) {
		
		return getTextLine(text,targetWidth,targetHeight,
				fontsize,alignment,
				MyColor.BLACK, MyColor.WHITE);
	}
	public static int[] getTextLine(String text, int targetWidth, int targetHeight,MyColor color) {
		
		return getTextLine(text,targetWidth,targetHeight,
				0,TextAlignment.CENTER,
				MyColor.BLACK, MyColor.WHITE);
	}
	public static int[] getTextLine(String text, int targetWidth, int targetHeight,
			int fontSize, TextAlignment alignment,
			MyColor backGroundColor, MyColor fontColor) {

		int backgroundColor = backGroundColor.VALUE;
		int fontcolor = fontColor.VALUE;
		
		int[] result = new int[targetWidth * targetHeight];
		int resultWidth = text.length() * 8 + text.length() - 1;
		int[] word = new int[7 * resultWidth];

		int multiplier;
		int xmultiplier = targetWidth / resultWidth;
		int ymultiplier = targetHeight / 7;
		if (xmultiplier < ymultiplier) {
			multiplier = xmultiplier;
		} else {
			multiplier = ymultiplier;
		}
		
		if(multiplier > fontSize && fontSize > 0) {
			multiplier = fontSize;
		}

		char[] textArray = new char[text.length()];

		int heightOverhead = targetHeight - (int)(7 * multiplier);
		int topOverhead = (heightOverhead / 2) + (heightOverhead % 2);
		int bottomOverhead = heightOverhead / 2;

		int widthOverhead = targetWidth - (int)(resultWidth * multiplier);
		int leftOverhead = (widthOverhead / 2);
		int rightOverhead = (widthOverhead / 2) + (widthOverhead % 2);
		if(text.contains("Level")) {
		}

		if(alignment==TextAlignment.LEFT) {
			leftOverhead = 1;
			rightOverhead = widthOverhead -1;
		}
		if(alignment==TextAlignment.RIGHT) {
			rightOverhead = 1;
			leftOverhead  = widthOverhead - 1;
		}

		for (int row = 0; row < topOverhead; row++) {
			for (int column = 0; column < targetWidth; column++) {
				result[column + row * targetWidth] = backgroundColor;
			}
		}
		for (int row = (targetHeight - bottomOverhead) - 1; row < targetHeight; row++) {
			for (int column = 0; column < targetWidth; column++) {
				result[column + row * targetWidth] = backgroundColor;
			}
		}
		for (int row = 0; row < targetHeight; row++) {
			for (int column = 0; column < leftOverhead; column++) {
				result[column + row * targetWidth] = backgroundColor;
			}
		}
		for (int row = 0; row < targetHeight; row++) {
			for (int column = (targetWidth - rightOverhead) - 1; column < targetWidth; column++) {
				result[column + row * targetWidth] = backgroundColor;
			}
		}

		int lastWrittenWidth = 0;
		text.getChars(0, text.length(), textArray, 0);

		int symbolNr = 1;
		for (char symbol : textArray) {
			if (!(Character.getNumericValue(symbol) == -1)) {
				
				String s = String.valueOf(symbol).toLowerCase();
				int[] symbolarray = symbols.get(s);

				int index = 0;

				for (int y = 0; y < 7; y++) {
					for (int x = lastWrittenWidth; x < lastWrittenWidth + 8; x++) {
						word[x + y * resultWidth] = symbolarray[index];
						index++;
					}
				}

			} else if (Character.isDigit(symbol)) {

				int[] symbolarray = symbols.get(symbol);
				int index = 0;

				for (int y = 0; y < 7; y++) {
					for (int x = lastWrittenWidth; x < lastWrittenWidth + 8; x++) {
						word[x + y * resultWidth] = symbolarray[index];
						index++;
					}
				}

			} else if (symbol == ' ') {
				for (int y = 0; y < 7; y++) {
					for (int x = lastWrittenWidth; x < lastWrittenWidth + 8; x++) {
						word[x + y * resultWidth] = backgroundColor;
					}
				}
			}else if (symbol == '.') {
				for (int y = 0; y < 7; y++) {
					for (int x = lastWrittenWidth; x < lastWrittenWidth + 8; x++) {
						word[x + y * resultWidth] = backgroundColor;
					}
				}
				word[(lastWrittenWidth+2)+6*resultWidth] = fontcolor;
				word[(lastWrittenWidth+3)+6*resultWidth] = fontcolor;
				word[(lastWrittenWidth+2)+5*resultWidth] = fontcolor;
				word[(lastWrittenWidth+3)+5*resultWidth] = fontcolor;
			}else {
				String symbolS = String.valueOf(symbol);
				int[] symbolarray = symbols.get(symbolS);
				int index = 0;

				for (int y = 0; y < 7; y++) {
					for (int x = lastWrittenWidth; x < lastWrittenWidth + 8; x++) {
						word[x + y * resultWidth] = symbolarray[index];
						index++;
					}
				}
				
			}
			
			lastWrittenWidth += 8;
			if (symbolNr != text.length()) {
					for (int i = 0; i < 7; i++) {
						word[lastWrittenWidth + i * resultWidth] = backgroundColor;
					}
					lastWrittenWidth++;
				}
				symbolNr++;

		}
		
		for (int y = topOverhead; y < (targetHeight - bottomOverhead); y++) {
			for (int x = leftOverhead; x < (targetWidth - rightOverhead); x++) {
				int newx = (int) ((x - leftOverhead) / multiplier);
				int newy = (int) ((y - topOverhead) / multiplier);
				result[x + y * targetWidth] = word[newx + newy * resultWidth];
			}
		}
		int resultIndex = 0;
		
		for(int i : result) {
			if(i == 0 || i == -16777216 || i == -12450784) {
				result[resultIndex] = backgroundColor;
			}
			if(i == -1) {
				result[resultIndex] = fontcolor;
			}
			resultIndex++;
		}
		return result;
	}
	
	public static int[] getButtonLine(String text, int targetWidth, int targetHeight, 
			int fontSize, TextAlignment alignment,
			MyColor backGroundColor, MyColor fontColor, boolean isthick) {
		
		int backgroundColor = 0;
		int fontcolor = fontColor.VALUE;
		int[] button = new int[targetWidth*targetHeight];
		
		if(isthick) {
			int labelWidth = targetWidth-10;
			int labelHeight = targetHeight-10;
			
			int[] label = getTextLine(text, labelWidth, labelHeight, fontSize, TextAlignment.CENTER);
			
			for(int i = 0; i < button.length; i++) {
				button[i] = backgroundColor;
			}
			for(int x = 0; x < targetWidth; x++) {
				for(int y = 0; y < 4; y++ ) {
					button[x+y*targetWidth] = -1;
				}
			}
			for(int x = 0; x < targetWidth; x++) {
				for(int y = targetHeight-4; y < targetHeight; y++ ) {
					button[x+y*targetWidth] = -1;
				}
			}
			for(int x = 0; x < 4; x++) {
				for(int y = 0; y < targetHeight; y++ ) {
					button[x+y*targetWidth] = -1;
				}
			}
			for(int x = targetWidth-4; x < targetWidth; x++) {
				for(int y = 0; y < targetHeight; y++ ) {
					button[x+y*targetWidth] = -1;
				}
			}
			int index = 0;
			for(int y = 5; y < labelHeight+5; y++) {
				for(int x = 5; x < labelWidth+5; x++) {
					button[x+y*targetWidth] = label[index];
					index++;
				}
			}		
		}else {
			
			int labelWidth = targetWidth-4;
			int labelHeight = targetHeight-4;
			
			int[] label = getTextLine(text, labelWidth, labelHeight, fontSize, TextAlignment.CENTER);
			
			for(int i = 0; i < button.length; i++) {
				button[i] = backgroundColor;
			}
			for(int x = 0; x < targetWidth; x++) {
				for(int y = 0; y < 2; y++ ) {
					button[x+y*targetWidth] = -1;
				}
			}
			for(int x = 0; x < targetWidth; x++) {
				for(int y = targetHeight-2; y < targetHeight; y++ ) {
					button[x+y*targetWidth] = -1;
				}
			}
			for(int x = 0; x < 2; x++) {
				for(int y = 0; y < targetHeight; y++ ) {
					button[x+y*targetWidth] = -1;
				}
			}
			for(int x = targetWidth-2; x < targetWidth; x++) {
				for(int y = 0; y < targetHeight; y++ ) {
					button[x+y*targetWidth] = -1;
				}
			}
			int index = 0;
			for(int y = 2; y < labelHeight+2; y++) {
				for(int x = 2; x < labelWidth+2; x++) {
					button[x+y*targetWidth] = label[index];
					index++;
				}
			}
		}
		

		
		int buttonIndex = 0;
		
		for(int i : button) {
			if(i == 0) {
				button[buttonIndex] = backgroundColor;
			}
			if(i == -1) {
				button[buttonIndex] = fontcolor;
			}
			buttonIndex++;
		}
		
		return button;		
	}
	
}
