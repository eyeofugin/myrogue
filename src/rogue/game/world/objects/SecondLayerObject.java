package rogue.game.world.objects;

import rogue.framework.eventhandling.Connector;
import util.MovementOption;

public abstract class SecondLayerObject extends Tile{

	private Connector connector;
	private String name = "dummy";
	private int team;
	private int portraitId = 0;
	private int x = -1;
	private int y = -1;
	private Integer appearance = null;
	

	public SecondLayerObject() {
		super();
	}
	
	public SecondLayerObject(int id, int x, int y, int portraitId, String name, int team, Connector connector) {
		super(id);
		this.x = x;
		this.y = y;
		this.portraitId = portraitId;
		this.connector = connector;
		this.name = name;
		this.team = team;
	}
	public void setId(int id) {
		this.tileID=id;
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

	public int getTeam() {
		return team;
	}

	public void setTeam(int team) {
		this.team = team;
	}

	public Integer getAppearance() {
		return appearance;
	}

	public void setAppearance(Integer appearance) {
		this.appearance = appearance;
	}
}
