package rogue.game.world.objects.tiles;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import rogue.game.world.objects.entities.Entity;

public class Tile {
	
	private int id;
	private boolean visBlock;
	
	private List<Enhancement> enhancements=new ArrayList<Enhancement>();
	
	public Tile(int id) {
		this.id=id;
	}
	
	public void turn() {
		for(Enhancement e : enhancements) {
			e.turn();
		}
		enhancements.removeIf(e->e.getDuration()==0);
	}
	
	public void onEnter(Entity e) {
		
	}
	public void onEnd(Entity e) {
		
	}
	public void onLeave(Entity e) {
		
	}

	public boolean isVisBlock() {
		return this.visBlock || 
				getEnhancementVisBlock();
	}
	private boolean getEnhancementVisBlock() {
		return this.enhancements.stream()
				.filter(e->e.isBlockVis())
				.collect(Collectors.toList()).size()>0;
	}
	private boolean getEnhancementObstacle() {
		return this.enhancements.stream()
				.filter(e->e.isSolid())
				.collect(Collectors.toList()).size()>0;
	}
	public void addEnhancement(Enhancement enh) {
		this.enhancements.add(enh);
	}
	public boolean isObstacle() {
		return getEnhancementObstacle();
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Enhancement> getEnhancements() {
		return enhancements;
	}

	public void setEnhancements(List<Enhancement> enhancements) {
		this.enhancements = enhancements;
	}
}
