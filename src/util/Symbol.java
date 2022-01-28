package util;

public class Symbol {

	public final int WIDTH,HEIGHT;
	public int[] pixels;
	
	public static Symbol one = 		new Symbol(48,	0,7, 	0,6, 	SpriteSheet.fonts);
	public static Symbol two = 		new Symbol(48, 	8,15, 	0,6, 	SpriteSheet.fonts);
	public static Symbol three = 	new Symbol(48, 	16,23,	0,6, 	SpriteSheet.fonts);
	public static Symbol four = 	new Symbol(48, 	24,31, 	0,6, 	SpriteSheet.fonts);
	public static Symbol five = 	new Symbol(48, 	32,39, 	0,6, 	SpriteSheet.fonts);
	public static Symbol six = 		new Symbol(48, 	40,47, 	0,6, 	SpriteSheet.fonts);
	public static Symbol seven = 	new Symbol(48, 	0,7, 	7,13,	SpriteSheet.fonts);
	public static Symbol eight = 	new Symbol(48, 	8,15, 	7,13, 	SpriteSheet.fonts);
	public static Symbol nine = 	new Symbol(48, 	16,23, 	7,13, 	SpriteSheet.fonts);
	public static Symbol zero = 	new Symbol(48, 	24,31, 	7,13, 	SpriteSheet.fonts);
	public static Symbol a = 		new Symbol(48, 	32,39, 	7,13, 	SpriteSheet.fonts);
	public static Symbol b = 		new Symbol(48, 	40,47, 	7,13, 	SpriteSheet.fonts);
	public static Symbol c = 		new Symbol(48, 	0,7, 	14,20, 	SpriteSheet.fonts);
	public static Symbol d = 		new Symbol(48, 	8,15, 	14,20, 	SpriteSheet.fonts);
	public static Symbol e = 		new Symbol(48, 	16,23, 	14,20, 	SpriteSheet.fonts);
	public static Symbol f = 		new Symbol(48, 	24,31, 	14,20, 	SpriteSheet.fonts);
	public static Symbol g = 		new Symbol(48, 	32,39, 	14,20, 	SpriteSheet.fonts);
	public static Symbol h = 		new Symbol(48, 	40,47, 	14,20, 	SpriteSheet.fonts);
	public static Symbol i = 		new Symbol(48, 	0,7, 	21,27, 	SpriteSheet.fonts);
	public static Symbol j = 		new Symbol(48, 	8,15, 	21,27, 	SpriteSheet.fonts);
	public static Symbol k = 		new Symbol(48, 	16,23, 	21,27, 	SpriteSheet.fonts);
	public static Symbol l = 		new Symbol(48, 	24,31, 	21,27, 	SpriteSheet.fonts);
	public static Symbol m = 		new Symbol(48, 	32,39, 	21,27, 	SpriteSheet.fonts);
	public static Symbol n = 		new Symbol(48, 	40,47, 	21,27, 	SpriteSheet.fonts);
	public static Symbol o = 		new Symbol(48, 	0,7, 	28,34, 	SpriteSheet.fonts);
	public static Symbol p = 		new Symbol(48, 	8,15, 	28,34, 	SpriteSheet.fonts);
	public static Symbol q = 		new Symbol(48, 	16,23, 	28,34, 	SpriteSheet.fonts);
	public static Symbol r = 		new Symbol(48, 	24,31, 	28,34, 	SpriteSheet.fonts);
	public static Symbol s = 		new Symbol(48, 	32,39, 	28,34, 	SpriteSheet.fonts);
	public static Symbol t = 		new Symbol(48, 	40,47, 	28,34, 	SpriteSheet.fonts);
	public static Symbol u = 		new Symbol(48, 	0,7, 	35,41, 	SpriteSheet.fonts);
	public static Symbol v = 		new Symbol(48, 	8,15, 	35,41, 	SpriteSheet.fonts);
	public static Symbol w = 		new Symbol(48, 	16,23, 	35,41, 	SpriteSheet.fonts);
	public static Symbol x = 		new Symbol(48, 	24,31, 	35,41, 	SpriteSheet.fonts);
	public static Symbol y = 		new Symbol(48, 	32,39, 	35,41, 	SpriteSheet.fonts);
	public static Symbol z = 		new Symbol(48, 	40,47, 	35,41, 	SpriteSheet.fonts);
	
	
	public static Symbol point =	initPoint();
	public static Symbol slash = 	initSlash();
	public static Symbol bracketopen = initBracketOpen();
	public static Symbol bracketclose = initBracketClose();
	
	public static Symbol A5x3 = 		new Symbol(33,	0,2,	0,4,	SpriteSheet.fonts5x3);
	public static Symbol B5x3 = 		new Symbol(33,	3,5,	0,4,	SpriteSheet.fonts5x3);
	public static Symbol C5x3 = 		new Symbol(33,	6,8,	0,4,	SpriteSheet.fonts5x3);
	public static Symbol D5x3 = 		new Symbol(33,	9,11,	0,4,	SpriteSheet.fonts5x3);
	public static Symbol E5x3 = 		new Symbol(33,	12,14,	0,4,	SpriteSheet.fonts5x3);
	public static Symbol F5x3 = 		new Symbol(33,	15,17,	0,4,	SpriteSheet.fonts5x3);
	public static Symbol G5x3 = 		new Symbol(33,	18,20,	0,4,	SpriteSheet.fonts5x3);
	public static Symbol H5x3 = 		new Symbol(33,	21,23,	0,4,	SpriteSheet.fonts5x3);
	public static Symbol I5x3 = 		new Symbol(33,	24,26,	0,4,	SpriteSheet.fonts5x3);
	public static Symbol J5x3 = 		new Symbol(33,	27,29,	0,4,	SpriteSheet.fonts5x3);
	public static Symbol K5x3 = 		new Symbol(33,	30,32,	0,4,	SpriteSheet.fonts5x3);
	public static Symbol L5x3 = 		new Symbol(33,	0,2,	5,9,	SpriteSheet.fonts5x3);
	public static Symbol M5x3 = 		new Symbol(33,	3,5,	5,9,	SpriteSheet.fonts5x3);
	public static Symbol N5x3 = 		new Symbol(33,	6,8,	5,9,	SpriteSheet.fonts5x3);
	public static Symbol O5x3 = 		new Symbol(33,	9,11,	5,9,	SpriteSheet.fonts5x3);
	public static Symbol P5x3 = 		new Symbol(33,	12,14,	5,9,	SpriteSheet.fonts5x3);
	public static Symbol Q5x3 = 		new Symbol(33,	15,17,	5,9,	SpriteSheet.fonts5x3);
	public static Symbol R5x3 = 		new Symbol(33,	18,20,	5,9,	SpriteSheet.fonts5x3);
	public static Symbol S5x3 = 		new Symbol(33,	21,23,	5,9,	SpriteSheet.fonts5x3);
	public static Symbol T5x3 = 		new Symbol(33,	24,26,	5,9,	SpriteSheet.fonts5x3);
	public static Symbol U5x3 = 		new Symbol(33,	27,29,	5,9,	SpriteSheet.fonts5x3);
	public static Symbol V5x3 = 		new Symbol(33,	30,32,	5,9,	SpriteSheet.fonts5x3);
	public static Symbol W5x3 = 		new Symbol(33,	0,2,	10,14,	SpriteSheet.fonts5x3);
	public static Symbol X5x3 = 		new Symbol(33,	3,5,	10,14,	SpriteSheet.fonts5x3);
	public static Symbol Y5x3 = 		new Symbol(33,	6,8,	10,14,	SpriteSheet.fonts5x3);
	public static Symbol Z5x3 = 		new Symbol(33,	9,11,	10,14,	SpriteSheet.fonts5x3);
	public static Symbol a5x3 = 		new Symbol(33,	12,14,	10,14,	SpriteSheet.fonts5x3);
	public static Symbol b5x3 = 		new Symbol(33,	15,17,	10,14,	SpriteSheet.fonts5x3);
	public static Symbol c5x3 = 		new Symbol(33,	18,20,	10,14,	SpriteSheet.fonts5x3);
	public static Symbol d5x3 = 		new Symbol(33,	21,23,	10,14,	SpriteSheet.fonts5x3);
	public static Symbol e5x3 = 		new Symbol(33,	24,26,	10,14,	SpriteSheet.fonts5x3);
	public static Symbol f5x3 = 		new Symbol(33,	27,29,	10,14,	SpriteSheet.fonts5x3);
	public static Symbol g5x3 = 		new Symbol(33,	30,32,	10,14,	SpriteSheet.fonts5x3);
	public static Symbol h5x3 = 		new Symbol(33,	0,2,	15,19,	SpriteSheet.fonts5x3);
	public static Symbol i5x3 = 		new Symbol(33,	3,5,	15,19,	SpriteSheet.fonts5x3);
	public static Symbol j5x3 = 		new Symbol(33,	6,8,	15,19,	SpriteSheet.fonts5x3);
	public static Symbol k5x3 = 		new Symbol(33,	9,11,	15,19,	SpriteSheet.fonts5x3);
	public static Symbol l5x3 = 		new Symbol(33,	12,14,	15,19,	SpriteSheet.fonts5x3);
	public static Symbol m5x3 = 		new Symbol(33,	15,17,	15,19,	SpriteSheet.fonts5x3);
	public static Symbol n5x3 = 		new Symbol(33,	18,20,	15,19,	SpriteSheet.fonts5x3);
	public static Symbol o5x3 = 		new Symbol(33,	21,23,	15,19,	SpriteSheet.fonts5x3);
	public static Symbol p5x3 = 		new Symbol(33,	24,26,	15,19,	SpriteSheet.fonts5x3);
	public static Symbol q5x3 = 		new Symbol(33,	27,29,	15,19,	SpriteSheet.fonts5x3);
	public static Symbol r5x3 = 		new Symbol(33,	30,32,	15,19,	SpriteSheet.fonts5x3);
	public static Symbol s5x3 = 		new Symbol(33,	0,2,	20,24,	SpriteSheet.fonts5x3);
	public static Symbol t5x3 = 		new Symbol(33,	3,5,	20,24,	SpriteSheet.fonts5x3);
	public static Symbol u5x3 = 		new Symbol(33,	6,8,	20,24,	SpriteSheet.fonts5x3);
	public static Symbol v5x3 = 		new Symbol(33,	9,11,	20,24,	SpriteSheet.fonts5x3);
	public static Symbol w5x3 = 		new Symbol(33,	12,14,	20,24,	SpriteSheet.fonts5x3);
	public static Symbol x5x3 = 		new Symbol(33,	15,17,	20,24,	SpriteSheet.fonts5x3);
	public static Symbol y5x3 = 		new Symbol(33,	18,20,	20,24,	SpriteSheet.fonts5x3);
	public static Symbol z5x3 = 		new Symbol(33,	21,23,	20,24,	SpriteSheet.fonts5x3);
	public static Symbol one5x3 = 		new Symbol(33,	24,26,	20,24,	SpriteSheet.fonts5x3);
	public static Symbol two5x3 = 		new Symbol(33,	27,29,	20,24,	SpriteSheet.fonts5x3);
	public static Symbol three5x3 =		new Symbol(33,	30,32,	20,24,	SpriteSheet.fonts5x3);
	public static Symbol four5x3 = 		new Symbol(33,	0,2,	25,29,	SpriteSheet.fonts5x3);
	public static Symbol five5x3 = 		new Symbol(33,	3,5,	25,29,	SpriteSheet.fonts5x3);
	public static Symbol six5x3 = 		new Symbol(33,	6,8,	25,29,	SpriteSheet.fonts5x3);
	public static Symbol seven5x3 = 	new Symbol(33,	9,11,	25,29,	SpriteSheet.fonts5x3);
	public static Symbol eight5x3 = 	new Symbol(33,	12,14,	25,29,	SpriteSheet.fonts5x3);
	public static Symbol nine5x3 = 		new Symbol(33,	15,17,	25,29,	SpriteSheet.fonts5x3);
	public static Symbol zero5x3 = 		new Symbol(33,	18,20,	25,29,	SpriteSheet.fonts5x3);
	
	public static Symbol point5x3 =		initPoint5x3();
	public static Symbol slash5x3 = 	initSlash5x3();
	public static Symbol bracketopen5x3 = initBracketOpen5x3();
	public static Symbol bracketclose5x3 =initBracketClose5x3();
	
	public static Symbol zero5x8 = 		new Symbol(50,	0,4,	0,7,	SpriteSheet.fonts5x8);
	public static Symbol one5x8 = 		new Symbol(50,	5,9,	0,7,	SpriteSheet.fonts5x8);
	public static Symbol two5x8 = 		new Symbol(50,	10,14,	0,7,	SpriteSheet.fonts5x8);
	public static Symbol three5x8=		new Symbol(50,	15,19,	0,7,	SpriteSheet.fonts5x8);
	public static Symbol four5x8=		new Symbol(50,  20,24,	0,7,	SpriteSheet.fonts5x8);
	public static Symbol five5x8=		new Symbol(50,	25,29,	0,7,	SpriteSheet.fonts5x8);
	public static Symbol six5x8 = 		new Symbol(50,	30,34,	0,7,	SpriteSheet.fonts5x8);
	public static Symbol seven5x8 = 	new Symbol(50, 	35,39,	0,7,	SpriteSheet.fonts5x8);
	public static Symbol eight5x8 = 	new Symbol(50,  40,44,  0,7,	SpriteSheet.fonts5x8);
	public static Symbol nine5x8 = 		new Symbol(50,  45,49,  0,7,    SpriteSheet.fonts5x8);
	public static Symbol A5x8 = 		new Symbol(50,	0,4,	8,15,	SpriteSheet.fonts5x8);
	public static Symbol B5x8 = 		new Symbol(50,	5,9,	8,15,	SpriteSheet.fonts5x8);
	public static Symbol C5x8 = 		new Symbol(50,	10,14,	8,15,	SpriteSheet.fonts5x8);
	public static Symbol D5x8=			new Symbol(50,	15,19,	8,15,	SpriteSheet.fonts5x8);
	public static Symbol E5x8=			new Symbol(50,  20,24,	8,15,	SpriteSheet.fonts5x8);
	public static Symbol F5x8=			new Symbol(50,	25,29,	8,15,	SpriteSheet.fonts5x8);
	public static Symbol G5x8 = 		new Symbol(50,	30,34,	8,15,	SpriteSheet.fonts5x8);
	public static Symbol H5x8 = 		new Symbol(50, 	35,39,	8,15,	SpriteSheet.fonts5x8);
	public static Symbol I5x8 = 		new Symbol(50,  40,44,  8,15,	SpriteSheet.fonts5x8);
	public static Symbol J5x8 = 		new Symbol(50,  45,49,  8,15,   SpriteSheet.fonts5x8);
	public static Symbol K5x8 = 		new Symbol(50,	0,4,	16,23,	SpriteSheet.fonts5x8);
	public static Symbol L5x8 = 		new Symbol(50,	5,9,	16,23,	SpriteSheet.fonts5x8);
	public static Symbol M5x8 = 		new Symbol(50,	10,14,	16,23,	SpriteSheet.fonts5x8);
	public static Symbol N5x8=			new Symbol(50,	15,19,	16,23,	SpriteSheet.fonts5x8);
	public static Symbol O5x8=			new Symbol(50,  20,24,	16,23,	SpriteSheet.fonts5x8);
	public static Symbol P5x8=			new Symbol(50,	25,29,	16,23,	SpriteSheet.fonts5x8);
	public static Symbol Q5x8 = 		new Symbol(50,	30,34,	16,23,	SpriteSheet.fonts5x8);
	public static Symbol R5x8 = 		new Symbol(50, 	35,39,	16,23,	SpriteSheet.fonts5x8);
	public static Symbol S5x8 = 		new Symbol(50,  40,44,  16,23,	SpriteSheet.fonts5x8);
	public static Symbol T5x8 = 		new Symbol(50,  45,49,  16,23,  SpriteSheet.fonts5x8);
	public static Symbol U5x8 = 		new Symbol(50,	0,4,	24,31,	SpriteSheet.fonts5x8);
	public static Symbol V5x8 = 		new Symbol(50,	5,9,	24,31,	SpriteSheet.fonts5x8);
	public static Symbol W5x8 = 		new Symbol(50,	10,14,	24,31,	SpriteSheet.fonts5x8);
	public static Symbol X5x8=			new Symbol(50,	15,19,	24,31,	SpriteSheet.fonts5x8);
	public static Symbol Y5x8=			new Symbol(50,  20,24,	24,31,	SpriteSheet.fonts5x8);
	public static Symbol Z5x8=			new Symbol(50,	25,29,	24,31,	SpriteSheet.fonts5x8);
	
	public static Symbol a5x8 = 		new Symbol(50,	0,4,	32,39,	SpriteSheet.fonts5x8);
	public static Symbol b5x8 = 		new Symbol(50,	5,9,	32,39,	SpriteSheet.fonts5x8);
	public static Symbol c5x8 = 		new Symbol(50,	10,14,	32,39,	SpriteSheet.fonts5x8);
	public static Symbol d5x8=			new Symbol(50,	15,19,	32,39,	SpriteSheet.fonts5x8);
	public static Symbol e5x8=			new Symbol(50,  20,24,	32,39,	SpriteSheet.fonts5x8);
	public static Symbol f5x8=			new Symbol(50,	25,29,	32,39,	SpriteSheet.fonts5x8);
	public static Symbol g5x8 = 		new Symbol(50,	30,34,	32,39,	SpriteSheet.fonts5x8);
	public static Symbol h5x8 = 		new Symbol(50, 	35,39,	32,39,	SpriteSheet.fonts5x8);
	public static Symbol i5x8 = 		new Symbol(50,  40,44,  32,39,	SpriteSheet.fonts5x8);
	public static Symbol j5x8 = 		new Symbol(50,  45,49,  32,39,  SpriteSheet.fonts5x8);
	public static Symbol k5x8 = 		new Symbol(50,	0,4,	40,47,	SpriteSheet.fonts5x8);
	public static Symbol l5x8 = 		new Symbol(50,	5,9,	40,47,	SpriteSheet.fonts5x8);
	public static Symbol m5x8 = 		new Symbol(50,	10,14,	40,47,	SpriteSheet.fonts5x8);
	public static Symbol n5x8=			new Symbol(50,	15,19,	40,47,	SpriteSheet.fonts5x8);
	public static Symbol o5x8=			new Symbol(50,  20,24,	40,47,	SpriteSheet.fonts5x8);
	public static Symbol p5x8=			new Symbol(50,	25,29,	40,47,	SpriteSheet.fonts5x8);
	public static Symbol q5x8 = 		new Symbol(50,	30,34,	40,47,	SpriteSheet.fonts5x8);
	public static Symbol r5x8 = 		new Symbol(50, 	35,39,	40,47,	SpriteSheet.fonts5x8);
	public static Symbol s5x8 = 		new Symbol(50,  40,44,  40,47,	SpriteSheet.fonts5x8);
	public static Symbol t5x8 = 		new Symbol(50,  45,49,  40,47,  SpriteSheet.fonts5x8);
	public static Symbol u5x8 = 		new Symbol(50,	0,4,	48,55,	SpriteSheet.fonts5x8);
	public static Symbol v5x8 = 		new Symbol(50,	5,9,	48,55,	SpriteSheet.fonts5x8);
	public static Symbol w5x8 = 		new Symbol(50,	10,14,	48,55,	SpriteSheet.fonts5x8);
	public static Symbol x5x8=			new Symbol(50,	15,19,	48,55,	SpriteSheet.fonts5x8);
	public static Symbol y5x8=			new Symbol(50,  20,24,	48,55,	SpriteSheet.fonts5x8);
	public static Symbol z5x8=			new Symbol(50,	25,29,	48,55,	SpriteSheet.fonts5x8);
	
	public static Symbol point5x8 =		initPoint5x8();
	public static Symbol slash5x8 = 	initSlash5x8();
	public static Symbol bracketopen5x8 = initBracketOpen5x8();
	public static Symbol bracketclose5x8 =initBracketClose5x8();
	
	public Symbol(int sheetBaseWidth, int xfrom,int xuntil,int yfrom,int yuntil, SpriteSheet sheet) {
		this.WIDTH = (xuntil-xfrom)+1;
		this.HEIGHT = (yuntil-yfrom)+1;
		pixels = new int[this.WIDTH*this.HEIGHT];
		cutOutSymbol(sheetBaseWidth,sheet.getSprite(sheet.WIDTH, sheet.HEIGHT),xfrom,xuntil,yfrom,yuntil);
	}
	public Symbol(int[] pixels, int width, int height) {
		this.WIDTH = width;
		this.HEIGHT = height;
		this.pixels = pixels;
	}
	private void cutOutSymbol(int sheetBaseWidth, int[] sheet, int xfrom,int xuntil,int yfrom,int yuntil) {
		int index = 0;
		for(int i = yfrom; i <= yuntil;i++) {
			for(int j = xfrom; j <= xuntil;j++) {
				int color = sheet[j + i * sheetBaseWidth];
				pixels[index] = color;
				index++;
			}
		}
	}
	private static Symbol initPoint() {
		int[] pixels = new int[8*7];
		pixels[1+6*8] = -1;
		 return new Symbol(pixels, 8,7);
	}
	private static Symbol initSlash() {
		int[] pixels = new int[]{0, 0, 0, 0, 0,-1, 0, 0,
								 0, 0, 0, 0,-1,-1, 0, 0,
								 0, 0, 0, 0,-1, 0, 0, 0,
								 0, 0, 0,-1,-1, 0, 0, 0,
								 0, 0, 0,-1, 0, 0, 0, 0,
								 0, 0,-1,-1, 0, 0, 0, 0,
								 0, 0,-1, 0, 0, 0, 0, 0};
		return new Symbol(pixels,8,7);
	}
	private static Symbol initBracketOpen() {
		int[] pixels = new int[]{0, 0, 0, 0, 0, 0,-1,-1,
								 0, 0, 0, 0, 0,-1, 0, 0,
								 0, 0, 0, 0, 0,-1, 0, 0,
								 0, 0, 0, 0, 0,-1, 0, 0,
								 0, 0, 0, 0, 0,-1, 0, 0,
								 0, 0, 0, 0, 0,-1, 0, 0,
								 0, 0, 0, 0, 0, 0,-1,-1};
		return new Symbol(pixels,8,7);
	}
	private static Symbol initBracketClose() {
		int[] pixels = new int[]{-1,-1, 0, 0, 0, 0, 0, 0,
								 0, 0,-1, 0, 0, 0, 0, 0,
								 0, 0,-1, 0, 0, 0, 0, 0,
								 0, 0,-1, 0, 0, 0, 0, 0,
								 0, 0,-1, 0, 0, 0, 0, 0,
								 0, 0,-1, 0, 0, 0, 0, 0,
								 -1,-1, 0, 0, 0, 0, 0, 0};
		return new Symbol(pixels,8,7);
	}
	
	private static Symbol initPoint5x3() {
		int[] pixels = new int[]{0,  0, 0,
				 				 0,  0, 0,
				 				 0,  0, 0,
				 				 0, -1, 0,
				 				 0,  0, 0};
		 return new Symbol(pixels, 5,3);
	}
	private static Symbol initSlash5x3() {
		int[] pixels = new int[]{ 0,  0, -1,
								  0, -1,  0,
								  0, -1,  0,
								 -1,  0,  0,
								  0,  0,  0};
		return new Symbol(pixels,5,3);
	}
	private static Symbol initPoint5x8() {
		int[] pixels = new int[]{0, 0, 0, 0, 0,
				 				 0, 0, 0, 0, 0,
				 				 0, 0, 0, 0, 0,
				 				 0, 0, 0, 0, 0,
				 				 0, 0, 0, 0, 0,
				 				 0, 0, 0, 0, 0,
				 				 -1,0, 0, 0, 0, 
				 				 0, 0, 0, 0, 0};
		 return new Symbol(pixels, 5,8);
	}
	private static Symbol initSlash5x8() {
		int[] pixels = new int[]{	0, 0, 0, 0,-1,
									0, 0, 0, 0,-1,
									0, 0, 0,-1, 0,
									0, 0,-1, 0, 0,
									0,-1, 0, 0, 0,
								   -1, 0, 0, 0, 0,
								   -1, 0, 0, 0, 0, 
									0, 0, 0, 0, 0};
		return new Symbol(pixels,5,8);
	}
	private static Symbol initBracketOpen5x8() {
		int[] pixels = new int[]{	0, 0, 0, 0,-1,
									0, 0, 0,-1, 0,
									0, 0,-1, 0, 0,
									0, 0,-1, 0, 0,
									0, 0,-1, 0, 0,
								    0, 0, 0,-1, 0,
								    0, 0, 0, 0,-1, 
									0, 0, 0, 0, 0};
		return new Symbol(pixels,5,8);
	}
	private static Symbol initBracketClose5x8() {
		int[] pixels = new int[]{  -1, 0, 0, 0, 0,
									0,-1, 0, 0, 0,
									0, 0,-1, 0, 0,
									0, 0,-1, 0, 0,
									0, 0,-1, 0, 0,
								    0,-1, 0, 0, 0,
								   -1, 0, 0, 0, 0, 
									0, 0, 0, 0, 0};
		return new Symbol(pixels,5,8);
	}
	private static Symbol initBracketOpen5x3() {
		int[] pixels = new int[]{ 	0,  0, -1,
									0, -1,  0,
									0, -1,  0,
									0, 0,  -1,
									0,  0,  0};
		return new Symbol(pixels,5,3);
	}
	private static Symbol initBracketClose5x3() {
		int[] pixels = new int[]{ 	-1,  0, 0,
				  					0, -1,  0,
				  					0, -1,  0,
				  					-1,  0,  0,
				  					0,  0,  0};
		return new Symbol(pixels,5,3);
	}
}
