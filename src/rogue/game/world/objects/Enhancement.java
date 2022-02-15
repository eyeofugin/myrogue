package rogue.game.world.objects;

import java.util.ArrayList;
import java.util.List;

import rogue.game.world.objects.SubEnhancement.Level;

public class Enhancement extends SecondLayerObject {
	
	private List<SubEnhancement> subenhancements = new ArrayList<>();
	
	public Enhancement() {
		
	};

	public Enhancement(int id, int x, int y) {
		super(id,x,y,0,"",0,null);
	}
	public List<SubEnhancement> getSubs(){
		return this.subenhancements;
	}
	public void addSub(SubEnhancement sub) {
		this.subenhancements.add(sub);
	}
	public boolean isVisible() {
		for(SubEnhancement sub : this.subenhancements) {
			if(sub.isBlockVisibility())
				return true;
		}
		return false;
	}
	public boolean hasTop() {
		for(SubEnhancement sub : this.subenhancements) {
			if(sub.getLevel().equals(Level.TOP))
				return true;
		}
		return false;
	}
}
