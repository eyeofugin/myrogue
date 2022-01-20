package util;

import java.util.HashMap;

import rogue.framework.resources.Property;

public class TextEditor {

	//TODO: TextEditor als static umsetzen
	
	public int buttonPadding = 2;
	public int buttonLine = 2;
	 
	public int charWidth,charHeight;
	public boolean hasLowerCase=true;
	
	public static TextEditorConfig conf8x7 = new TextEditorConfig(8,7);
	public static TextEditorConfig conf5x3 = new TextEditorConfig(3,5);
	public static TextEditorConfig conf5x8 = new TextEditorConfig(5,8);
	
	private HashMap<String,int[]> symbols;
	
	public TextEditor(TextEditorConfig conf) {
		this.charWidth = conf.getCharWidth();
		this.charHeight = conf.getCharHeight();
		if(conf.equals(TextEditor.conf5x3)) {
			this.symbols = fillSymbols5x3();
		}else if(conf.equals(TextEditor.conf5x8)){
			this.symbols = fillSymbols5x8();
		}else {
			this.symbols = fillSymbols();
			this.hasLowerCase=false;
		}
	}

	private HashMap<String,int[]> fillSymbols() {
		
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
	
	private HashMap<String,int[]> fillSymbols5x3() {
		HashMap<String,int[]> symbols = new HashMap<String,int[]>();
		
		symbols.put("1", Symbol.one5x3.pixels);
		symbols.put("2", Symbol.two5x3.pixels);
		symbols.put("3", Symbol.three5x3.pixels);
		symbols.put("4", Symbol.four5x3.pixels);
		symbols.put("5", Symbol.five5x3.pixels);
		symbols.put("6", Symbol.six5x3.pixels);
		symbols.put("7", Symbol.seven5x3.pixels);
		symbols.put("8", Symbol.eight5x3.pixels);
		symbols.put("9", Symbol.nine5x3.pixels);
		symbols.put("0", Symbol.zero5x3.pixels);
		symbols.put("a", Symbol.a5x3.pixels);
		symbols.put("b", Symbol.b5x3.pixels);
		symbols.put("c", Symbol.c5x3.pixels);
		symbols.put("d", Symbol.d5x3.pixels);
		symbols.put("e", Symbol.e5x3.pixels);
		symbols.put("f", Symbol.f5x3.pixels);
		symbols.put("g", Symbol.g5x3.pixels);
		symbols.put("h", Symbol.h5x3.pixels);
		symbols.put("i", Symbol.i5x3.pixels);
		symbols.put("j", Symbol.j5x3.pixels);
		symbols.put("k", Symbol.k5x3.pixels);
		symbols.put("l", Symbol.l5x3.pixels);
		symbols.put("m", Symbol.m5x3.pixels);
		symbols.put("n", Symbol.n5x3.pixels);
		symbols.put("o", Symbol.o5x3.pixels);
		symbols.put("p", Symbol.p5x3.pixels);
		symbols.put("q", Symbol.q5x3.pixels);
		symbols.put("r", Symbol.r5x3.pixels);
		symbols.put("s", Symbol.s5x3.pixels);
		symbols.put("t", Symbol.t5x3.pixels);
		symbols.put("u", Symbol.u5x3.pixels);
		symbols.put("v", Symbol.v5x3.pixels);
		symbols.put("w", Symbol.w5x3.pixels);
		symbols.put("x", Symbol.x5x3.pixels);
		symbols.put("y", Symbol.y5x3.pixels);
		symbols.put("z", Symbol.z5x3.pixels);
		symbols.put("A", Symbol.A5x3.pixels);
		symbols.put("B", Symbol.B5x3.pixels);
		symbols.put("C", Symbol.C5x3.pixels);
		symbols.put("D", Symbol.D5x3.pixels);
		symbols.put("E", Symbol.E5x3.pixels);
		symbols.put("F", Symbol.F5x3.pixels);
		symbols.put("G", Symbol.G5x3.pixels);
		symbols.put("H", Symbol.H5x3.pixels);
		symbols.put("I", Symbol.I5x3.pixels);
		symbols.put("J", Symbol.J5x3.pixels);
		symbols.put("K", Symbol.K5x3.pixels);
		symbols.put("L", Symbol.L5x3.pixels);
		symbols.put("M", Symbol.M5x3.pixels);
		symbols.put("N", Symbol.N5x3.pixels);
		symbols.put("O", Symbol.O5x3.pixels);
		symbols.put("P", Symbol.P5x3.pixels);
		symbols.put("Q", Symbol.Q5x3.pixels);
		symbols.put("R", Symbol.R5x3.pixels);
		symbols.put("S", Symbol.S5x3.pixels);
		symbols.put("T", Symbol.T5x3.pixels);
		symbols.put("U", Symbol.U5x3.pixels);
		symbols.put("V", Symbol.V5x3.pixels);
		symbols.put("W", Symbol.W5x3.pixels);
		symbols.put("X", Symbol.X5x3.pixels);
		symbols.put("Y", Symbol.Y5x3.pixels);
		symbols.put("Z", Symbol.Z5x3.pixels);
		symbols.put(".", Symbol.point5x3.pixels);
		symbols.put("/", Symbol.slash5x3.pixels);
		
		return symbols;
	}
	
	private HashMap<String,int[]> fillSymbols5x8() {
		HashMap<String,int[]> symbols = new HashMap<String,int[]>();
		
		symbols.put("1", Symbol.one5x8.pixels);
		symbols.put("2", Symbol.two5x8.pixels);
		symbols.put("3", Symbol.three5x8.pixels);
		symbols.put("4", Symbol.four5x8.pixels);
		symbols.put("5", Symbol.five5x8.pixels);
		symbols.put("6", Symbol.six5x8.pixels);
		symbols.put("7", Symbol.seven5x8.pixels);
		symbols.put("8", Symbol.eight5x8.pixels);
		symbols.put("9", Symbol.nine5x8.pixels);
		symbols.put("0", Symbol.zero5x8.pixels);
		symbols.put("a", Symbol.a5x8.pixels);
		symbols.put("b", Symbol.b5x8.pixels);
		symbols.put("c", Symbol.c5x8.pixels);
		symbols.put("d", Symbol.d5x8.pixels);
		symbols.put("e", Symbol.e5x8.pixels);
		symbols.put("f", Symbol.f5x8.pixels);
		symbols.put("g", Symbol.g5x8.pixels);
		symbols.put("h", Symbol.h5x8.pixels);
		symbols.put("i", Symbol.i5x8.pixels);
		symbols.put("j", Symbol.j5x8.pixels);
		symbols.put("k", Symbol.k5x8.pixels);
		symbols.put("l", Symbol.l5x8.pixels);
		symbols.put("m", Symbol.m5x8.pixels);
		symbols.put("n", Symbol.n5x8.pixels);
		symbols.put("o", Symbol.o5x8.pixels);
		symbols.put("p", Symbol.p5x8.pixels);
		symbols.put("q", Symbol.q5x8.pixels);
		symbols.put("r", Symbol.r5x8.pixels);
		symbols.put("s", Symbol.s5x8.pixels);
		symbols.put("t", Symbol.t5x8.pixels);
		symbols.put("u", Symbol.u5x8.pixels);
		symbols.put("v", Symbol.v5x8.pixels);
		symbols.put("w", Symbol.w5x8.pixels);
		symbols.put("x", Symbol.x5x8.pixels);
		symbols.put("y", Symbol.y5x8.pixels);
		symbols.put("z", Symbol.z5x8.pixels);
		symbols.put("A", Symbol.A5x8.pixels);
		symbols.put("B", Symbol.B5x8.pixels);
		symbols.put("C", Symbol.C5x8.pixels);
		symbols.put("D", Symbol.D5x8.pixels);
		symbols.put("E", Symbol.E5x8.pixels);
		symbols.put("F", Symbol.F5x8.pixels);
		symbols.put("G", Symbol.G5x8.pixels);
		symbols.put("H", Symbol.H5x8.pixels);
		symbols.put("I", Symbol.I5x8.pixels);
		symbols.put("J", Symbol.J5x8.pixels);
		symbols.put("K", Symbol.K5x8.pixels);
		symbols.put("L", Symbol.L5x8.pixels);
		symbols.put("M", Symbol.M5x8.pixels);
		symbols.put("N", Symbol.N5x8.pixels);
		symbols.put("O", Symbol.O5x8.pixels);
		symbols.put("P", Symbol.P5x8.pixels);
		symbols.put("Q", Symbol.Q5x8.pixels);
		symbols.put("R", Symbol.R5x8.pixels);
		symbols.put("S", Symbol.S5x8.pixels);
		symbols.put("T", Symbol.T5x8.pixels);
		symbols.put("U", Symbol.U5x8.pixels);
		symbols.put("V", Symbol.V5x8.pixels);
		symbols.put("W", Symbol.W5x8.pixels);
		symbols.put("X", Symbol.X5x8.pixels);
		symbols.put("Y", Symbol.Y5x8.pixels);
		symbols.put("Z", Symbol.Z5x8.pixels);
		symbols.put(".", Symbol.point5x8.pixels);
		symbols.put("/", Symbol.slash5x8.pixels);
		
		return symbols;
	}
	
	public int[] getTextLine(String text, int targetWidth, int targetHeight) {
		
		return getTextLine(text,targetWidth,targetHeight,
				0,TextAlignment.CENTER,
				MyColor.BLACK, MyColor.WHITE);
	}
	public int[] getTextLine(String text, int targetWidth, int targetHeight,int fontsize,TextAlignment alignment) {
		
		return getTextLine(text,targetWidth,targetHeight,
				fontsize,alignment,
				MyColor.BLACK, MyColor.WHITE);
	}
	public int[] getTextLine(String text, int targetWidth, int targetHeight,MyColor color) {
		
		return getTextLine(text,targetWidth,targetHeight,
				0,TextAlignment.CENTER,
				MyColor.BLACK, MyColor.WHITE);
	}
	public int[] getTextLine(String text, int targetWidth, int targetHeight,
			int fontSize, TextAlignment alignment,
			MyColor backGroundColor, MyColor fontColor) {

		int backgroundColor = backGroundColor.VALUE;
		int fontcolor = fontColor.VALUE;
		
		int[] result = new int[targetWidth * targetHeight];
		int resultWidth = text.length() * this.charWidth + text.length() - 1;
		int[] word = new int[this.charHeight * resultWidth];

		int multiplier;
		int xmultiplier = targetWidth / resultWidth;
		int ymultiplier = targetHeight / this.charHeight;
		if (xmultiplier < ymultiplier) {
			multiplier = xmultiplier;
		} else {
			multiplier = ymultiplier;
		}
		
		if(multiplier > fontSize && fontSize > 0) {
			multiplier = fontSize;
		}

		char[] textArray = new char[text.length()];

		int heightOverhead = targetHeight - (int)(this.charHeight * multiplier);
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
				String s ="";
				if(this.hasLowerCase) {
					s = String.valueOf(symbol);
				}else {
					s = String.valueOf(symbol).toLowerCase();
				}
				int[] symbolarray = symbols.get(s);

				int index = 0;

				for (int y = 0; y < this.charHeight; y++) {
					for (int x = lastWrittenWidth; x < lastWrittenWidth + this.charWidth; x++) {
						word[x + y * resultWidth] = symbolarray[index];
						index++;
					}
				}

			} else if (Character.isDigit(symbol)) {

				int[] symbolarray = symbols.get(symbol);
				int index = 0;

				for (int y = 0; y < this.charHeight; y++) {
					for (int x = lastWrittenWidth; x < lastWrittenWidth + this.charWidth; x++) {
						word[x + y * resultWidth] = symbolarray[index];
						index++;
					}
				}

			} else if (symbol == ' ') {
				for (int y = 0; y < this.charHeight; y++) {
					for (int x = lastWrittenWidth; x < lastWrittenWidth + this.charWidth; x++) {
						word[x + y * resultWidth] = backgroundColor;
					}
				}
			}else if (symbol == '.') {
				for (int y = 0; y < this.charHeight; y++) {
					for (int x = lastWrittenWidth; x < lastWrittenWidth + this.charWidth; x++) {
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

				for (int y = 0; y < this.charHeight; y++) {
					for (int x = lastWrittenWidth; x < lastWrittenWidth + this.charWidth; x++) {
						word[x + y * resultWidth] = symbolarray[index];
						index++;
					}
				}
				
			}
			
			lastWrittenWidth += this.charWidth;
			if (symbolNr != text.length()) {
					for (int i = 0; i < this.charHeight; i++) {
						word[lastWrittenWidth + i * resultWidth] = backgroundColor;
					}
					lastWrittenWidth+=Property.BASECHARDISTANCE;
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
	
	public int[] getButtonLine(String text, int targetWidth, int targetHeight,
			MyColor backGroundColor, MyColor fontColor) {
		
		int backgroundColor = backGroundColor.VALUE;
		int fontcolor = fontColor.VALUE;
		int[] button = new int[targetWidth*targetHeight];
		
		
		int labelWidth = targetWidth-(this.buttonPadding+this.buttonLine);
		int labelHeight = targetHeight-(this.buttonPadding+this.buttonLine);
		
		int[] label = getTextLine(text, labelWidth, labelHeight, 1, TextAlignment.CENTER);
		
		for(int i = 0; i < button.length; i++) {
			button[i] = backgroundColor;
		}
		for(int x = 0; x < targetWidth; x++) {
			for(int y = 0; y < this.buttonLine; y++ ) {
				button[x+y*targetWidth] = -1;
			}
		}
		for(int x = 0; x < targetWidth; x++) {
			for(int y = targetHeight-this.buttonLine; y < targetHeight; y++ ) {
				button[x+y*targetWidth] = -1;
			}
		}
		for(int x = 0; x < this.buttonLine; x++) {
			for(int y = 0; y < targetHeight; y++ ) {
				button[x+y*targetWidth] = -1;
			}
		}
		for(int x = targetWidth-this.buttonLine; x < targetWidth; x++) {
			for(int y = 0; y < targetHeight; y++ ) {
				button[x+y*targetWidth] = -1;
			}
		}
		int labelpadding = this.buttonLine+this.buttonPadding-2;
		int index = 0;
		for(int y = labelpadding; y < labelHeight+labelpadding; y++) {
			for(int x = labelpadding; x < labelWidth+labelpadding; x++) {
				button[x+y*targetWidth] = label[index];
				index++;
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
	
	public static class TextEditorConfig {
		
		private int charWidth;
		private int charHeight;
		
		public TextEditorConfig(int charWidth, int charHeight) {
			this.charWidth=charWidth;
			this.charHeight=charHeight;
		}
		public int getCharWidth() {
			return this.charWidth;
		}
		public int getCharHeight() {
			return this.charHeight;
		}
	}
}
