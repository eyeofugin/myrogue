package rogue.game.pvp;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import rogue.game.world.objects.entities.NPC;
import rogue.game.world.objects.entities.PlayableCharacter;
import util.DraftColor;

public class Team {
	
	public static int TIER_THRESHHOLD = 15;
	private boolean turn;
	
	private int points=0; 
	
	private int teamNr;
	
	private List<PlayableCharacter> characters = new ArrayList<PlayableCharacter>();
	private List<PlayableCharacter> bench =  new ArrayList<PlayableCharacter>();
	private List<NPC> mobs = new ArrayList<NPC>();

	
	public List<DraftColor> getDraftColors(){
		int redCounter=0,blueCounter=0,greenCounter=0,blackCounter=0,whiteCounter=0;
		for(PlayableCharacter pc : characters) {
			blackCounter+=pc.getColors().stream().filter(c->c.equals(DraftColor.BLACK)).collect(Collectors.toList()).size();
			blueCounter+=pc.getColors().stream().filter(c->c.equals(DraftColor.BLUE)).collect(Collectors.toList()).size();
			redCounter+=pc.getColors().stream().filter(c->c.equals(DraftColor.RED)).collect(Collectors.toList()).size();
			whiteCounter+=pc.getColors().stream().filter(c->c.equals(DraftColor.WHITE)).collect(Collectors.toList()).size();
			greenCounter+=pc.getColors().stream().filter(c->c.equals(DraftColor.GREEN)).collect(Collectors.toList()).size();
		}
		List<DraftColor> result = new ArrayList<>();
		if(redCounter>2) {result.add(DraftColor.RED);}
		if(blueCounter>2) {result.add(DraftColor.BLUE);}
		if(blackCounter>2) {result.add(DraftColor.BLACK);}
		if(whiteCounter>2) {result.add(DraftColor.WHITE);}
		if(greenCounter>2) {result.add(DraftColor.GREEN);}
		return result;
	}
	
	public boolean isFull(int newTier) {
		if(bench.size()>=getMaxBenchSize() && characters.size()>=getMaxTeamSize()) {
			return true;
		}
		if(bench.size()>=getMaxBenchSize() && getTierValue()+newTier>TIER_THRESHHOLD) {
			return true;
		}
		return false;
	}
	public int getMaxTeamSize() {
		int teamSize=5;
		if(getDraftColors().contains(DraftColor.RED)) {teamSize+=4;}
		return teamSize;
	}
	public int getMaxBenchSize() {
		int benchSize=2;
		if(getDraftColors().contains(DraftColor.GREEN)) {benchSize+=3;}
		return benchSize;
	}
	
	
	public int getTierValue() {
		int value=0;
		for(PlayableCharacter pc : characters) {
			value+=pc.getTier();
		}
		return value;
	}
	public boolean isTurn() {
		return turn;
	}

	public void setTurn(boolean turn) {
		this.turn = turn;
	}

	public List<PlayableCharacter> getCharacters() {
		return characters;
	}

	public void setCharacters(List<PlayableCharacter> characters) {
		this.characters = characters;
	}

	public List<NPC> getMobs() {
		return mobs;
	}

	public void setMobs(List<NPC> mobs) {
		this.mobs = mobs;
	}

	public int getTeamNr() {
		return teamNr;
	}

	public void setTeamNr(int teamNr) {
		this.teamNr = teamNr;
	}

	public List<PlayableCharacter> getBench() {
		return bench;
	}

	public void setBench(List<PlayableCharacter> bench) {
		this.bench = bench;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}
	public void addPoints(int points) {
		this.points+=points;
	}
	
}
