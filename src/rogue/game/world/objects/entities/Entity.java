package rogue.game.world.objects.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import rogue.framework.eventhandling.Connector;
import rogue.game.combat.skills.Skill;
import rogue.game.combat.skills.Skill.DamageType;
import rogue.game.combat.skills.Skill.Effect;
import rogue.game.combat.skills.Skill.Effect.EffectType;
import rogue.game.combat.skills.Skill.Effect.StatChange;
import rogue.game.combat.skills.Skill.Effect.StatusInfliction;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.world.objects.BattleLog;
import rogue.game.world.objects.Equipment;
import util.DraftColor;

public class Entity {
	
	protected int id;
	private int x,y;
	protected String name = "dummy";
	private int team;
	protected int portraitId = 0;
	private Integer appearance = null;
	
	protected List<Skill> skills;
	protected List<Equipment> equipments = new ArrayList<>();
	protected CharacterTab activeTab = CharacterTab.STATS;
	
	//draft
	protected List<DraftColor> colors = new ArrayList<>();
	protected int level = 0;
	protected int tier=0;
	protected int deathTimer = 5;
	protected int deathTimerCurrent = 5;
//Stats
	protected int maxLife;
	protected int currentLife;
	protected int lifeRegain;
	protected int maxMana;
	protected int currentMana;
	protected int manaRegain;
	protected int range;
	protected int visionDistance = 5;
	protected DamageType stdDamageType;
	protected Proficiency stdDamageProf;
	
	protected List<Effect> currentEffects = new ArrayList<>();
	protected Map<Proficiency,Integer> proficiencies = new HashMap<>();
	protected Map<DamageType,Integer> resistances = new HashMap<>();
	protected Map<DamageType,Double> multipliers = new HashMap<>();

	
	//Playing
	private int currentActions;
	protected int maxActions;
	
	private int currentMovement;
	protected int maxMovement;
	
	public Entity() {
		
	}
	public Entity(int id, int portraitId, String name, Connector connector) {
		this.id= id;
		this.portraitId=portraitId;
		this.name=name;
	}
	
	public Entity(int id, int portraitId, String name, int team,
			int maxLife,int lifeRegain,int maxMana,int manaRegain,int maxActions,int maxMovement,int range,List<Skill> skills,DamageType std,Proficiency stdP,
			Map<DamageType,Integer> resistances,Map<DamageType,Double> multipliers,Map<Proficiency,Integer> proficiencies) {
		this.id = id;
		this.portraitId = portraitId;
		this.name=name;
		this.currentLife = maxLife;
		this.maxLife = maxLife;
		this.lifeRegain=lifeRegain;
		this.currentMana = maxMana;
		this.manaRegain=manaRegain;
		this.maxMana = maxMana;
		this.maxActions=maxActions;
		this.currentActions=maxActions;
		this.maxMovement=maxMovement;
		this.currentMovement=maxMovement;
		this.range=range;
		this.skills=skills;
		this.stdDamageType=std;
		this.stdDamageProf=stdP;
		this.resistances=resistances;
		this.proficiencies=proficiencies;
		this.multipliers=multipliers;
	}
	
	
	public static enum CharacterTab{
		STATS,
		SKILLS,
		ITEMS,
		GEAR,
	}
	public static enum Proficiency{
		PRECISION("Precision"),
		STRENGTH("Strength"),
		INTELLIGENCE("Intelligence"),
		FAITH("Faith"),
		LETHALITY("Lethality");
		private final String value;
		private Proficiency(String s) {
			this.value = s;
		}
		public String value() {
			return this.value;
		}
	}
	
	protected static List<Skill> getSkills(int s1, int s2, int s3, int s4, int s5, int s6) {
		List<Skill> skills = new ArrayList<>();
		if(s1!=0) {skills.add(SkillLibrary.getSkill(s1));}
		if(s2!=0) {skills.add(SkillLibrary.getSkill(s2));}
		if(s3!=0) {skills.add(SkillLibrary.getSkill(s3));}
		if(s4!=0) {skills.add(SkillLibrary.getSkill(s4));}
		if(s5!=0) {skills.add(SkillLibrary.getSkill(s5));}
		if(s6!=0) {skills.add(SkillLibrary.getSkill(s6));}
		
		return skills;
	}
	protected static Map<DamageType,Integer> resistance(int normal,int burn,int dark,int freeze,int light,int psych,int shock){
		Map<DamageType,Integer> resistances = new HashMap<>();
		resistances.put(DamageType.BURNING, burn);
		resistances.put(DamageType.DARK, dark);
		resistances.put(DamageType.FREEZING, freeze);
		resistances.put(DamageType.LIGHT, light);
		resistances.put(DamageType.MAGICAL, psych);
		resistances.put(DamageType.SHOCK, shock);
		resistances.put(DamageType.NORMAL, normal);
		return resistances;
	}
	protected static Map<DamageType,Double> multipliers(double normal,double burn,double dark,double freeze,double light,double psych,double shock,double heal){
		Map<DamageType,Double> mult = new HashMap<>();
		mult.put(DamageType.NORMAL, normal);
		mult.put(DamageType.BURNING, burn);
		mult.put(DamageType.DARK, dark);
		mult.put(DamageType.FREEZING, freeze);
		mult.put(DamageType.LIGHT, light);
		mult.put(DamageType.MAGICAL, psych);
		mult.put(DamageType.SHOCK, shock);
		mult.put(DamageType.HEAL,heal);
		return mult;
	}
	protected static Map<Proficiency,Integer> proficiencies(int strength, int faith, int intelligence, int lethality, int precision){
		Map<Proficiency,Integer> proficiencies = new HashMap<>();
		proficiencies.put(Proficiency.STRENGTH,strength);
		proficiencies.put(Proficiency.FAITH,faith);
		proficiencies.put(Proficiency.INTELLIGENCE,intelligence);
		proficiencies.put(Proficiency.LETHALITY,lethality);
		proficiencies.put(Proficiency.PRECISION,precision);
		return proficiencies;
	}
	public void endOfTurn(BattleLog log) {
		for(Effect effect : this.currentEffects) {
			effect.turn();
			if(effect.getType().equals(EffectType.STATUS_INFLICTION)) 
				tickStatus(effect,log);
			if(effect.getType().equals(EffectType.STAT_CHANGE) && effect.getTurns()==0) {
				endStatChange(effect);
			}
			if(effect.getType().equals(EffectType.TRANSFORMATION) && effect.getTurns()==0) {
				endTransformation(effect);
			}
			if(effect.getType().equals(EffectType.BLOCK_ABILITY) && effect.getTurns()==0) {
				endBlock(effect);
			}
		}
		this.currentEffects.removeIf(e->e.getTurns()==0);
	}
	private void tickStatus(Effect e,BattleLog log) {
		switch(e.getStatus()) {
		case BLEEDING:
			this.damage(e.getIntensity());
			log.formulateEffect(this.name, e.getIntensity());
			break;
		case BURNING:
			this.damage(e.getIntensity());
			log.formulateEffect(this.name, e.getIntensity());
			break;
		case FROZEN:
			this.damage(e.getIntensity());
			log.formulateEffect(this.name, e.getIntensity());
			break;
		case BLESSED:
			this.heal(e.getIntensity());
			log.formulateHeal(this.name, e.getIntensity());
			break;
		default: break;
		}
	}
	private void endBlock(Effect e) {
		//this.skills[e.getIntensity()].setBlocked(false);
	}
	private void endStatChange(Effect e) {
		StatChange sc = e.getStatChange();
		if(sc.getStat()!=null) {
			revertChangeResist(sc.getStat(), sc.getMultiplier());
		}
		if(sc.getProf()!=null) {
			changeProf(sc.getProf(), -1*sc.getAmnt());
		}
	}
	public void applyStatChange(Effect e) {
		StatChange sc = e.getStatChange();
		if(sc.getStat()!=null) {
			changeResist(sc.getStat(), sc.getMultiplier());
		}
		if(sc.getProf()!=null) {
			changeProf(sc.getProf(), sc.getAmnt());
		}
	}
	public void applyTransformation(Effect e) {
		if(e.getTransformId()==-1) {
			Random rand = new Random();
			this.setAppearance(rand.nextInt(34));
		}
		this.setAppearance(e.getTransformId());
	}
	public void endTransformation(Effect e) {
		this.setAppearance(null);
	}
	public void applyBlock(Effect e) {
		//this.skills[e.getIntensity()].setBlocked(true);
	}
	public void addEffect(Effect e) {
		Effect copy = new Effect();
		copy.setIntensity(e.getIntensity());
		copy.setStatChange(e.getStatChange());
		copy.setStatus(e.getStatus());
		copy.setTurns(e.getTurns());
		copy.setType(e.getType());
		copy.setTransformId(e.getTransformId());
		this.currentEffects.add(copy);
		if(copy.getType().equals(EffectType.STAT_CHANGE)) {
			applyStatChange(copy);
		}if(copy.getStatus()!=null && copy.getStatus().equals(StatusInfliction.CLEAR)) {
			removeStatusEffects();
		}
		if(copy.getType().equals(EffectType.TRANSFORMATION)) {
			applyTransformation(copy);
		}
		if(copy.getType().equals(EffectType.BLOCK_ABILITY)) {
			applyBlock(copy);
		}
	}
	private void removeStatusEffects() {
		this.currentEffects.removeIf(e->e.getType().equals(EffectType.STATUS_INFLICTION));
	}
	public void refresh() {
		this.currentActions=this.maxActions-actionInfliction();
		this.currentMovement=this.maxMovement-movementInfliction();
	}
	public boolean useAction(int amnt) {
		if(amnt<=this.currentActions) {
			this.currentActions-=amnt;
			return true;
		}
		return false;
	}
	public boolean useMana(int amnt) {
		if(amnt<=this.currentMana) {
			this.currentMana-=amnt;
			return true;
		}
		return false;
	}
	public boolean useLife(int amnt) {
		if(amnt<=this.currentLife) {
			this.currentLife-=amnt;
			return true;
		}
		return false;
	}
	public boolean useMovement(int amnt) {
		if(amnt<=this.currentMovement) {
			this.currentMovement-=amnt;
			return true;
		}
		return false;
	}
	private int actionInfliction() {
		int infl = 0;
		for(Effect e:this.currentEffects) {
			if(e.getStatus().equals(StatusInfliction.FROZEN)) {
				infl+=this.maxActions;
			}
			if(e.getStatus().equals(StatusInfliction.PARALYSED)) {
				infl+=e.getIntensity();
			}
			if(e.getStatus().equals(StatusInfliction.STUNNED)) {
				infl+=this.maxActions;
			}
		}
		return infl;
	}
	private int movementInfliction() {
		int infl = 0;
		for(Effect e:this.currentEffects) {
			if(e.getStatus().equals(StatusInfliction.FROZEN)) {
				infl+=this.maxMovement;
			}
			if(e.getStatus().equals(StatusInfliction.PARALYSED)) {
				infl+=e.getIntensity();
			}
			if(e.getStatus().equals(StatusInfliction.STUNNED)) {
				infl+=this.maxMovement;
			}
			if(e.getStatus().equals(StatusInfliction.ROOTED)) {
				infl+=this.maxMovement;
			}
		}
		return infl;
	}

	
	public int getProficiency(Proficiency prof) {
		int p = this.proficiencies.get(prof);
		p+=getEquipmentProficiency(prof);
		return p;
	}
	public void changeProf(Proficiency prof, int amnt) {
		this.proficiencies.put(prof,this.proficiencies.get(prof)+amnt);
	}
	public void changeResist(DamageType dmg, double multiplier) {
		this.resistances.put(dmg, (int)(this.resistances.get(dmg)*multiplier));
	}
	public void revertChangeResist(DamageType dmg, double multiplier) {
		this.resistances.put(dmg,(int)(this.resistances.get(dmg)/multiplier));
	}
	private int getEquipmentProficiency(Proficiency prof) {
		int p = 0;
		for(Equipment e : getEquipments()) {
			p+=e.getProfBonus(prof);
		}
		return p;
	}
	public int getResistance(DamageType stat) {
		int s = this.resistances.get(stat);
		s+=getEquipmentResistances(stat);
		return s;
	}
	private int getEquipmentResistances(DamageType stat) {
		int p = 0;
		for(Equipment e : getEquipments()) {
			p+=e.getResistBonus(stat);
		}
		return p;
	}
	public double getMultiplier(DamageType stat) {
		double s = this.multipliers.get(stat);
		s+=getEquipmentMultipliers(stat);
		return s;
	}
	private double getEquipmentMultipliers(DamageType stat) {
		double p = 0;
		for(Equipment e : getEquipments()) {
			p+=e.getMultBonus(stat);
		}
		return p;
	}
	public void damage(int damage) {
		this.currentLife-=damage;
	}
	public void heal(int heal) {
		this.currentLife+=heal;
		if(this.currentLife>this.maxLife)
			this.currentLife=this.maxLife;
	}

	
	public boolean isUnstoppable() {
		for(Skill s : this.skills) {
			if(s.getId()==SkillLibrary.UNSTOPPABLE){
				return true;
			}
		}
		return false;
	}
	public boolean isWoodWalk() {
		for(Skill s : this.skills) {
			if(s.getId()==SkillLibrary.WOOD_WALK){
				return true;
			}
		}
		return false;
	}
	public boolean isIndestructible() {
		for(Effect e:this.currentEffects) {
			if(e.getStatus()!=null && e.getStatus().equals(StatusInfliction.INDESCTRUCTIBLE)) {
				return true;
			}
		}
		return false;
	}
	public boolean isCursed() {
		for(Effect e:this.currentEffects) {
			if(e.getStatus().equals(StatusInfliction.CURSED)) {
				return true;
			}
		}
		return false;
	}
	public boolean isAllTerrain() {
		for(Skill s : this.skills) {
			if(s.getId()==SkillLibrary.ALL_TERRAIN){
				return true;
			}
		}
		return false;
	}
	public Effect getRelocation() {
		for(Effect e: this.currentEffects) {
			if(e.getType().equals(EffectType.OBJECT_PUSH) || e.getType().equals(EffectType.OBJECT_PULL)) {
				return e;
			}
		}
		return null;
	}
	public void removeRelocations() {
		this.currentEffects.removeIf(e->e.getType().equals(EffectType.OBJECT_PULL)||
										e.getType().equals(EffectType.OBJECT_PUSH));
	}
	
	//render
	public String getLevelString() {
		return "Level "+ level;
	}
	public String getTierString() {
		return "Tier " + this.tier;
	}
	public String getColorIcons() {
		String result = "";
		for(DraftColor c : this.colors) {
			if(c.equals(DraftColor.BLACK)) {
				result+="°";
			}
			if(c.equals(DraftColor.BLUE)) {
				result+="~";
			}
			if(c.equals(DraftColor.RED)) {
				result+="+";
			}
			if(c.equals(DraftColor.WHITE)) {
				result+="*";
			}
			if(c.equals(DraftColor.GREEN)) {
				result+="#";
			}
		}
		return result;
	}
	public int getTier() {
		return this.tier;
	}
	public double getCurrentResourcePercentage(String resource) {
		switch(resource) {
		case "life":
			return (double)this.currentLife / this.maxLife; 
		case "mana":
			return (double)this.currentMana / this.maxMana;
		case "movement":
			return (double)this.currentMovement / this.maxMovement ;
		case "action":
			return (double)this.currentActions / this.maxActions;
		default: 
			return 0.0;
		}
	}
	public String getCurrentResourceString(String resource) {
		
		switch(resource) {
		case "life":
			return calcCurrentResourceString(this.currentLife>0?this.currentLife:0, this.maxLife, this.lifeRegain);
		case "mana":
			return calcCurrentResourceString(this.currentMana>0?this.currentMana:0, this.maxMana, this.manaRegain);
		case "movement":
			return "m " + calcCurrentResourceStringShort(this.currentMovement,this.maxMovement);
		case "action":
			return "a " + calcCurrentResourceStringShort(this.currentActions,this.maxActions);
		default:
			return " ";
		}
	}
	private String calcCurrentResourceString(int current, int max, int regain) {
		String temp = calcCurrentResourceStringShort(current, max);
		temp+="("+regain+")";
		return temp;
	}
	private String calcCurrentResourceStringShort(int current, int max) {
		int lDiff = Integer.toString(max).length()-Integer.toString(current).length();

		int addToCurrentL = lDiff;
		String currentStringL = "";
		String maxStringL = "";
		
		for(int i = 1; i <= addToCurrentL; i++) {
			currentStringL+=" ";
		}
		currentStringL+=current;
		maxStringL+=max;
		
		return currentStringL + "/" + maxStringL;
	}
	public Map<DamageType,Double> getMultipliersForTable(){
		Map<DamageType,Double> copy = new HashMap<>();
		for(Entry<DamageType,Double> e: this.multipliers.entrySet()) {
			if(e.getValue()!=1.0) {
				copy.put(e.getKey(),e.getValue());
			}
		}
		return copy;
	}

	
	//Getters and Setters
		
		public int getMaxLife() {
			return maxLife;
		}

		public int getMaxMana() {
			return maxMana;
		}

		public void setMaxMana(int maxMana) {
			this.maxMana = maxMana;
		}

		public int getCurrentMana() {
			return currentMana;
		}

		public void setCurrentMana(int currentMana) {
			this.currentMana = currentMana;
		}

		public int getCurrentActions() {
			return currentActions;
		}

		public void setCurrentActions(int currentActions) {
			this.currentActions = currentActions;
		}

		public int getMaxActions() {
			return maxActions;
		}

		public void setMaxActions(int maxActions) {
			this.maxActions = maxActions;
		}

		public int getCurrentMovement() {
			return currentMovement;
		}

		public void setCurrentMovement(int currentMovement) {
			this.currentMovement = currentMovement;
		}

		public int getMaxMovement() {
			return maxMovement;
		}

		public void setMaxMovement(int maxMovement) {
			this.maxMovement = maxMovement;
		}
		public void setMaxLife(int maxLife) {
			this.maxLife = maxLife;
		}
		public int getCurrentLife() {
			return currentLife;
		}
		public void setCurrentLife(int currentLife) {
			this.currentLife = currentLife;
		}
		public CharacterTab getActiveTab() {
			return activeTab;
		}
		public void setActiveTab(CharacterTab activeTab) {
			this.activeTab = activeTab;
		}
		public List<Equipment> getEquipments(){
			return this.equipments;
		}
		public void setEquipments(List<Equipment> equipments) {
			this.equipments = equipments;
		}
		public List<Skill> getSkills() {
			return skills;
		}
		public void setSkills(List<Skill>skills) {
			this.skills = skills;
		}
		public int getLevel() {
			return level;
		}
		public void setLevel(int level) {
			this.level = level;
		}

		public List<Effect> getCurrentEffects() {
			return currentEffects;
		}

		public int getVisionDistance() {
			return visionDistance;
		}

		public void setVisionDistance(int visionDistance) {
			this.visionDistance = visionDistance;
		}

		public void setCurrentEffects(List<Effect> currentEffects) {
			this.currentEffects = currentEffects;
		}
		public DamageType getBasicDamageType() {
			return this.stdDamageType;
		}
		public Map<Proficiency, Integer> getProficiencies() {
			return proficiencies;
		}

		public void setProficiencies(Map<Proficiency, Integer> proficiencies) {
			this.proficiencies = proficiencies;
		}

		public Map<DamageType, Integer> getResistances() {
			return resistances;
		}

		public void setResistances(Map<DamageType, Integer> resistances) {
			this.resistances = resistances;
		}

		public Map<DamageType, Double> getMultipliers() {
			return multipliers;
		}
		public void setMultipliers(Map<DamageType, Double> multipliers) {
			this.multipliers = multipliers;
		}

		public int getLifeRegain() {
			return lifeRegain;
		}

		public void setLifeRegain(int lifeRegain) {
			this.lifeRegain = lifeRegain;
		}

		public int getManaRegain() {
			return manaRegain;
		}

		public void setManaRegain(int manaRegain) {
			this.manaRegain = manaRegain;
		}

		public int getRange() {
			return range;
		}

		public void setRange(int range) {
			this.range = range;
		}

		public DamageType getStdDamageType() {
			return stdDamageType;
		}

		public void setStdDamageType(DamageType stdDamageType) {
			this.stdDamageType = stdDamageType;
		}

		public Proficiency getStdDamageProf() {
			return stdDamageProf;
		}

		public void setStdDamageProf(Proficiency stdDamageProf) {
			this.stdDamageProf = stdDamageProf;
		}

		@Override
		public String toString() {
			return "Entity [name="+this.name + ", equipments=" + equipments
					+ ", activeTab=" + activeTab + ", level=" + level + ", maxLife=" + maxLife + ", currentLife="
					+ currentLife + ", maxMana=" + maxMana + ", currentMana=" + currentMana + ", currentActions=" + currentActions
					+ ", maxActions=" + maxActions + ", currentMovement=" + currentMovement + ", maxMovement=" + maxMovement
					+ "]";
		}
		public int getTeam() {
			return team;
		}
		public void setTeam(int team) {
			this.team = team;
		}
		public Integer getAppearance() {
			return appearance;
		}
		public void setAppearance(Integer appearance) {
			this.appearance = appearance;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public int getPortraitId() {
			return portraitId;
		}
		public void setPortraitId(int portraitId) {
			this.portraitId = portraitId;
		}
		public int getX() {
			return x;
		}
		public void setX(int x) {
			this.x = x;
		}
		public int getY() {
			return y;
		}
		public void setY(int y) {
			this.y = y;
		}
		public List<DraftColor> getColors() {
			return colors;
		}
		public void setColors(List<DraftColor> colors) {
			this.colors = colors;
		}



}
