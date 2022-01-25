package rogue.game.world.objects;

import rogue.framework.eventhandling.Connector;
import util.MovementOption;

public abstract class SecondLayerObject extends Tile{

	private Connector connector;
	private String name = "dummy";
	private MovementOption movement;
	private int portraitId = 0;
	private int x = -1;
	private int y = -1;
	
	public SecondLayerObject() {
		super();
	}
	
	public SecondLayerObject(int id, int x, int y, int portraitId, String name, MovementOption movement, Connector connector) {
		super(id);
		this.x = x;
		this.y = y;
		this.portraitId = portraitId;
		this.connector = connector;
		this.name = name;
		this.movement = movement;
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
	public int getPortraitId() {
		return this.portraitId;
	}
	public void setPortraitId(int b) {
		this.portraitId = b;
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name= name;
	}
	public MovementOption getMovement() {
		return movement;
	}
	public void setMovement(MovementOption movement) {
		this.movement = movement;
	}

	@Override
	public String toString() {
		return "SecondLayerObject [connector=" + connector + ", name=" + name + ", movement=" + movement
				+ ", portraitId=" + portraitId + ", x=" + x + ", y=" + y + "]";
	}
	
	
}
