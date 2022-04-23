package rogue.game.world;

import rogue.framework.eventhandling.Connector;
import rogue.framework.eventhandling.Event;
import rogue.framework.resources.Property;
import rogue.graphics.InformationContainer;
import util.ConfirmationDialog;
import util.MyColor;

public class HUD extends InformationContainer{
	
	private static int CONFIRM_X = 810;
	private static int CONFIRM_X_UNTIL = CONFIRM_X + ConfirmationDialog.WIDTH;
	private static int CONFIRM_Y = 390;
	private static int CONFIRM_Y_UNTIL = CONFIRM_Y+ConfirmationDialog.HEIGHT;
	public boolean active=false;
	
	
	ConfirmationDialog confDia;
	
	public HUD(Connector connector) {
		this.connector=connector;
		this.width=Property.END_OF_X;
		this.height=Property.END_OF_Y;
		this.pixels=new int[width*height];
		
		makeVoid();
	}
	private void makeVoid() {
		for(int i = 0; i < this.pixels.length; i++) {
			this.pixels[i] = MyColor.VOID.VALUE;
		}
	}
	public int[] render() {
		if(this.confDia!=null) {
			fillWithGraphics(CONFIRM_X, CONFIRM_X_UNTIL-1, CONFIRM_Y, CONFIRM_Y_UNTIL-1, this.confDia.render(), false);
		}
		return this.pixels;
	}
	public void confirmationDialog(Event confirm) {
		confDia = new ConfirmationDialog(CONFIRM_X,CONFIRM_Y,connector);
		this.confDia.setConfirm(confirm);
		this.active=true;
	}
	public void mouseClicked(Event e) {
		if(e.getEventId().equals(Connector.CANCEL_CONFIRM_DIALOG)) {
			this.confDia=null;
			this.active=false;
		}
		if(e.getEventId().equals(Connector.CONFIRM_DIALOG)) {
			this.confDia=null;
			this.active=false;
			this.connector.fire(e.getAfterConfirmEvent());
		}
	}
}
