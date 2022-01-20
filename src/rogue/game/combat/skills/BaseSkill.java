package rogue.game.combat.skills;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import rogue.framework.eventhandling.Connector;
import rogue.framework.eventhandling.Event;
import rogue.game.world.objects.Entity.Proficiency;

public class BaseSkill {
	public static final byte NONE =0;
	public static final byte SLASH=1;
	public static final byte HATEFUL_SWING=2;
	
	public static final List<BaseSkill> SKILLS = new ArrayList<>();
	
	protected byte id;
	protected String name;
	protected String description;
	
//	@SerializedName("targetType")
	protected TargetType targetType;
//	@SerializedName("damageType")
	protected DamageType damageType;
	protected int radius;
//	@SerializedName("effects")
	protected List<Effect> effects;
	private Event event;
//	@SerializedName("multipliers")
	protected List<Multiplier> multipliers;
	
	
	public BaseSkill(byte id, String name, String description,DamageType damage, TargetType targetType, int radius,
			List<Effect> effects, List<Multiplier> multipliers) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.damageType=damage;
		this.targetType = targetType;
		this.radius = radius;
		this.effects = effects;
		this.multipliers = multipliers;
		Event e = new Event();
		e.setEventId(Connector.SKILL_CHOSEN);
		e.setSkill(id);
		this.event=e;
	}
	public static ActiveSkill activeSkillSetup(String path) {
		ActiveSkill s = null;
		String json = getJsonOf(path);
		Gson gson = new Gson();
		s = gson.fromJson(json, ActiveSkill.class);
		return s;
	}
	public static PassiveSkill passiveSkillSetup(String path) {
		PassiveSkill s = null;
		String json = getJsonOf(path);
		Gson gson = new Gson();
		s = gson.fromJson(json, PassiveSkill.class);
		return s;
	}
	private static String getJsonOf(String path) {
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
			System.out.println(json);
			reader.close();
			return json;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "";
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
	public static class Effect{
		private EffectType type;
		private int turns;
		private int intensity; //for status inflictions
		private StatusInfliction status;
		private StatChange statChange;//for stat changes
		
		public EffectType getType() {
			return type;
		}
		public void setType(EffectType type) {
			this.type = type;
		}
		public int getTurns() {
			return turns;
		}
		public void setTurns(int turns) {
			this.turns = turns;
		}
		public int getIntensity() {
			return intensity;
		}
		public void setIntensity(int intensity) {
			this.intensity = intensity;
		}
		public StatusInfliction getStatus() {
			return status;
		}
		public void setStatus(StatusInfliction status) {
			this.status = status;
		}
		public StatChange getStatChange() {
			return statChange;
		}
		public void setStatChange(StatChange statChange) {
			this.statChange = statChange;
		}
		public static enum EffectType{
			@SerializedName("0")
			STATUS_INFLICTION,
			@SerializedName("1")
			STAT_CHANGE,
			@SerializedName("2")
			TERRAIN_ENHANCEMENT,
			@SerializedName("3")
			NONE
		}
		public static enum StatusInfliction{
			@SerializedName("0")
			BLEEDING,
			@SerializedName("1")
			STUNNED,
			@SerializedName("2")
			PARALYSED,
			@SerializedName("3")
			BURNING,
			@SerializedName("4")
			FROZEN,
			@SerializedName("5")
			BLESSED,
			@SerializedName("6")
			CURSED,
			@SerializedName("7")
			CONFUSED,
			@SerializedName("8")
			ROOTED
		}
		public static class StatChange{
			private DamageType stat;
			private double multiplier;
			private Proficiency prof;
			private int amnt;
			public DamageType getStat() {
				return stat;
			}
			public void setStat(DamageType stat) {
				this.stat = stat;
			}
			public double getMultiplier() {
				return multiplier;
			}
			public void setMultiplier(double multiplier) {
				this.multiplier = multiplier;
			}
			public Proficiency getProf() {
				return prof;
			}
			public void setProf(Proficiency prof) {
				this.prof = prof;
			}
			public int getAmnt() {
				return amnt;
			}
			public void setAmnt(int amnt) {
				this.amnt = amnt;
			}
		}
	}
	public static class Multiplier{
		private Proficiency prof;
		private double percentage;
		public Proficiency getProf() {
			return prof;
		}
		public void setProf(Proficiency prof) {
			this.prof = prof;
		}
		public double getPercentage() {
			return percentage;
		}
		public void setPercentage(double percentage) {
			this.percentage = percentage;
		}
	}
	//----------------------------------------------------------//
	//------getters-and-setters---------------------------------//
	//----------------------------------------------------------//
	public static BaseSkill getSkill(byte id) {
		return SKILLS.get(id);
	}
	public static ActiveSkill getActiveSkill(byte id) {
		if(ActiveSkill.class.isInstance(SKILLS.get(id))) {
			return ActiveSkill.class.cast(SKILLS.get(id));
		}
		return null;
	}
	public static PassiveSkill getPassiveSkill(byte id) {
		if(PassiveSkill.class.isInstance(SKILLS.get(id))) {
			return PassiveSkill.class.cast(SKILLS.get(id));
		}
		return null;
	}
	public byte getId() {
		return id;
	}
	public void setId(byte id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public TargetType getTargetType() {
		return targetType;
	}
	public void setTargetType(TargetType targetType) {
		this.targetType = targetType;
	}
	public DamageType getDamageType() {
		return damageType;
	}
	public void setDamageType(DamageType damageType) {
		this.damageType = damageType;
	}
	public int getRadius() {
		return radius;
	}
	public void setRadius(int radius) {
		this.radius = radius;
	}
	public List<Effect> getEffects() {
		return effects;
	}
	public void setEffects(List<Effect> effects) {
		this.effects = effects;
	}
	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}
	public List<Multiplier> getMultipliers() {
		return multipliers;
	}
	public void setMultipliers(List<Multiplier> multipliers) {
		this.multipliers = multipliers;
	}
	public static List<BaseSkill> getSkills() {
		return SKILLS;
	}
}
