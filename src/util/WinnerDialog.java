package util;

import rogue.framework.eventhandling.Connector;
import rogue.framework.eventhandling.Event;
import rogue.framework.resources.Resources;
import rogue.graphics.InformationContainer;

public class WinnerDialog extends InformationContainer{
	public static int WIDTH = 300;
	public static int HEIGHT = 150;
	public static int QUESTION_X = 10;
	public static int QUESTION_X_UNTIL=QUESTION_X+280;
	public static int QUESTION_Y = 45;
	public static int QUESTION_Y_UNTIL=QUESTION_Y+40;
	
	private String alert;
	
	public WinnerDialog(int xanchor, int yanchor, Connector connector) {
		
		this.xanchor=xanchor;
		this.yanchor=yanchor;
		this.pixels=new int[WIDTH*HEIGHT];
		this.height=HEIGHT;
		this.width=WIDTH;
		this.connector=connector;
		this.editor=new TextEditor(Resources.textEditorConfig);
	}
	public void setWinner(int w) {
		this.alert = "Team nr " + w + " won";
	}
	public int[] render() {
		background(MyColor.RED);
		question();
		border();
		return this.pixels;
	}
	private void question() {
		writeLine(this.alert, QUESTION_X, QUESTION_X_UNTIL, QUESTION_Y, QUESTION_Y_UNTIL,0,TextAlignment.CENTER,MyColor.RED,MyColor.WHITE);
	}
}
