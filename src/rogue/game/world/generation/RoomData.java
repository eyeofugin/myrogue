package rogue.game.world.generation;

import rogue.game.world.objects.Enhancement;
import rogue.game.world.objects.ObjectLibrary;
import rogue.game.world.objects.Tile;

public class RoomData {

	private Tile[][] tileData;
	private Enhancement[][] enhancements;
	private int size;
	
	public RoomData(byte[][]tileData) {
		this.size = tileData.length;
		this.tileData = new Tile[tileData.length][tileData[0].length];
		this.enhancements= new Enhancement[tileData.length][tileData[0].length];
		for(int i = 0; i < this.tileData.length; i++) {
			for(int j = 0; j < this.tileData[i].length; j++) {
				this.tileData[i][j] = new Tile(tileData[i][j]);
				Enhancement e = new Enhancement();
				if(ObjectLibrary.getEnhancement(tileData[i][j])!=null) {
					e.addSub(ObjectLibrary.getEnhancement(tileData[i][j]));
				}
				if(e!=null) {
					e.setX(j);e.setY(i);
					this.enhancements[i][j] = e;
				}
				
			}
		}
	}
	public Tile[][] getTileData(){
		return this.tileData;
	}
	public Enhancement[][] getEnhancements(){
		return this.enhancements;
	}
	public int size() {
		return size;
	}
}
