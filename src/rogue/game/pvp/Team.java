package rogue.game.pvp;

import java.util.ArrayList;
import java.util.List;

import rogue.game.world.objects.entities.NPC;
import rogue.game.world.objects.entities.PlayableCharacter;

public class Team {
	
	private boolean turn;
	
	private int teamNr;
	
	private List<PlayableCharacter> characters = new ArrayList<PlayableCharacter>();
	private List<PlayableCharacter> bench =  new ArrayList<PlayableCharacter>();
	private List<NPC> mobs = new ArrayList<NPC>();

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
	
}
