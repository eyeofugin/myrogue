package rogue.game.world.objects;

public class Tile {

	private byte tileID;
	
	public Tile() {
		
	}
	public Tile(byte id) {
		this.tileID = id;
	}
	
	public byte getId() {
		return this.tileID;
	}
}
