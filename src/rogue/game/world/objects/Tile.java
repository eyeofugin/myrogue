package rogue.game.world.objects;

public class Tile {

	private int tileID;
	
	public Tile() {
		
	}
	public Tile(int id) {
		this.tileID = id;
	}
	
	public int getId() {
		return this.tileID;
	}
}
