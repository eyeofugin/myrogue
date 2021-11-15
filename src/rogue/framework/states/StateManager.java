package rogue.framework.states;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.EmptyStackException;
import java.util.Stack;

import rogue.framework.eventhandling.Connector;
import rogue.framework.eventhandling.Event;
import rogue.framework.resources.Property;
import rogue.game.states.ArenaState;
import rogue.game.states.DungeonState;


public class StateManager {

	private static final int MOUSE_BUTTON_L = 1;
	private static final int MOUSE_BUTTON_R = 3;
	
	private Stack<State> states;
	private Connector connector;
	
	public StateManager(int x, int y, int mapXFrom, int mapXUntil) {
		states = new Stack<State>();
		connector = new Connector(x,y,mapXFrom,mapXUntil);
	}
	public void stackDungeonState() {
		DungeonState state = new DungeonState(this.connector);
		states.add(state);
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
	
	public void mouseClicked(MouseEvent e) {
		Event event = new Event();

		if(e.getButton()== MOUSE_BUTTON_L) {
			event = this.connector.getEvent(e);
		}
		if(e.getButton() == MOUSE_BUTTON_R) {
			event = this.connector.getContext(e);
		}
		
		if(event!=null && event.getEventId()!=null) {

			System.out.println("event: " + event.getEventId());
			this.states.peek().mouseClicked(event);
		}
	}
	public void keyPressed(KeyEvent e) {
		this.states.peek().keyPressed(e);
	}
}
