package rogue.game.world.objects;

import java.util.HashMap;
import java.util.Map;

import rogue.game.combat.skills.Skill.DamageType;
import rogue.game.world.objects.entities.Entity.Proficiency;

public class Equipment {

	private int meleeDamageBonus = 0;
	private int meleeDefenseBonus = 0;
	private Map<Proficiency,Integer> pBonus = new HashMap<Proficiency,Integer>();
	private Map<DamageType,Integer> rBonus= new HashMap<DamageType,Integer>();
	private Map<DamageType,Double> mBonus= new HashMap<DamageType,Double>();
	
	public Equipment(int meleeDamageBonus, int meleeDefenseBonus) {
		this.meleeDamageBonus = meleeDamageBonus;
		this.meleeDefenseBonus = meleeDefenseBonus;
	}
	public int getProfBonus(Proficiency p) {
		if(this.pBonus.get(p)!=null) {
			return this.pBonus.get(p);
		}
		return 0;
	}
	public int getResistBonus(DamageType s) {
		if(this.rBonus.get(s)!=null) {
			return this.rBonus.get(s);
		}
		return 0;
	}
	public double getMultBonus(DamageType s) {
		if(this.mBonus.get(s)!=null) {
			return this.mBonus.get(s);
		}
		return 0.0;
	}
}
