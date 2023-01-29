package rogue.framework.states;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.EmptyStackException;
import java.util.Stack;

import rogue.framework.eventhandling.Connector;
import rogue.framework.eventhandling.Event;
import rogue.framework.resources.Property;
import rogue.game.states.ArenaState;


public class StateManager {

	private static final int MOUSE_BUTTON_L = 1;
	private static final int MOUSE_BUTTON_R = 3;
	
	private Stack<State> states;
	private Connector connector;
	
	public StateManager(int x, int y, int mapXFrom, int mapXUntil) {
		states = new Stack<State>();
		connector = new Connector(x,y,mapXFrom,mapXUntil);
		ArenaState arena = new ArenaState(this.connector);
		states.add(arena);
	}
	public void stackArenaState() {
		ArenaState state = new ArenaState(this.connector);
		states.add(state);
	}
	
	public int[] render(){
		int[] p = new int[Property.END_OF_X*Property.END_OF_Y];
		try {
			p =  this.states.peek().render();
		} catch(EmptyStackException e) {
			e.printStackTrace();
		}
		return p;
	}
	public void update() {
		this.states.peek().update();
	}
	public boolean hasSprite() {
		return this.states.peek().hasSprite();
	}
	
	public void mouseClicked(MouseEvent e) {
		Event event = new Event();
		System.out.println("x"+e.getX()+ " y"+e.getY());
		Point eventPointer = new Point((int)(e.getX()*1.25), (int)(e.getY()*1.25));

		if(e.getButton()== MOUSE_BUTTON_L) {
			event = this.connector.getEvent(eventPointer);
		}
		if(e.getButton() == MOUSE_BUTTON_R) {
			event = this.connector.getContext(eventPointer);
		}
		if(event!=null && event.getEventId()!=null) {

			System.out.println("event: " + event.getEventId());
			this.states.peek().mouseClicked(event);
			while(this.connector.firedEvent!=null) {
				System.out.println(this.connector.firedEvent.getEventId() + " " + this.connector.firedEvent.getCardnr());
				Event firedEvent = this.connector.firedEvent;
				this.connector.firedEvent=null;
				this.states.peek().mouseClicked(firedEvent);
				
			}

		}
	}
	public void keyPressed(KeyEvent e) {
		this.states.peek().keyPressed(e);
	}
}
