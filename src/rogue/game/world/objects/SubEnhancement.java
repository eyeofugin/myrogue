package rogue.game.world.objects;

public class SubEnhancement{
	
	private int id;
	private Level level;
	private int team;
	private boolean showTeam;
	private boolean showEnemy;
	private boolean solid;
	private boolean blockVisibility;
	private int duration;
	
	public SubEnhancement(int id, Level level, boolean solid,
				boolean blockVisibility, int duration, boolean showTeam, boolean showEnemy) {
		this.id = id;
		this.level = level;
		this.solid = solid;
		this.blockVisibility = blockVisibility;
		this.duration = duration;
		this.showEnemy=showEnemy;
		this.showTeam=showTeam;
	}
	public SubEnhancement() {
		
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
	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public boolean isSolid() {
		return solid;
	}

	public void setSolid(boolean solid) {
		this.solid = solid;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}
	public boolean isShowTeam() {
		return showTeam;
	}
	public void setShowTeam(boolean showTeam) {
		this.showTeam = showTeam;
	}
	public boolean isShowEnemy() {
		return showEnemy;
	}
	public void setShowEnemy(boolean showEnemy) {
		this.showEnemy = showEnemy;
	}
	public boolean isBlockVisibility() {
		return blockVisibility;
	}
	public void setBlockVisibility(boolean blockVisibility) {
		this.blockVisibility = blockVisibility;
	}
	public int getTeam() {
		return team;
	}
	public void setTeam(int team) {
		this.team = team;
	}
}
