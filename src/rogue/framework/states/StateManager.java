package rogue.framework.states;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.EmptyStackException;
import java.util.Stack;

import rogue.framework.eventhandling.Connector;
import rogue.framework.eventhandling.Event;
import rogue.game.states.ArenaState;
import rogue.game.states.DungeonState;


public class StateManager {

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
		int[] p = new int[960*640];
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
		for(Event ev : this.connector.getEventsList()) {
		}
		Event event = this.connector.getEvent(e);
		if(event!=null && event.getEventId()!=null) {
			
			this.states.peek().mouseClicked(event);
		}
	}
	public void keyPressed(KeyEvent e) {
		this.states.peek().keyPressed(e);
	}
}
