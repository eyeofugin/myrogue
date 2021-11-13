package rogue.game.states;

import java.awt.event.KeyEvent;

import rogue.framework.eventhandling.Connector;
import rogue.framework.eventhandling.Event;
import rogue.framework.resources.Property;
import rogue.framework.resources.Resources;
import rogue.framework.states.State;
import rogue.game.world.World;
import rogue.game.world.objects.PlayableCharacter;
import rogue.graphics.EntityInformationContainer;
import util.MovementOption;

public class DungeonState extends State{

	private World world;
	private PlayableCharacter player;
	
	private EntityInformationContainer activeCharacterCanvas;
	private boolean characterHasChanges = true;
	
	
	
	
	public DungeonState(Connector connector) {
		super(connector);
		player = new PlayableCharacter(3,3,Resources.KNIGHT,"player",Resources.KNIGHTMALE,MovementOption.PLAYER,this.connector);
		player.setMeeleeAtk1(10);
		player.setPlayer();
		this.world = new World(player,connector);
		this.activeCharacterCanvas = new EntityInformationContainer(player,connector);
		//this.hud = new HUD();
	}
	
	@Override
	protected void update() {
		this.activeCharacterCanvas.checkUdate(player);
	}

	@Override
	protected int[] render() {
		int[] pixels = new int[Property.END_OF_X*Property.END_OF_Y];
		pixels = backGround(pixels);
		int[] map = world.getRoom().render().get(0);
		
		int indexWorld = 0;
		for(int i = Property.START_OF_ROOM_Y; i < Property.END_OF_ROOM_Y; i++) {
			for(int j = Property.START_OF_ROOM_X; j < Property.END_OF_ROOM_X; j++) {
				pixels[j+i*Property.END_OF_X] = map[indexWorld];
				indexWorld++;
			}
		}
		int indexActiveCharacter=0;
		for(int i = Property.START_OF_ACTIVE_CHAR_Y; i < Property.END_OF_ACTIVE_CHAR_Y; i++) {
			for(int j = Property.START_OF_ACTIVE_CHAR_X; j < Property.END_OF_ACTIVE_CHAR_X; j++) {
				pixels[j+i*Property.END_OF_X] = this.activeCharacterCanvas.getPixels()[indexActiveCharacter];
				indexActiveCharacter++;
			}
		}
		
		return pixels;
	}
	
	private int[] backGround(int[] pixels) {
		for(int i = Property.START_OF_ROOM_BACKGROUND_Y; i < Property.END_OF_ROOM_BACKGROUND_Y; i++) {
			for(int j = Property.START_OF_ROOM_BACKGROUND_X; j < Property.END_OF_ROOM_BACKGROUND_X; j++) {
				pixels[j+i*Property.END_OF_X] = 15066597;
			}
		}
		return pixels;
	}

	@Override
	protected void mouseClicked(Event e) {
		if(e.getEventId().equals("tabChange")) {
			this.player.setActiveTab(e.getTab());
		}
		this.world.getRoom().mouseClicked(e);
		
	}

	@Override
	protected void keyPressed(KeyEvent e) {
		world.getRoom().keyPressed(e);
	}
 }
