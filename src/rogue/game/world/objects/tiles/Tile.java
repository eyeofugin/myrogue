package rogue.game.world.objects.tiles;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import rogue.framework.resources.Resources;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.world.objects.ObjectLibrary;
import rogue.game.world.objects.entities.Entity;
import rogue.game.world.objects.tiles.Enhancement.Level;

public class Tile {
	
	private int id;
	private boolean visBlock;
	
	private List<Enhancement> enhancements=new ArrayList<Enhancement>();
	
	public Tile(int id) {
		this.id=id;
		if(id==Resources.TREE) {
			enhancements.add(ObjectLibrary.getEnhancement(id));
			this.id=Resources.MEADOW;
		}
		if(id==Resources.TALLGRASS ) {
			enhancements.add(ObjectLibrary.getEnhancement(id));	
			this.id=Resources.MEADOW;			
		}
		if(id==Resources.WATER) {
			enhancements.add(ObjectLibrary.getEnhancement(id));
			this.id=Resources.MEADOW;
		}
	}
	
	public void turn() {
		enhancements.removeIf(e->e.getDuration()==0);
		for(Enhancement e : enhancements) {
			e.turn();
		}
		enhancements.removeIf(e->e.getDuration()==0);
	}
	
	public void onEnter(Entity e) {
		for(Enhancement enh : this.enhancements) {
			enh.onEnter(e);
		}
		if(e.hasAbility(SkillLibrary.FROSTWALK)) {
			this.enhancements.add(ObjectLibrary.getEnhancement(Resources.FROSTED));
		}
	}
	public void onEnd(Entity e) {
	}
	public void onLeave(Entity e) {
		for(Enhancement enh : this.enhancements) {
			enh.onLeave(e);
		}
	}

	public boolean isVisBlock() {
		return this.visBlock || 
				getEnhancementVisBlock();
	}
	public boolean hasTop() {
		for(Enhancement enh : this.enhancements) {
			if(enh.getLevel().equals(Level.TOP)) {
				return true;
			}
		}
		return false;
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
		return getEnhancementObstacle()||this.id == Resources.WALL;
	}
	public boolean hasEnhancement(int id) {
		for(Enhancement enh : this.enhancements) {
			if(enh.getId() == id) {
				return true;
			}
		}
		return false;
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
