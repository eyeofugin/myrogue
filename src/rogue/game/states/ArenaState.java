package rogue.game.states;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import rogue.framework.eventhandling.Connector;
import rogue.framework.eventhandling.Event;
import rogue.framework.resources.Property;
import rogue.framework.resources.Resources;
import rogue.framework.states.State;
import rogue.game.Init;
import rogue.game.combat.skills.Skill.Effect;
import rogue.game.pvp.CharacterLibrary;
import rogue.game.pvp.Team;
import rogue.game.world.Arena;
import rogue.game.world.Draft;
import rogue.game.world.HUD;
import rogue.game.world.Menu;
import rogue.game.world.objects.entities.PlayableCharacter;
import rogue.graphics.EntityInformationContainer;
import util.DraftColor;
import util.StndColumn;
import util.StndTable;
import util.TextEditor;

public class ArenaState extends State{

	private Menu menu;
	private Arena arena;
	private Draft draft;
	private HUD hud;
	
	private List<Team> teams = new ArrayList<Team>();
	private Map<Integer,List<DraftColor>> draftColors = new HashMap<>();
	private int activePointer;
	private int maxPointer;
	private int round = 0;
	private boolean inChangeTeam=false;
	private boolean inDraft = true;
	private boolean inMenu = false;
	private boolean inEnd = false;
	
	private PlayableCharacter activeCharacter;
	private EntityInformationContainer activeCharacterCanvas;
	
	
	
	
	public ArenaState(Connector connector) {
		super(connector);
//		mockTeams();
		initTeams(2);
		this.menu=new Menu(0,0,1920,1080,connector);
		this.activeCharacter = new PlayableCharacter();
		this.activeCharacterCanvas = new EntityInformationContainer(this.activeCharacter,EntityInformationContainer.PLAYER_CONFIG,TextEditor.conf8x7,connector);
//		this.arena = new Arena(Init.ROOMS[1],this.connector);
//		this.arena.initTeams(teams);
//		this.arena.openViewForTeamNr(1);
		this.activePointer=1;
		this.maxPointer=teams.size();
		this.hud=new HUD(connector);
		startDraft();
//		startArena();
	}
	private void startArena() {
		this.activePointer=0;
		this.arena = new Arena(Init.ROOMS[2],this.connector);
		//mockTeams();
		this.arena.initTeams(teams);
		nextArena();
	}
	private void nextArena() {
		getNextTeamNr();
		this.arena.openViewForTeamNr(this.activePointer);
	}
	private void endArena(int winner) {
		this.inDraft=true;
		getTeam(winner).addPoints(round);
		if(round==7) {
			showEndScreen();
		}else {
		refreshTeams();
		startDraft();			
		}
	}
	
	private void startDraft() {
		this.activePointer=1;
		this.round++;
		fillDraftColors();
		nextDraft();
	}
	private void nextDraft() {
		this.draft=new Draft(this.connector);
		this.draft.buildDraftFor(this.activePointer, this.teams,this.draftColors.get(this.activePointer),this.round);
	}	
	private void endDraft() {
		int next=  getNextTeamNr();
		if(next!=1) {
			nextDraft();
		}else {
			this.inDraft=false;
			startArena();
		}
	}
	private void showEndScreen() {
		this.inEnd=true;
		this.hud.winnderDialog(getWinner());
	}


	@Override
	protected void update() {
		if(inDraft) {
		}else {
			this.activeCharacterCanvas.checkUdate(activeCharacter);
			this.arena.update();			
		}

	}

	@Override
	protected int[] render() {
		//this.connector.cleanAll();
		
		int[] pixels = new int[1920*1080];
		pixels = backGround(pixels);
		if(inMenu) {
			
		}else if(inDraft){
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
			
			int[] teamOverView = renderTeamInfo();
			int infoCounter = 0;
			for(int i = Property.TEAMS_INFO_Y_FROM; i < Property.END_OF_Y; i++) {
				for(int j = Property.TEAMS_INFO_X_FROM; j < Property.TEAMS_INFO_X_FROM + 260; j++) {
					if(infoCounter==teamOverView.length) {
						break;
					}
					pixels[j+i*Property.END_OF_X] = teamOverView[infoCounter];
					infoCounter++;
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
	private int[] renderTeamInfo() {
		StndColumn[] columns = new StndColumn[this.teams.size()+1];
		StndColumn header = new StndColumn(new String[] {
				"Team",
				"Points"
		});
		columns[0] = header;
		int index = 1;
		for(Team t : this.teams) {
			StndColumn col = new StndColumn(new String[] {
				"Team"+t.getTeamNr(),
				""+t.getPoints()
			});
			columns[index++]=col;
		}
		
		StndTable infotable = new StndTable(columns,new TextEditor(Resources.textEditorConfig),new int[] {200,50});
		infotable.finish();
		
		return infotable.getPixels();
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
	private void refreshTeams() {
		for(Team t : this.teams) {
			for(PlayableCharacter pc: t.getCharacters()) {
				pc.setCurrentActions(pc.getMaxActions());
				pc.setCurrentEffects(new ArrayList<Effect>());
				pc.setCurrentLife(pc.getMaxLife());
				pc.setCurrentMana(pc.getMaxMana());
				pc.setCurrentMovement(pc.getMaxMovement());
			}
		}
	}
	private void fillDraftColors() {
		for(Team t : this.teams) {
			this.draftColors.put(t.getTeamNr(), getEnemyDraftColors(teams, t.getTeamNr()));
		}
	}
	private List<DraftColor> getEnemyDraftColors(List<Team> teams, int teamNr){
		List<Team> enemies =teams.stream().filter(i->i.getTeamNr()!=teamNr).collect(Collectors.toList());
		List<DraftColor> draftColors = new ArrayList<>();;
		for(Team t : enemies) {
			draftColors.addAll(t.getDraftColors());
		}
		return draftColors.stream().distinct().collect(Collectors.toList());
	}
	private void initTeams(int amnt) {
		for(int i=1;i<=amnt;i++) {
			Team team = new Team();
			team.setTeamNr(i);
			this.teams.add(team);
		}
	}
	private void mockTeams() {
		this.teams.clear();
		Team t1 = new Team();
		t1.setTeamNr(Property.TEAM_1);
		List<PlayableCharacter> chars1 = new ArrayList<PlayableCharacter>();
		PlayableCharacter char1 = CharacterLibrary.get(Resources.MOODY);
		char1.setTeam(Property.TEAM_1);
		PlayableCharacter char2 = CharacterLibrary.get(Resources.TALZIN);
		char2.setTeam(Property.TEAM_1);
		PlayableCharacter char3 = CharacterLibrary.get(Resources.OBELIX);
		char3.setTeam(Property.TEAM_1);
		PlayableCharacter char4 = CharacterLibrary.get(Resources.OOZE);
		char4.setTeam(Property.TEAM_1);
		PlayableCharacter char5 = CharacterLibrary.get(Resources.PROFESSOR);
		char5.setTeam(Property.TEAM_1);
		PlayableCharacter char6 = CharacterLibrary.get(Resources.R2D2);
		char6.setTeam(Property.TEAM_1);
		chars1.add(char1); 
		chars1.add(char2);
		chars1.add(char3);
		chars1.add(char4);
		chars1.add(char5);
		chars1.add(char6);
		t1.setCharacters(chars1);
		
		Team t2 = new Team();
		t2.setTeamNr(Property.TEAM_2);
		List<PlayableCharacter> chars2 = new ArrayList<PlayableCharacter>();
		PlayableCharacter char7 = CharacterLibrary.get(Resources.RADAGAST);
		char7.setTeam(Property.TEAM_2);
		PlayableCharacter char8 = CharacterLibrary.get(Resources.REBEL);
		char8.setTeam(Property.TEAM_2);
		PlayableCharacter char9 = CharacterLibrary.get(Resources.RED_MAGE);
		char9.setTeam(Property.TEAM_2);
		PlayableCharacter char10 = CharacterLibrary.get(Resources.ROGUE);
		char10.setTeam(Property.TEAM_2);
		PlayableCharacter char11 = CharacterLibrary.get(Resources.SAMWISE);
		char11.setTeam(Property.TEAM_2);
		PlayableCharacter char12 = CharacterLibrary.get(Resources.SERPINE);
		char12.setTeam(Property.TEAM_2);
		chars2.add(char7);
		chars2.add(char8);
		chars2.add(char9);
		chars2.add(char10);
		chars2.add(char11);
		chars2.add(char12);
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
	private void confirmationDialog(Event confirm) {
		this.hud.confirmationDialog(confirm);
	}
	private int getWinner() {
		int points = 0;
		int team = 0;
		for(Team t : this.teams) {
			if(t.getPoints()>points) {
				points = t.getPoints();
				team = t.getTeamNr();
			}
		}
		return team;
	}
	private Team getTeam(int teamNr) {
		for(Team t : this.teams) {
			if(t.getTeamNr()==teamNr) {
				return t;
			}
		}
		return new Team();
	}

	@Override
	protected void mouseClicked(Event e) {
		if(e.getEventId().equals(Connector.REQUEST_CONFIRMATION)) {
			confirmationDialog(e.getAfterConfirmEvent());
		}
		if(this.hud.active) {
			this.hud.mouseClicked(e);
		}
		if(e.getEventId().equals(Connector.TEAM_WON_ROUND)) {
			endArena(e.getTeamNr());
		}
		if(e.getEventId().equals("selectPlayerEvent")) {
			activeCharacter = getCharacter(e.getEntity().getName());
		}
		if(e.getEventId().equals(Connector.END_DRAFT)) {
			endDraft();
		}
		if(this.inDraft) {
			this.draft.mouseClicked(e);
		}else {
			this.arena.mouseClicked(e);
		}
		
		if(e.getEventId().equals(this.connector.END_TURN)) {
			nextArena();
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
