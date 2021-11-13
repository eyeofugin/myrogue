package rogue.game.states;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import rogue.framework.eventhandling.Connector;
import rogue.framework.eventhandling.Event;
import rogue.framework.resources.Resources;
import rogue.framework.states.State;
import rogue.game.world.World;
import rogue.game.world.objects.PlayableCharacter;
import rogue.graphics.EntityInformationContainer;
import util.MovementOption;

public class ArenaState extends State{

	private World world;
	private List<PlayableCharacter> team = new ArrayList<PlayableCharacter>();
	private PlayableCharacter activeCharacter;
	
	private EntityInformationContainer activeCharacterCanvas;
	
	public ArenaState(Connector connector) {
		super(connector);
		
		team.add(new PlayableCharacter(1,1,Resources.KNIGHT,"knight",Resources.KNIGHTMALE,MovementOption.PLAYER,this.connector));
		team.add(new PlayableCharacter(1,2,Resources.SKELETON,"skeleton",Resources.SKELETONMALE,MovementOption.PLAYER,this.connector));
		this.activeCharacter = team.get(0);
		this.world = new World(team,connector);
		this.activeCharacterCanvas = new EntityInformationContainer(team.get(0),connector);
	}

	@Override
	protected void update() {
		this.activeCharacterCanvas.checkUdate(activeCharacter);
	}

	@Override
	protected int[] render() {
		int[] pixels = new int[1920*1080];
		pixels = backGround(pixels);
		int[] map = world.getRoom().render().get(0);
		
		int indexWorld = 0;
		for(int i = 12; i < 1068; i++) {
			for(int j = 431; j < 1487; j++) {
				pixels[j+i*1920] = map[indexWorld];
				indexWorld++;
			}
		}
		int indexActiveCharacter=0;
		for(int i = 0; i < 660; i++) {
			for(int j = 1500; j < 1920; j++) {
				pixels[j+i*1920] = this.activeCharacterCanvas.getPixels()[indexActiveCharacter];
				indexActiveCharacter++;
			}
		}
		
		return pixels;
	}
	private PlayableCharacter getCharacter(String name) {
		for(PlayableCharacter p : team) {
			if(p.getName().equals(name)) {
				return p;
			}
		}
		return null;
	}
	private int[] backGround(int[] pixels) {
		for(int i = 0; i < 1080; i++) {
			for(int j = 420; j < 1500; j++) {
				pixels[j+i*1920] = 15066597;
			}
		}
		return pixels;
	}

	@Override
	protected void mouseClicked(Event e) {
		if(e.getEventId().equals("tabChange")) {
			this.activeCharacter.setActiveTab(e.getTab());
		}
		if(e.getEventId().equals("selectPlayerEvent")) {
			activeCharacter = getCharacter(e.getObject().getName());
		}
		this.world.getRoom().mouseClicked(e);
		
	}

	@Override
	protected void keyPressed(KeyEvent e) {
		world.getRoom().keyPressed(e);
		
	}

}
