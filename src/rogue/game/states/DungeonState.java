package rogue.game.states;

import java.awt.event.KeyEvent;
import java.util.List;

import rogue.framework.eventhandling.Connector;
import rogue.framework.eventhandling.Event;
import rogue.framework.resources.Property;
import rogue.framework.resources.Resources;
import rogue.framework.states.State;
import rogue.game.world.World;
import rogue.game.world.objects.Entity;
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
		this.activeCharacterCanvas = new EntityInformationContainer(player,EntityInformationContainer.PLAYER_CONFIG,connector);
		//this.hud = new HUD();
	}
	
	@Override
	protected void update() {
		this.activeCharacterCanvas.checkUdate(player);
		this.world.getRoom().update();
	}

	@Override
	protected int[] render() {
		int[] pixels = new int[Property.END_OF_X*Property.END_OF_Y];
		pixels = backGround(pixels);
		List<int[]> roomPixels = world.getRoom().render();
		int[] map = roomPixels.get(0);
		
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
		int[] activeNpc = roomPixels.get(1);
		int indexActiveNpc=0;
		for(int i = Property.START_OF_ACTIVE_NPC_Y; i < Property.END_OF_ACTIVE_NPC_Y; i++) {
			for(int j = Property.START_OF_ACTIVE_NPC_X; j < Property.END_OF_ACTIVE_NPC_X; j++) {
				pixels[j+i*Property.END_OF_X] = activeNpc[indexActiveNpc];
				indexActiveNpc++;
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
			Entity entity = Entity.class.cast(e.getObject());
			System.out.println(entity.toString());
			if(entity.getName().equals("player")) {
				System.out.println("!!");
				this.player.setActiveTab(e.getTab());
				return;
			}
		}
		this.world.getRoom().mouseClicked(e);
		
	}

	@Override
	protected void keyPressed(KeyEvent e) {
		world.getRoom().keyPressed(e);
	}
 }
