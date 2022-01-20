package rogue.game.combat.skills;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import rogue.framework.eventhandling.Connector;
import rogue.framework.eventhandling.Event;

public class Skill {

	public static final byte NONE = 0;
	public static final byte SLASH =1;
	
	public static final ArrayList<Skill> SKILLS = new ArrayList<>();
	
	private byte id;
	private String name;
	private String description;
	
	
	private int baseDamage;
	private List<Multiplier> multipliers;
	private int manaCost;
	private int distance;
	private int radius;
	@SerializedName("damageType")
	private DamageType damageType;
	@SerializedName("targetType")
	private TargetType targetType;
	private Event event;
	private int actionCost;
	
//	public static Skill NONE = new Skill((byte)0,"none",0,0,0,0,0,new DamageType[] {DamageType.NONE},TargetType.NONE);
//	public static Skill SLASH = new Skill((byte)1,"slash",1,1,1,2,1,new DamageType[]{DamageType.SLASHING},TargetType.LINE); 
	
	public Skill(byte id, String name,int actionCost, int baseDamage, int manaCost, int distance, int radius,
			DamageType damageType, TargetType targetType) {
		this.id = (byte)id;
		this.name = name;
		this.baseDamage = baseDamage;
		this.actionCost = actionCost;
		this.manaCost = manaCost;
		this.distance = distance;
		this.radius = radius;
		this.damageType = damageType;
		this.targetType = targetType;
		
		Event e = new Event();
		e.setEventId(Connector.SKILL_CHOSEN);
		e.setSkill(id);
		this.event=e;
	}
	public static Skill skillSetup(String path) {
		Skill s = null;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(path));
			String json = "";
			
			StringBuilder sb = new StringBuilder();
			String line = reader.readLine();
			while(line!=null) {
				sb.append(line);
		        sb.append("\n");
		        line = reader.readLine();
			}
			json = sb.toString();
			
			Gson gson = new Gson();
			System.out.println(json);
			s = gson.fromJson(json, Skill.class);
			
			reader.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return s;
	}
	public static enum DamageType{
		@SerializedName("0")
		PIERCING,
		@SerializedName("1")
		SLASHING,
		@SerializedName("2")
		BLUDGEONING,
		@SerializedName("3")
		BURNING,
		@SerializedName("4")
		FREEZING,
		@SerializedName("5")
		LIGHT,
		@SerializedName("6")
		DARK,
		@SerializedName("7")
		PSYCHIC,
		@SerializedName("8")
		SHOCK,
		@SerializedName("9")
		NONE
	}
	public static enum TargetType{
		@SerializedName("0")
		SINGLE_TARGET,
		@SerializedName("1")
		AREA,
		@SerializedName("2")
		LINE,
		@SerializedName("3")
		CONE,
		@SerializedName("4")
		SURROUNDING,
		@SerializedName("5")
		SELF,
		@SerializedName("6")
		NONE,
	}
	public static class Multiplier{
		DamageType type;
		double multiplier;
		public DamageType getType() {
			return type;
		}
		public void setType(DamageType type) {
			this.type = type;
		}
		public double getMultiplier() {
			return multiplier;
		}
		public void setMultiplier(int multiplier) {
			this.multiplier = multiplier;
		}
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
	public void setBaseDamage(int i) {
		this.baseDamage = i;
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
	public DamageType getDamageType() {
		return damageType;
	}
	public TargetType getTargetType() {
		return targetType;
	}
	@Override
	public String toString() {
		return "Skill [id=" + id + ", name=" + name + ", baseDamage=" + baseDamage + ", manaCost=" + manaCost
				+ ", distance=" + distance + ", radius=" + radius + ", damageType=" + damageType
				+ ", targetType=" + targetType + ", event=" + event + "]";
	}
	public static Skill getSkill(byte id) {
		return SKILLS.get(id);
	}
	
}
