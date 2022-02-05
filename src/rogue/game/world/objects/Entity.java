package rogue.game.world.objects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rogue.framework.eventhandling.Connector;
import rogue.game.combat.skills.Skill;
import rogue.game.combat.skills.Skill.DamageType;
import rogue.game.combat.skills.Skill.Effect;
import rogue.game.combat.skills.Skill.Effect.EffectType;
import rogue.game.combat.skills.Skill.Effect.StatChange;
import rogue.game.combat.skills.Skill.Effect.StatusInfliction;
import rogue.game.combat.skills.SkillLibrary;
import util.MovementOption;

public class Entity extends SecondLayerObject{
	
	private Skill[] skills;
	private List<Equipment> equipments = new ArrayList<>();
	private CharacterTab activeTab = CharacterTab.STATS;
	
	protected int level = 0;
	protected int team;
//Stats
	private int maxLife;
	private int currentLife;
	private int lifeRegain;
	private int maxMana;
	private int currentMana;
	private int manaRegain;
	private int range;
	private DamageType stdDamageType;
	private Proficiency stdDamageProf;
	
	private List<Effect> currentEffects = new ArrayList<>();
	private Map<Proficiency,Integer> proficiencies = new HashMap<>();
	private Map<DamageType,Integer> resistances = new HashMap<>();
	private Map<DamageType,Double> multipliers = new HashMap<>();
	
	
//Playing
	private int currentActions;
	private int maxActions;
	
	private int currentMovement;
	private int maxMovement;
	
	public Entity() {
		super();
	}
	
	public Entity(int x, int y, int id, Connector connector, String name, int portraitId, MovementOption movement) {
	}
	
	public Entity(int x, int y, int id, Connector connector, String name,CharacterTemplate t, int team, int portraitId, MovementOption movement) {
		super(id,x,y,portraitId,name,movement,connector);
		loadSkills(t);
		this.team = team;
		this.currentLife = 10;
		this.maxLife = 10;
		this.currentMana = 5;
		this.maxMana = 5;
		this.maxActions=1;
		this.currentActions=1;
		this.maxMovement=3;
		this.currentMovement=3;
	}
	public Entity(int id, int portraitId, String name, MovementOption movement, int team, Connector connector,
			int maxLife,int lifeRegain,int maxMana,int manaRegain,int maxActions,int maxMovement,int range,Skill[] skills,DamageType std,Proficiency stdP,
			Map<DamageType,Integer> resistances,Map<DamageType,Double> multipliers,Map<Proficiency,Integer> proficiencies) {
		super(id,0,0,portraitId,name,movement,connector);
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
	
	private void loadSkills(CharacterTemplate t) {
		if(t.equals(CharacterTemplate.NONE)) {
			this.setSkills(new Skill[] {
					SkillLibrary.getSkill(SkillLibrary.NONE),
					SkillLibrary.getSkill(SkillLibrary.NONE),
					SkillLibrary.getSkill(SkillLibrary.NONE),
					SkillLibrary.getSkill(SkillLibrary.NONE),
					SkillLibrary.getSkill(SkillLibrary.NONE),
					SkillLibrary.getSkill(SkillLibrary.NONE),
					SkillLibrary.getSkill(SkillLibrary.NONE)});
		}else if(t.equals(CharacterTemplate.KNIGHT)) {
			this.setSkills(new Skill[] {
					SkillLibrary.getSkill(SkillLibrary.HATEFUL_SWING),
					SkillLibrary.getSkill(SkillLibrary.NONE),
					SkillLibrary.getSkill(SkillLibrary.NONE),
					SkillLibrary.getSkill(SkillLibrary.NONE),
					SkillLibrary.getSkill(SkillLibrary.NONE),
					SkillLibrary.getSkill(SkillLibrary.NONE),
					SkillLibrary.getSkill(SkillLibrary.NONE)});
		}
	}
	public void endOfTurn() {
		for(Effect effect : this.currentEffects) {
			effect.turn();
			if(effect.getType().equals(EffectType.STATUS_INFLICTION)) 
				tickStatus(effect);
			if(effect.getType().equals(EffectType.STAT_CHANGE) && effect.getTurns()==0) {
				endStatChange(effect);
			}
		}
		this.currentEffects.removeIf(e->e.getTurns()==0);
	}
	public void tickStatus(Effect e) {
		switch(e.getStatus()) {
		case BLEEDING:
			this.damage(e.getIntensity());
			break;
		case BURNING:
			this.damage(e.getIntensity());
			break;
		case FROZEN:
			this.damage(e.getIntensity());
			break;
		case BLESSED:
			this.heal(e.getIntensity());
			break;
		default: break;
		}
	}
	public void endStatChange(Effect e) {
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
	public void addEffect(Effect e) {
		this.currentEffects.add(e);
		if(e.getType().equals(EffectType.STAT_CHANGE)) {
			applyStatChange(e);
		}if(e.getStatus().equals(StatusInfliction.CLEAR)) {
			removeStatusEffects();
		}
	}
	private void removeStatusEffects() {
		this.currentEffects.removeIf(e->e.getType().equals(EffectType.STATUS_INFLICTION));
	}
	public static enum CharacterTemplate{
		KNIGHT,
		NONE,
	}
	public static enum CharacterTab{
		STATS,
		SKILLS,
		ITEMS,
		GEAR,
	}
	public static enum EntityType{
		PLAYABLE,
		NPC,
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
	public EntityType getEntityType() {
		return null;
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
	public Skill[] getSkills() {
		return skills;
	}
	public void setSkills(Skill[]skills) {
		this.skills = skills;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getTeam() {
		return team;
	}

	public void setTeam(int team) {
		this.team = team;
	}

	public List<Effect> getCurrentEffects() {
		return currentEffects;
	}

	public void setCurrentEffects(List<Effect> currentEffects) {
		this.currentEffects = currentEffects;
	}

	public String getLevelString() {
		return "level "+ level;
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

	@Override
	public String toString() {
		return "Entity [name="+this.getName()+", skills=" + Arrays.toString(skills) + ", equipments=" + equipments
				+ ", activeTab=" + activeTab + ", level=" + level + ", maxLife=" + maxLife + ", currentLife="
				+ currentLife + ", maxMana=" + maxMana + ", currentMana=" + currentMana + ", currentActions=" + currentActions
				+ ", maxActions=" + maxActions + ", currentMovement=" + currentMovement + ", maxMovement=" + maxMovement
				+ "]";
	}
	
	
}
