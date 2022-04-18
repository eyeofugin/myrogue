package rogue.game.world.objects.tiles;

public class Enhancement {
	private int id;
	private int team;
	private Level level;
	private boolean visTeam;
	private boolean visEnemy;
	private boolean solid;
	private boolean blockVis;
	private int duration;
	
	public Enhancement() {
		
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
	public void turn() {
		this.duration--;
	}


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
