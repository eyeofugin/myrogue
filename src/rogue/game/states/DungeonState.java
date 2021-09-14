package rogue.game.states;

import java.awt.event.KeyEvent;

import rogue.framework.eventhandling.Connector;
import rogue.framework.eventhandling.Event;
import rogue.framework.states.State;
import rogue.game.world.World;
import rogue.game.world.objects.PlayableCharacter;
import rogue.graphics.CharacterInformationContainer;
import rogue.graphics.InformationContainer;

public class DungeonState extends State{

	private World world;
	private PlayableCharacter player;
	
	private CharacterInformationContainer activeCharacterCanvas;
	private boolean characterHasChanges = true;
	
	
	
	public DungeonState(Connector connector) {
		super(connector);
		player = new PlayableCharacter(3,3,(byte)3,"player",(byte)0,this.connector);
		player.setPlayer();
		this.world = new World(player,connector);
		this.activeCharacterCanvas = new CharacterInformationContainer(player,connector);
		//this.hud = new HUD();
	}
	
	@Override
	protected void update() {
		this.activeCharacterCanvas.checkUdate(player);
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
			this.player.setActiveTab(e.getTab());
		}
		this.world.getRoom().mouseClicked(e);
		
	}

	@Override
	protected void keyPressed(KeyEvent e) {
		world.getRoom().keyPressed(e);
	}
 }
