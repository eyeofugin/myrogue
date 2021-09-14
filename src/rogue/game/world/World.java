package rogue.game.world;

import rogue.framework.eventhandling.Connector;
import rogue.game.Init;
import rogue.game.world.objects.PlayableCharacter;

public class World {

	private Room[][]rooms;
	private Connector connector;
	
	public World(PlayableCharacter player, Connector connector) {
		this.connector = connector;
		this.rooms = new Room[1][1];
		this.rooms[0][0] = new Room(Init.ROOMS[0],this.connector);
		this.rooms[0][0].initiallyPlacePlayer(player);
	}
	
	public Room getRoom() {
		return rooms[0][0];
	}
}
