package util;

public class Symbol {

	public final int WIDTH,HEIGHT;
	public int[] pixels;
	
	public static Symbol one = 		new Symbol(48,	0,7, 	0,6, 	8,7,SpriteSheet.fonts);
	public static Symbol two = 		new Symbol(48, 	8,15, 	0,6, 	8,7,SpriteSheet.fonts);
	public static Symbol three = 	new Symbol(48, 	16,23,	0,6, 	8,7,SpriteSheet.fonts);
	public static Symbol four = 	new Symbol(48, 	24,31, 	0,6, 	8,7,SpriteSheet.fonts);
	public static Symbol five = 	new Symbol(48, 	32,39, 	0,6, 	8,7,SpriteSheet.fonts);
	public static Symbol six = 		new Symbol(48, 	40,47, 	0,6, 	8,7,SpriteSheet.fonts);
	public static Symbol seven = 	new Symbol(48, 	0,7, 	7,13,	8,7,SpriteSheet.fonts);
	public static Symbol eight = 	new Symbol(48, 	8,15, 	7,13, 	8,7,SpriteSheet.fonts);
	public static Symbol nine = 	new Symbol(48, 	16,23, 	7,13, 	8,7,SpriteSheet.fonts);
	public static Symbol zero = 	new Symbol(48, 	24,31, 	7,13, 	8,7,SpriteSheet.fonts);
	public static Symbol a = 		new Symbol(48, 	32,39, 	7,13, 	8,7,SpriteSheet.fonts);
	public static Symbol b = 		new Symbol(48, 	40,47, 	7,13, 	8,7,SpriteSheet.fonts);
	public static Symbol c = 		new Symbol(48, 	0,7, 	14,20, 	8,7,SpriteSheet.fonts);
	public static Symbol d = 		new Symbol(48, 	8,15, 	14,20, 	8,7,SpriteSheet.fonts);
	public static Symbol e = 		new Symbol(48, 	16,23, 	14,20, 	8,7,SpriteSheet.fonts);
	public static Symbol f = 		new Symbol(48, 	24,31, 	14,20, 	8,7,SpriteSheet.fonts);
	public static Symbol g = 		new Symbol(48, 	32,39, 	14,20, 	8,7,SpriteSheet.fonts);
	public static Symbol h = 		new Symbol(48, 	40,47, 	14,20, 	8,7,SpriteSheet.fonts);
	public static Symbol i = 		new Symbol(48, 	0,7, 	21,27, 	8,7,SpriteSheet.fonts);
	public static Symbol j = 		new Symbol(48, 	8,15, 	21,27, 	8,7,SpriteSheet.fonts);
	public static Symbol k = 		new Symbol(48, 	16,23, 	21,27, 	8,7,SpriteSheet.fonts);
	public static Symbol l = 		new Symbol(48, 	24,31, 	21,27, 	8,7,SpriteSheet.fonts);
	public static Symbol m = 		new Symbol(48, 	32,39, 	21,27, 	8,7,SpriteSheet.fonts);
	public static Symbol n = 		new Symbol(48, 	40,47, 	21,27, 	8,7,SpriteSheet.fonts);
	public static Symbol o = 		new Symbol(48, 	0,7, 	28,34, 	8,7,SpriteSheet.fonts);
	public static Symbol p = 		new Symbol(48, 	8,15, 	28,34, 	8,7,SpriteSheet.fonts);
	public static Symbol q = 		new Symbol(48, 	16,23, 	28,34, 	8,7,SpriteSheet.fonts);
	public static Symbol r = 		new Symbol(48, 	24,31, 	28,34, 	8,7,SpriteSheet.fonts);
	public static Symbol s = 		new Symbol(48, 	32,39, 	28,34, 	8,7,SpriteSheet.fonts);
	public static Symbol t = 		new Symbol(48, 	40,47, 	28,34, 	8,7,SpriteSheet.fonts);
	public static Symbol u = 		new Symbol(48, 	0,7, 	35,41, 	8,7,SpriteSheet.fonts);
	public static Symbol v = 		new Symbol(48, 	8,15, 	35,41, 	8,7,SpriteSheet.fonts);
	public static Symbol w = 		new Symbol(48, 	16,23, 	35,41, 	8,7,SpriteSheet.fonts);
	public static Symbol x = 		new Symbol(48, 	24,31, 	35,41, 	8,7,SpriteSheet.fonts);
	public static Symbol y = 		new Symbol(48, 	32,39, 	35,41, 	8,7,SpriteSheet.fonts);
	public static Symbol z = 		new Symbol(48, 	40,47, 	35,41, 	8,7,SpriteSheet.fonts);
	
	
	public static Symbol point =	initPoint();
	public static Symbol slash = 	initSlash();
	
	
	
	public Symbol(int sheetBaseWidth, int xfrom,int xuntil,int yfrom,int yuntil, int width, int height, SpriteSheet sheet) {
		this.WIDTH = width;
		this.HEIGHT = height;
		pixels = new int[width*height];
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
	
}
