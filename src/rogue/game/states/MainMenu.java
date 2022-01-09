package rogue.game.states;

import java.awt.event.KeyEvent;

import rogue.framework.eventhandling.Connector;
import rogue.framework.eventhandling.Event;
import rogue.framework.states.State;

public class MainMenu extends State{

	private int mode = 1;
	
	public MainMenu(Connector connector) {
		super(connector);
	}
	
	@Override
	protected void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected int[] render() {
		int[] p =new int[960*640];
		for(int i = 0; i < p.length; i++) {
			p[i] = 24089*mode;
		}
		return p;
	}

	@Override
	protected void mouseClicked(Event e) {
		mode++;
	}

	@Override
	protected void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean hasSprite() {
		return false;
	}

}
