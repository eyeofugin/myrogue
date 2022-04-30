package rogue.game.world.objects.tiles;

import rogue.game.world.objects.entities.Entity;

public class Enhancement {
	private int id;
	protected int team;
	protected Level level;
	protected boolean visTeam;
	protected boolean visEnemy;
	protected boolean solid;
	protected boolean blockVis;
	protected int duration;
	
	
//--DEFINITIONS
	public Enhancement() {
		
	}
	public Enhancement(int id) {
		this.id = id;
	}
	public Enhancement(int id, Level level,boolean visTeam, boolean visEnemy, boolean solid, boolean blockVis, int duration) {
		this.id=id;
		this.level=level;
		this.visTeam=visTeam;
		this.visEnemy=visEnemy;
		this.solid=solid;
		this.blockVis=blockVis;
		this.duration=duration;
	}
	public static enum Level{
		TOP,
		SUB
	}
	
//--UTIL
	public void turn() {
		this.duration--;
	}
//--INTERACTION
	public void onEnter(Entity e) {
		
	}
	public void onLeave(Entity e) {
		
	}
	
	
	//Getters setters


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getTeam() {
		return team;
	}


	public void setTeam(int team) {
		this.team = team;
	}


	public Level getLevel() {
		return level;
	}


	public void setLevel(Level level) {
		this.level = level;
	}


	public boolean isVisTeam() {
		return visTeam;
	}


	public void setVisTeam(boolean visTeam) {
		this.visTeam = visTeam;
	}


	public boolean isVisEnemy() {
		return visEnemy;
	}


	public void setVisEnemy(boolean visEnemy) {
		this.visEnemy = visEnemy;
	}


	public boolean isSolid() {
		return solid;
	}


	public void setSolid(boolean solid) {
		this.solid = solid;
	}


	public boolean isBlockVis() {
		return blockVis;
	}


	public void setBlockVis(boolean blockVis) {
		this.blockVis = blockVis;
	}


	public int getDuration() {
		return duration;
	}


	public void setDuration(int duration) {
		this.duration = duration;
	}
	
}
