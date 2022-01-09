package rogue.game.world.objects;

import java.util.Arrays;

import rogue.framework.eventhandling.Event;

public class Skill {

	private byte id;
	private String name;
	
	private int baseDamage;
	private int manaCost;
	private int distance;
	private int radius;
	private DamageType[] damageTypes;
	private TargetType targetType;
	private Event event;
	private int actionCost;
	
	public static final Skill NONE = new Skill((byte)0,"none",0,0,0,0,0,new DamageType[] {DamageType.NONE},TargetType.NONE);
	public static final Skill SLASH = new Skill((byte)1,"slash",1,1,1,2,1,new DamageType[]{DamageType.SLASHING},TargetType.LINE); 
	
	public Skill(byte id, String name,int actionCost, int baseDamage, int manaCost, int distance, int radius,
			DamageType[] damageTypes, TargetType targetType) {
		this.id = (byte)id;
		this.name = name;
		this.baseDamage = baseDamage;
		this.actionCost = actionCost;
		this.manaCost = manaCost;
		this.distance = distance;
		this.radius = radius;
		this.damageTypes = damageTypes;
		this.targetType = targetType;
		
		Event e = new Event();
		e.setEventId("castSkill");
		e.setSkill(this);
		this.event=e;
	}
	public static enum DamageType{
		PIERCING,
		SLASHING,
		BLUDGEONING,
		BEAM,
		BLEEDING,
		BURN,
		EXHAUSTION,
		FREEZING,
		PROJECTILE,
		STATIC,
		WAVES,
		NONE
	}
	public static enum TargetType{
		SINGLE_TARGET,
		AREA,
		LINE,
		CONE,
		SURROUNDING,
		NONE,
	}
	public byte getId() {
		return this.id;
	}
	public Event getEvent() {
		return event;
	}
	public String getName() {
		return name;
	}
	public int getBaseDamage() {
		return baseDamage;
	}
	public int getManaCost() {
		return manaCost;
	}
	public int getDistance() {
		return distance;
	}
	public int getRadius() {
		return radius;
	}
	public int getActionCost() {
		return this.actionCost;
	}
	public DamageType[] getDamageTypes() {
		return damageTypes;
	}
	public TargetType getTargetType() {
		return targetType;
	}
	@Override
	public String toString() {
		return "Skill [id=" + id + ", name=" + name + ", baseDamage=" + baseDamage + ", manaCost=" + manaCost
				+ ", distance=" + distance + ", radius=" + radius + ", damageTypes=" + Arrays.toString(damageTypes)
				+ ", targetType=" + targetType + ", event=" + event + "]";
	}
	
}
