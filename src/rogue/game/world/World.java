package rogue.game.world;

import java.util.List;

import rogue.framework.eventhandling.Connector;
import rogue.game.Init;
import rogue.game.world.objects.entities.PlayableCharacter;

public class World {

	private Room[][]rooms;
	private Connector connector;
	
	public World(PlayableCharacter player, Connector connector) {
		this.connector = connector;
		this.rooms = new Room[1][1];
		this.rooms[0][0] = new Room(Init.ROOMS[0],this.connector);
		this.rooms[0][0].initiallyPlacePlayer(player);
	}
	public World(List<PlayableCharacter> team, Connector connector) {
		this.connector = connector;
		this.rooms = new Room[1][1];
		this.rooms[0][0] = new Room(Init.ROOMS[0],this.connector);
		this.rooms[0][0].initiallyPlacePlayer(team);
	}
	
	
	public Room getRoom() {
		return rooms[0][0];
	}
}
