package util;

public enum MyColor {

	BLACK(0),
	WHITE(-1),
	DARKGREY(7829367),
	GREEN(8964864),
	TRUEGREEN(65280),
	RED(-65536),
	BLUE(-16776961),
	YELLOW(-16776961),
	VOID(-12450784);
	
	public final int VALUE;
	
	MyColor(int value){
		this.VALUE = value;
	}
	
	public static MyColor colorize(int target) {
		if(target<0) {return MyColor.RED;}
		else{return MyColor.GREEN;}
	}
}
