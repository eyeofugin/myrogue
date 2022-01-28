package rogue.game.combat.skills;

import java.util.ArrayList;
import java.util.List;

import rogue.framework.eventhandling.Connector;
import rogue.framework.eventhandling.Event;
import rogue.game.combat.skills.Skill.Effect.EffectType;
import rogue.game.world.objects.Entity.Proficiency;
import rogue.game.world.objects.PlayableCharacter;

public class Skill {
	
	protected int id;
	protected String name;
	protected String description;
	protected Event event;
	
	private SkillType type;
	
	private TargetType target;
	private DamageType damageType;
	private int radius;
	private int distance;
	private List<Effect> effects;
	private List<Multiplier> multipliers;
	
	private int manaCost;
	private int lifeCost;
	private int actionCost;
	private int accuracy;
	
	private int power;
	private int summonedId;
	
	public Skill(int id, String name, String description) {
		this.id = id;
		this.name=name;
		this.description=description;
		Event e = new Event();
		e.setEventId(Connector.SKILL_CHOSEN);
		e.setSkill(id);
		this.event=e;
	}
	
	public Skill(int id, String name, String description, SkillType type, TargetType target,
			DamageType damageType, int radius, int distance, List<Effect> effects, List<Multiplier> multipliers,
			int manaCost, int lifeCost, int actionCost, int accuracy, int power, int summonedId) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.type = type;
		this.target = target;
		this.damageType = damageType;
		this.radius = radius;
		this.distance = distance;
		this.effects = effects;
		this.multipliers = multipliers;
		this.manaCost = manaCost;
		this.lifeCost = lifeCost;
		this.actionCost = actionCost;
		this.accuracy = accuracy;
		this.power = power;
		this.summonedId = summonedId;

		Event e = new Event();
		e.setEventId(Connector.SKILL_CHOSEN);
		e.setSkill(id);
		this.event=e;
	}
	public static Skill getDamageSkill(int id, String name, String description, TargetType target,
			DamageType damageType, Effect[] effects, Multiplier[] multipliers,
			int power,int accuracy,int distance,int radius,int manaCost,int lifeCost,int actionCost) {
		List<Effect> eList = new ArrayList<>();
		if(effects!=null) {
			for(Effect e : effects) {
				eList.add(e);
			}
		}
		List<Multiplier> mList = new ArrayList<>();
		if(multipliers!=null) {
			for(Multiplier m :multipliers) {
				mList.add(m);
			}
		}
		return new Skill(id,name,description,SkillType.DAMAGE,target,damageType,radius,distance,eList,mList,manaCost,lifeCost,actionCost,accuracy,power,0);
	}
	public static Skill getSummonSkill(int id, String name, String description, int distance, int radius,
			int manaCost, int lifeCost, int actionCost, int summonId) {
		
		return new Skill(id,name,description,SkillType.SUMMON,TargetType.SINGLE_FREE,null,radius,distance,null,null,manaCost,lifeCost,actionCost,100,0,summonId);
	}
	public static Skill getPassive(int id, String name, String description,
			DamageType damageType, Effect[] effects, Multiplier[] multipliers,int power,
			int radius,int manaCost, int lifeCost, int actionCost) {
		List<Effect> eList = new ArrayList<>();
		if(effects!=null) {
			for(Effect e : effects) {
				eList.add(e);
			}
		}
		List<Multiplier> mList = new ArrayList<>();
		if(multipliers!=null) {
			for(Multiplier m :multipliers) {
				mList.add(m);
			}
		}
		return new Skill(id,name,description,SkillType.PASSIVE,TargetType.SELF,damageType,radius,0,eList,mList,manaCost,lifeCost,actionCost,100,power,0);
	}
	public static Skill getEnhancementSkill(int id, String name, String description, TargetType target,
			 Effect[] effects, Multiplier[] multipliers,int distance, int radius,
			int manaCost, int lifeCost, int actionCost) {
		List<Effect> eList = new ArrayList<>();
		if(effects!=null) {
			for(Effect e : effects) {
				eList.add(e);
			}
		}
		List<Multiplier> mList = new ArrayList<>();
		if(multipliers!=null) {
			for(Multiplier m :multipliers) {
				mList.add(m);
			}
		}
		return new Skill(id,name,description,SkillType.ENHANCEMENT,target,null,radius,distance,eList,mList,manaCost,lifeCost,actionCost,100,0,0);
	}
	public static Skill getMovementSkill(int id, String name, String description, Effect[] effects, Multiplier[] multipliers,
			 int distance,int manaCost, int lifeCost, int actionCost) {
		List<Effect> eList = new ArrayList<>();
		if(effects!=null) {
			for(Effect e : effects) {
				eList.add(e);
			}
		}
		List<Multiplier> mList = new ArrayList<>();
		if(multipliers!=null) {
			for(Multiplier m :multipliers) {
				mList.add(m);
			}
		}
		return new Skill(id,name,description,SkillType.MOVEMENT,TargetType.SINGLE_FREE,null,0,distance,eList,mList,manaCost,lifeCost,actionCost,100,0,0);
	}

	public static enum SkillType{
		DAMAGE,
		ENHANCEMENT,
		PASSIVE,
		MOVEMENT,
		SUMMON
	}
	public static enum DamageType{
		PIERCING("Piercing"),
		SLASHING("Slashing"),
		BLUDGEONING("Bludgeoning"),
		BURNING("Burning"),
		FREEZING("Freezing"),
		DARK("Dark"),
		LIGHT("Light"),
		PSYCHIC("Psychic"),
		SHOCK("Shock"),
		ALL("All"),
		HEAL("Heal"),
		NONE("None");
		private String value;
		private DamageType(String s) {
			this.value=s;
		}
		public String value() {
			return this.value;
		}
	}
	public static enum TargetType{
		SINGLE_TARGET,
		SINGLE_FREE,
		LINE,
		SURROUNDING,
		SELF,
		NONE,
	}
	public static class Effect{
		private EffectType type;
		private int turns;
		private int intensity; //for status inflictions
		private StatusInfliction status;
		private StatChange statChange;//for stat changes
		
		public Effect(EffectType type, int turns, int intensity, StatusInfliction status, StatChange change) {
			this.type=type;
			this.turns=turns;
			this.intensity=intensity;
			this.status=status;
			this.statChange=change;
		}
		
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
			STATUS_INFLICTION,
			STAT_CHANGE,
			TERRAIN_ENHANCEMENT,
			OBJECT_PUSH,
			OBJECT_PULL,
			PROTECTION_FROM,
			TELEPORT,
			NONE
		}
		public static enum StatusInfliction{
			BLEEDING,
			STUNNED,
			PARALYSED,
			BURNING,
			FROZEN,
			BLESSED,
			CURSED,
			CONFUSED,
			ROOTED,
			INDESCTRUCTIBLE,
			CLEAR,
			REMOVE_OBSTACLE
		}
		public static class StatChange{
			private DamageType stat;
			private double multiplier;
			private Proficiency prof;
			private int amnt;
			public StatChange(DamageType stat,double multiplier, Proficiency prof, int amnt) {
				this.stat=stat;
				this.multiplier=multiplier;
				this.prof=prof;
				this.amnt=amnt;
			}
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
		public Multiplier(Proficiency prof, double percentage) {
			this.prof=prof;
			this.percentage=percentage;
		}
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
	
	
	public int getSkillDamage(PlayableCharacter p) {
		int dmg = 0;
		int multiplierBonus = getMultiBonus(p);
		dmg += this.power;
		dmg += multiplierBonus;
		return (int)(dmg * p.getMultiplier(this.damageType));
	}
	private int getMultiBonus(PlayableCharacter p) {
		if(this.multipliers==null) {
			return 0;
		}
		int result = 0;
		for(Multiplier m : this.multipliers) {
			result +=(int)( m.percentage * p.getProficiency(m.prof)); 
		}
		return result;
	}
	public boolean hasTP() {
		if(this.effects==null) {
			return false;
		}
		for(Effect e : this.effects) {
			if(e.type!=null && e.type.equals(EffectType.TELEPORT)) {
				return true;
			}
		}
		return false;
	}
	
	//getters and setters 
	public int getId() {
		return this.id;
	}
	public Event getEvent() {
		return this.event;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public SkillType getType() {
		return type;
	}

	public TargetType getTarget() {
		return target;
	}

	public DamageType getDamageType() {
		return damageType;
	}

	public int getRadius() {
		return radius;
	}

	public int getDistance() {
		return distance;
	}

	public List<Effect> getEffects() {
		return effects;
	}

	public List<Multiplier> getMultipliers() {
		return multipliers;
	}

	public int getManaCost() {
		return manaCost;
	}

	public int getLifeCost() {
		return lifeCost;
	}

	public int getActionCost() {
		return actionCost;
	}

	public int getAccuracy() {
		return accuracy;
	}

	public int getPower() {
		return power;
	}

	public int getSummonedId() {
		return summonedId;
	}

}
