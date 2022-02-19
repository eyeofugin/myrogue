package rogue.game.states;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import rogue.framework.eventhandling.Connector;
import rogue.framework.eventhandling.Event;
import rogue.framework.resources.Property;
import rogue.framework.resources.Resources;
import rogue.framework.states.State;
import rogue.game.Init;
import rogue.game.pvp.CharacterLibrary;
import rogue.game.pvp.Team;
import rogue.game.world.Arena;
import rogue.game.world.objects.PlayableCharacter;
import rogue.graphics.EntityInformationContainer;
import rogue.graphics.InformationContainer;
import util.MovementOption;
import util.MyColor;
import util.TextAlignment;
import util.TextEditor;

public class ArenaState extends State{

	private Arena arena;
	private List<Team> teams = new ArrayList<Team>();
	private int activePointer;
	private int maxPointer;
	private PlayableCharacter activeCharacter;
	private boolean inChangeTeam=false;
	private EntityInformationContainer activeCharacterCanvas;
	
	public ArenaState(Connector connector) {
		super(connector);
		mockTeams();
		this.activeCharacter = teams.get(0).getCharacters().get(0);
		this.activeCharacterCanvas = new EntityInformationContainer(this.activeCharacter,EntityInformationContainer.PLAYER_CONFIG,TextEditor.conf8x7,connector);
		this.arena = new Arena(Init.ROOMS[1],this.connector);
		this.arena.initTeams(teams);
		this.arena.openViewForTeamNr(1);
		this.activePointer=1;
		this.maxPointer=teams.size();
	}

	@Override
	protected void update() {
		this.activeCharacterCanvas.checkUdate(activeCharacter);
		this.arena.update();
	}

	@Override
	protected int[] render() {
		int[] pixels = new int[1920*1080];
		pixels = backGround(pixels);
		
		if(inChangeTeam) {
			getChangeConfirm(pixels);
		}else {
			
			List<int[]> roomPixels = this.arena.render();
			int[] map = roomPixels.get(0);
			
			int indexWorld = 0;
			for(int i = Property.START_OF_ROOM_Y; i < Property.END_OF_ROOM_Y; i++) {
				for(int j = Property.START_OF_ROOM_X; j < Property.END_OF_ROOM_X; j++) {
					pixels[j+i*Property.END_OF_X] = map[indexWorld];
					indexWorld++;
				}
			}
			int[] activeTeam = roomPixels.get(1);
			int indexActiveCharacter=0;
			for(int i = Property.START_OF_ACTIVE_CHAR_Y; i < Property.END_OF_ACTIVE_CHAR_Y; i++) {
				for(int j = Property.START_OF_ACTIVE_CHAR_X; j < Property.END_OF_ACTIVE_CHAR_X; j++) {
					pixels[j+i*Property.END_OF_X] = activeTeam[indexActiveCharacter];
					indexActiveCharacter++;
				}
			}
			int[] activeNpc = roomPixels.get(2);
			int indexActiveNpc=0;
			for(int i = Property.START_OF_ACTIVE_NPC_Y; i < Property.END_OF_ACTIVE_NPC_Y; i++) {
				for(int j = Property.START_OF_ACTIVE_NPC_X; j < Property.END_OF_ACTIVE_NPC_X; j++) {
					pixels[j+i*Property.END_OF_X] = activeNpc[indexActiveNpc];
					indexActiveNpc++;
				}
			}
			int[] buttonPanel = roomPixels.get(4);
			int indexButtonPanel=0;
			for(int i = Property.BUTTON_PANEL_Y_FROM; i < Property.BUTTON_PANEL_Y_UNTIL; i++) {
				for(int j = Property.BUTTON_PANEL_X_FROM; j < Property.BUTTON_PANEL_X_UNTIL; j++) {
					pixels[j+i*Property.END_OF_X] = buttonPanel[indexButtonPanel];
					indexButtonPanel++;
				}
			}
			//getButtons(pixels);
			int[] minimap = roomPixels.get(3);
			int minimapIndex=0;
			for(int i = Property.MINIMAP_Y_FROM; i < Property.MINIMAP_Y_UNTIL; i++) {
				for(int j = Property.MINIMAP_X_FROM; j < Property.MINIMAP_X_UNTIL; j++) {
					pixels[j+i*Property.END_OF_X] = minimap[minimapIndex];
					minimapIndex++;
				}
			}
			
			int[] log = roomPixels.get(5);
			int logIndex=0;
			for(int i = Property.LOG_Y_FROM; i < Property.LOG_Y_UNTIL; i++) {
				for(int j = Property.LOG_X_FROM; j < Property.LOG_X_UNTIL; j++) {
					pixels[j+i*Property.END_OF_X] = log[logIndex];
					logIndex++;
				}
			}
		}
		return pixels;
	}
	private int[] getChangeConfirm(int[] p) {
		InformationContainer.writeLineExt("end turn", Property.CHANGE_CONFIRM_X_FROM, Property.CHANGE_CONFIRM_X_UNTIL, Property.CHANGE_CONFIRM_Y_FROM, Property.CHANGE_CONFIRM_Y_UNTIL, 
				0, TextAlignment.CENTER, MyColor.BLACK, MyColor.WHITE, p, Property.END_OF_X);
		Event e = new Event();
		e.setEventId("CONFIRM_END");
		this.connector.addEvent(Property.CHANGE_CONFIRM_X_FROM, Property.CHANGE_CONFIRM_Y_FROM, Property.BUTTON_NORMAL_WIDTH, Property.BUTTON_NORMAL_HEIGHT, e);
		return p;
	}
	private PlayableCharacter getCharacter(String name) {
		for(PlayableCharacter p : teams.get(0).getCharacters()) {
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
	private void mockTeams() {
		Team t1 = new Team();
		t1.setTeamNr(Property.TEAM_1);
		List<PlayableCharacter> chars1 = new ArrayList<PlayableCharacter>();
		PlayableCharacter sion = CharacterLibrary.get(Resources.DARTH_SION);
		sion.setTeam(Property.TEAM_1);
		PlayableCharacter ta = CharacterLibrary.get(Resources.TALZIN);
		ta.setTeam(Property.TEAM_1);
		PlayableCharacter boba = CharacterLibrary.get(Resources.BOBA);
		boba.setTeam(Property.TEAM_1);
		chars1.add(sion);
		chars1.add(boba);
		chars1.add(ta);
		t1.setCharacters(chars1);
		
		Team t2 = new Team();
		t2.setTeamNr(Property.TEAM_2);
		List<PlayableCharacter> chars2 = new ArrayList<PlayableCharacter>();
		PlayableCharacter vader = CharacterLibrary.get(Resources.DARTH_VADER);
		vader.setTeam(Property.TEAM_2);
		PlayableCharacter luke = CharacterLibrary.get(Resources.LUKE);
		luke.setTeam(Property.TEAM_2);
		PlayableCharacter r2 = CharacterLibrary.get(Resources.R2D2);
		r2.setTeam(Property.TEAM_2);
		chars2.add(vader);
		chars2.add(luke);
		chars2.add(r2);
		t2.setCharacters(chars2);
		
		this.teams.add(t1);
		this.teams.add(t2);
		
	}
	private int getNextTeamNr() {
		if(this.activePointer<this.maxPointer) {
			this.activePointer++;
		}else {
			this.activePointer=1;
		}
		return this.activePointer;
		
	}
	private void confirmEnd() {
		this.inChangeTeam=false;
		int teamNr = getNextTeamNr();
		this.arena.openViewForTeamNr(teamNr);
	}

	@Override
	protected void mouseClicked(Event e) {
		if(e.getEventId().equals("selectPlayerEvent")) {
			activeCharacter = getCharacter(e.getObject().getName());
		}
		this.arena.mouseClicked(e);
		if(e.getEventId().equals(this.connector.END_TURN)) {
			this.inChangeTeam=true;
		}
		if(e.getEventId().equals("CONFIRM_END")) {
			confirmEnd();
		}	
//		if(e.getEventId().contains("tabChange")) {
//			if(e.getEventId().startsWith(this.activeCharacterCanvas.getPrefix())) {
//				if(this.activeCharacter!=null)
//					this.activeCharacter.setActiveTab(e.getTab());
//			}
//		}
		
	}

	@Override
	protected void keyPressed(KeyEvent e) {
		arena.keyPressed(e);
		
	}

	@Override
	protected boolean hasSprite() {
		// TODO Auto-generated method stub
		return false;
	}

}
