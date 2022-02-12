package rogue.game.world.objects;

public class SubEnhancement{
	
	private int id;
	private Level level;
	private boolean solid;
	private boolean visible;
	private int duration;
	
	public SubEnhancement(int id, Level level, boolean solid,
				boolean visible, int duration) {
		this.id = id;
		this.level = level;
		this.solid = solid;
		this.visible = visible;
		this.duration = duration;
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

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}
}
