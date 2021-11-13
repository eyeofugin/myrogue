package rogue.game.world.generation;

import rogue.game.world.objects.Tile;

public class RoomData {

	private Tile[][] tileData;
	private int size;
	
	public RoomData(byte[][]tileData) {
		this.size = tileData.length;
		this.tileData = new Tile[tileData.length][tileData[0].length];
		for(int i = 0; i < this.tileData.length; i++) {
			for(int j = 0; j < this.tileData[i].length; j++) {
				this.tileData[i][j] = new Tile(tileData[i][j]);
			}
		}
	}
	public Tile[][] getTileData(){
		return this.tileData;
	}
	public int size() {
		return size;
	}
}
