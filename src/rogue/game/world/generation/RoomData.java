package rogue.game.world.generation;

import java.awt.Point;


import rogue.game.world.objects.tiles.Tile;

public class RoomData {

	private Tile[][] tileData;
	private Point[] startingPoints;
	private int size;
	
	public RoomData(int[][]tileData, Point[] startingPoints) {
		this.size = tileData.length;
		this.tileData = new Tile[tileData.length][tileData[0].length];
		for(int i = 0; i < this.tileData.length; i++) {
			for(int j = 0; j < this.tileData[i].length; j++) {
				this.tileData[i][j] = new Tile(tileData[i][j]);
				
			}
		}
		this.startingPoints=startingPoints;
	}
	public Tile[][] getTileData(){
		return this.tileData;
	}
	public int size() {
		return size;
	}
	public Point[] getTeamPlacement(int teamNr) {
		int start = (teamNr-1)*6;
		int end = start+5;
		Point[] result = sub(startingPoints,start,end);
		return result;
	}
	private Point[] sub(Point[] points, int start, int end) {
		Point[] result = new Point[(end-start)+1];
		int index = 0;
		for(int i = start; i <= end; i++) {
			result[index++] = points[i];
		}
		return result;
	}
}
