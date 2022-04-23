package rogue.game.world;

import rogue.framework.eventhandling.Connector;
import rogue.graphics.InformationContainer;

public class Menu extends InformationContainer{
	
	private static int GREETING_X = 860;
	private static int GREETING_Y = 300;
	private static int GREETING_WIDTH = 200;
	private static int GREETING_HEIGHT= 40;
	private static int GREETING_XU = GREETING_X+GREETING_WIDTH;
	private static int GREETING_YU= GREETING_Y+GREETING_HEIGHT;
	private static String GREETING = "Welcome to the Arena";
	private static int DRAFT_BUTTON_X = 950;
	private static int DRAFT_BUTTON_Y = 400;
	private static String DRAFT_BUTTON = "Start Draft";
	private static int EXIT_BUTTON_X = 950;
	private static int EXIT_BUTTON_Y = 500;
	private static String EXIT_BUTTON = "Exit";
	

	public Menu(int xanchor, int yanchor, int width, int height,Connector connector) {
		this.xanchor = xanchor;
		this.yanchor = yanchor;
		this.width = width;
		this.height = height;
		this.connector= connector;
		this.pixels=new int[width*height];
	}
	
	public int[] render() {
		greeting();
		buttons();
		return this.pixels;
	}
	private void greeting() {
		writeLine(GREETING, GREETING_X, GREETING_XU, GREETING_Y, GREETING_YU);
	}
	private void buttons() {
		
		writeButton(DRAFT_BUTTON, DRAFT_BUTTON_X, DRAFT_BUTTON_Y);
		writeButton(EXIT_BUTTON,EXIT_BUTTON_X,EXIT_BUTTON_Y);
	}
	
}
