package rogue.game.world.objects;

import rogue.framework.eventhandling.Connector;

public class Enhancement extends SecondLayerObject {
	
	private boolean solid;
	private boolean visible;
	private int duration;
	public Enhancement() {
		
	};

	public Enhancement(int id, int x, int y, int portrait, String name, boolean solid, boolean visible,int duration) {
		super(id,x,y,portrait,name,0,null);
		this.solid=solid;
		this.visible=visible;
		this.duration=duration;
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
