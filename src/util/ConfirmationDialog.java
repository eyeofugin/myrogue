package util;

import rogue.framework.eventhandling.Connector;
import rogue.framework.eventhandling.Event;
import rogue.framework.resources.Resources;
import rogue.graphics.InformationContainer;

public class ConfirmationDialog extends InformationContainer{

	public static int WIDTH = 300;
	public static int HEIGHT = 150;
	public static int QUESTION_X = 10;
	public static int QUESTION_X_UNTIL=QUESTION_X+280;
	public static int QUESTION_Y = 45;
	public static int QUESTION_Y_UNTIL=QUESTION_Y+40;
	public static int CANCEL_X = 10;
	public static int CANCEL_X_UNTIL=CANCEL_X+280;
	public static int CANCEL_Y = 120;
	public static int CANCEL_Y_UNTIL=CANCEL_Y+40;
	public static int CONFIRM_X = 215;
	public static int CONFIRM_X_UNTIL=CONFIRM_X+280;
	public static int CONFIRM_Y = 120;
	public static int CONFIRM_Y_UNTIL=CONFIRM_Y+40;
	
	public static String QUESTION = "Are You Sure";
	public static String CANCEL = "Cancel.";
	public static String CONFIRM = "Confirm.";
	
	private Event confirmationEvent;
	
	public ConfirmationDialog(int xanchor, int yanchor, Connector connector) {
		
		this.xanchor=xanchor;
		this.yanchor=yanchor;
		this.pixels=new int[WIDTH*HEIGHT];
		this.height=HEIGHT;
		this.width=WIDTH;
		this.connector=connector;
		this.editor=new TextEditor(Resources.textEditorConfig);
	}
	public void setConfirm(Event e) {
		Event confirm = new Event();
		confirm.setEventId(Connector.CONFIRM_DIALOG);
		confirm.setAfterConfirmEvent(e);
		this.confirmationEvent=confirm;
	}
	public int[] render() {
		background(MyColor.RED);
		question();
		buttons();
		border();
		return this.pixels;
	}
	private void question() {
		writeLine(QUESTION, QUESTION_X, QUESTION_X_UNTIL, QUESTION_Y, QUESTION_Y_UNTIL,0,TextAlignment.CENTER,MyColor.RED,MyColor.WHITE);
	}
	private void buttons() {
		cancel();
		confirm();
	}
	private void cancel() {
		writeButton(CANCEL, CANCEL_X, CANCEL_Y);
		Event cancel = new Event();
		cancel.setEventId(Connector.CANCEL_CONFIRM_DIALOG);
		this.connector.addEvent(this.xanchor+CANCEL_X, this.yanchor+CANCEL_Y, CANCEL_X_UNTIL-1, CANCEL_Y_UNTIL-1, cancel);
	}
	private void confirm() {
		writeButton(CONFIRM, CONFIRM_X, CONFIRM_Y);
		this.connector.addEvent(this.xanchor+CONFIRM_X, this.yanchor+CONFIRM_Y, CONFIRM_X_UNTIL-1, CONFIRM_X_UNTIL-1, confirmationEvent);
	}
}
