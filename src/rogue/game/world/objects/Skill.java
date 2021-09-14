package rogue.game.world.objects;

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
	
	public static final Skill NONE = new Skill((byte)0,"none",0,0,0,0,new DamageType[] {DamageType.NONE},TargetType.NONE);
	public static final Skill SLASH = new Skill((byte)1,"slash",1,1,1,0,new DamageType[]{DamageType.SLASHING},TargetType.SINGLE_TARGET); 
	
	public Skill(byte id, String name, int baseDamage, int manaCost, int distance, int radius,
			DamageType[] damageTypes, TargetType targetType) {
		this.id = (byte)id;
		this.name = name;
		this.baseDamage = baseDamage;
		this.manaCost = manaCost;
		this.distance = distance;
		this.radius = radius;
		this.damageTypes = damageTypes;
		this.targetType = targetType;
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
}
