package rogue.game.world.objects;

import rogue.framework.eventhandling.Connector;

public abstract class SecondLayerObject extends Tile{

	private Connector connector;
	private String name;
	private byte portraitId;
	private int x;
	private int y;
	
	public SecondLayerObject() {
		super();
	}
	
	public SecondLayerObject(byte id, int x, int y, byte portraitId, String name, Connector connector) {
		super(id);
		this.x = x;
		this.y = y;
		this.portraitId = portraitId;
		this.connector = connector;
		this.name = name;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public byte getPortraitId() {
		return this.portraitId;
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name= name;
	}
}
