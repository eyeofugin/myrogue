package rogue.game.combat.skills;

import java.util.*;

public class CombinedSkill extends Skill{

	private List<Skill> skills;
	
	public CombinedSkill(byte id, String name, String description) {
		super(id,name,description);
	}
	
	public void addSkill(Skill skill) {
		this.skills.add(skill);
	}
}
