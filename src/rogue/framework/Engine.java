package rogue.framework;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import rogue.framework.gui.WindowManager;
import rogue.framework.states.StateManager;

public class Engine {

	//private static boolean running;
	//private static boolean hasChange = false;
	
	private static WindowManager windowManager;
	private static StateManager stateManager;
	private static final int X = 1920;
	private static final int Y = 1080;
	private static final int MAP_X_FROM = 420;
	private static final int MAP_X_UNTIL = 1499;
	public static int turn=0; 
	

	public static void init() {	
		stateManager = new StateManager(X,Y,MAP_X_FROM,MAP_X_UNTIL);
//ToDo write main menu
		//stateManager.stackDungeonState();
		//stateManager.stackArenaState();
		//running  = true;
		windowManager = new WindowManager(X,Y);
		windowManager.addMouse(new Mouse());
		windowManager.addKey(new KeyBoard());
		windowManager.start();
		loop();
	}
	private static void loop() {
		update();
		render();
	}
	private static void update() {
		stateManager.update();
	}
	private static void render() {
		renderSprite();
		windowManager.render(stateManager.render());
	}
	private static void renderSprite() {
		if(stateManager.hasSprite()) {
		}
	}
	
	private static class Mouse implements MouseListener{

		
		@Override
		public void mouseClicked(MouseEvent e) {
			turn++;
			stateManager.mouseClicked(e);
			loop();
		}
		@Override
		public void mousePressed(MouseEvent e) { }
		@Override
		public void mouseReleased(MouseEvent e) { }
		@Override
		public void mouseEntered(MouseEvent e) { }
		@Override
		public void mouseExited(MouseEvent e) { }
	}
	
	private static class KeyBoard implements KeyListener{

		@Override
		public void keyTyped(KeyEvent e) {
		}
		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				windowManager.dispose();
			}else {
				stateManager.keyPressed(e);
			}
			loop();
			
		}
		@Override
		public void keyReleased(KeyEvent e) {}

	}
}
