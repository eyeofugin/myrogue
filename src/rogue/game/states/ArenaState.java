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
import rogue.game.world.Draft;
import rogue.game.world.HUD;
import rogue.game.world.Menu;
import rogue.game.world.objects.entities.PlayableCharacter;
import rogue.graphics.EntityInformationContainer;
import rogue.graphics.InformationContainer;
import util.MyColor;
import util.TextAlignment;
import util.TextEditor;

public class ArenaState extends State{

	private Menu menu;
	private Arena arena;
	private Draft draft;
	private HUD hud;
	
	private List<Team> teams = new ArrayList<Team>();
	private int activePointer;
	private int maxPointer;
	private int round = 1;
	private boolean inChangeTeam=false;
	private boolean inDraft = false;
	private boolean inMenu = false;
	
	private PlayableCharacter activeCharacter;
	private EntityInformationContainer activeCharacterCanvas;
	
	
	
	
	public ArenaState(Connector connector) {
		super(connector);
		mockTeams();
		this.menu=new Menu(0,0,1920,1080,connector);
		this.activeCharacter = teams.get(0).getCharacters().get(0);
		this.activeCharacterCanvas = new EntityInformationContainer(this.activeCharacter,EntityInformationContainer.PLAYER_CONFIG,TextEditor.conf8x7,connector);
		this.arena = new Arena(Init.ROOMS[1],this.connector);
		this.arena.initTeams(teams);
		this.arena.openViewForTeamNr(1);
		this.draft=new Draft(this.connector);
		this.activePointer=1;
		this.maxPointer=teams.size();
		this.hud=new HUD(connector);
	}

	@Override
	protected void update() {
		this.activeCharacterCanvas.checkUdate(activeCharacter);
		this.arena.update();
	}

	@Override
	protected int[] render() {
		//this.connector.cleanAll();
		
		int[] pixels = new int[1920*1080];
		pixels = backGround(pixels);
		if(inMenu) {
			
		}if(inDraft){
			List<int[]>  draftPixels = this.draft.render();
			
			int[] options = draftPixels.get(0);
			int indexOptions = 0;
			for(int i = Property.START_OF_ROOM_Y; i < Property.END_OF_ROOM_Y; i++) {
				for(int j = Property.START_OF_ROOM_X; j < Property.END_OF_ROOM_X; j++) {
					pixels[j+i*Property.END_OF_X] = options[indexOptions];
					indexOptions++;
				}
			}
			int[] team = draftPixels.get(1);
			int indexTeam = 0;
			for(int i = Property.START_OF_ACTIVE_CHAR_Y; i < Property.END_OF_Y; i++) {
				for(int j = Property.START_OF_ACTIVE_CHAR_X; j < Property.END_OF_X; j++) {
					pixels[j+i*Property.END_OF_X] = team[indexTeam];
					indexTeam++;
				}
			}
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
			
			int[] log = roomPixels.get(5);
			int logIndex=0;
			for(int i = Property.LOG_Y_FROM; i < Property.LOG_Y_UNTIL; i++) {
				for(int j = Property.LOG_X_FROM; j < Property.LOG_X_UNTIL; j++) {
					pixels[j+i*Property.END_OF_X] = log[logIndex];
					logIndex++;
				}
			}
		}
		
		if(this.hud.active){
//			this.connector.cleanAll();
			int[] hudP = this.hud.render();
			
			int index = 0;
			for(int i = 0; i <1080; i++) {
				for(int j = 0; j <1920; j++) {
				
					if(hudP[index]!=-12450784) {
						pixels[j + i * 1920] = hudP[index];
					}
					index++;
				}
			}
		}
		
		return pixels;
	}
//	private int[] getChangeConfirm(int[] p) {
//		InformationContainer.writeLineExt("end turn", Property.CHANGE_CONFIRM_X_FROM, Property.CHANGE_CONFIRM_X_UNTIL, Property.CHANGE_CONFIRM_Y_FROM, Property.CHANGE_CONFIRM_Y_UNTIL, 
//				0, TextAlignment.CENTER, MyColor.BLACK, MyColor.WHITE, p, Property.END_OF_X);
//		Event e = new Event();
//		e.setEventId("CONFIRM_END");
//		this.connector.addEvent(Property.CHANGE_CONFIRM_X_FROM, Property.CHANGE_CONFIRM_Y_FROM, Property.BUTTON_NORMAL_WIDTH, Property.BUTTON_NORMAL_HEIGHT, e);
//		return p;
//	}
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
		PlayableCharacter char1 = CharacterLibrary.get(Resources.DUMBLEDORE);
		char1.setTeam(Property.TEAM_1);
		PlayableCharacter char2 = CharacterLibrary.get(Resources.TALZIN);
		char2.setTeam(Property.TEAM_1);
		PlayableCharacter char3 = CharacterLibrary.get(Resources.TALZIN);
		char3.setTeam(Property.TEAM_1);
		PlayableCharacter char4 = CharacterLibrary.get(Resources.UMBRIDGE);
		char4.setTeam(Property.TEAM_1);
		PlayableCharacter char5 = CharacterLibrary.get(Resources.VOLDEMORT);
		char5.setTeam(Property.TEAM_1);
		PlayableCharacter char6 = CharacterLibrary.get(Resources.HAGRID);
		char6.setTeam(Property.TEAM_1);
		chars1.add(char1); 
		chars1.add(char2);
		chars1.add(char3);
//		chars1.add(char3);
//		chars1.add(char4);
//		chars1.add(char5);
//		chars1.add(char6);
		t1.setCharacters(chars1);
		
		Team t2 = new Team();
		t2.setTeamNr(Property.TEAM_2);
		List<PlayableCharacter> chars2 = new ArrayList<PlayableCharacter>();
		PlayableCharacter char7 = CharacterLibrary.get(Resources.LEGOLAS);
		char7.setTeam(Property.TEAM_2);
		PlayableCharacter char8 = CharacterLibrary.get(Resources.GIMLI);
		char8.setTeam(Property.TEAM_2);
		PlayableCharacter char9 = CharacterLibrary.get(Resources.BAUMBART);
		char9.setTeam(Property.TEAM_2);
		PlayableCharacter char10 = CharacterLibrary.get(Resources.RADAGAST);
		char10.setTeam(Property.TEAM_2);
		PlayableCharacter char11 = CharacterLibrary.get(Resources.SAMWISE);
		char11.setTeam(Property.TEAM_2);
		PlayableCharacter char12 = CharacterLibrary.get(Resources.BALROG);
		char12.setTeam(Property.TEAM_2);
		chars2.add(char7);
//		chars2.add(char7);
//		chars2.add(char7);
//		chars2.add(char7);
//		chars2.add(char7);
		chars2.add(char8);
		chars2.add(char9);
//		chars2.add(char9);
//		chars2.add(char10);
//		chars2.add(char11);
//		chars2.add(char12);
		t2.setCharacters(chars2);
		List<PlayableCharacter> passive2 = new ArrayList<>();
		PlayableCharacter char13 = CharacterLibrary.get(Resources.TALZIN);
		char13.setTeam(Property.TEAM_2);
		passive2.add(char13);
		t2.setBench(passive2);
		
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
	private void confirmationDialog(Event confirm) {
		this.hud.confirmationDialog(confirm);
	}
	private void confirmEnd() {
		int teamNr = getNextTeamNr();
//		this.inDraft = true;
//		this.draft.buildDraftFor(teamNr, this.teams,this.round);
		this.arena.openViewForTeamNr(teamNr);
	}

	@Override
	protected void mouseClicked(Event e) {
		if(e.getEventId().equals(Connector.REQUEST_CONFIRMATION)) {
			confirmationDialog(e.getAfterConfirmEvent());
		}
		if(this.hud.active) {
			this.hud.mouseClicked(e);
		}
		if(e.getEventId().equals("selectPlayerEvent")) {
			activeCharacter = getCharacter(e.getEntity().getName());
		}
		if(this.inDraft) {
			this.draft.mouseClicked(e);
		}else {
			this.arena.mouseClicked(e);
		}
		
		if(e.getEventId().equals(this.connector.END_TURN)) {
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
