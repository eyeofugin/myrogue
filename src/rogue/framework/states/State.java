package rogue.framework.states;

import java.awt.event.KeyEvent;

import rogue.framework.eventhandling.Connector;
import rogue.framework.eventhandling.Event;

public abstract class State {

	protected Connector connector;
	
	protected abstract void update();
	protected abstract int[] render();
	protected abstract void mouseClicked(Event e);
	protected abstract void keyPressed(KeyEvent e);
	
	public State(Connector connector) {
		this.connector = connector;
	}
	
}
