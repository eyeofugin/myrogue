package rogue.game.world.objects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import rogue.framework.eventhandling.Connector;
import rogue.game.combat.skills.Skill.DamageType;
import rogue.game.combat.skills.Skill;
import rogue.game.combat.skills.Skill.Effect;
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
	private int maxMana;
	private int currentMana;
	private int range;
	private DamageType stdDamageType;
	private Proficiency stdDamageProf;
	
	private List<Effect> currentEffects = new ArrayList<>();
	private Map<Proficiency,Integer> proficiencies;
	private Map<DamageType,Integer> resistances;
	private Map<DamageType,Double> multipliers;
	
	
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
		loadStats(t);
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
			int maxLife,int maxMana,int maxActions,int maxMovement,int range,Skill[] skills,DamageType std,Proficiency stdP,
			Map<DamageType,Integer> resistances,Map<DamageType,Double> multipliers,Map<Proficiency,Integer> proficiencies) {
		super(id,0,0,portraitId,name,movement,connector);
		this.currentLife = maxLife;
		this.maxLife = maxLife;
		this.currentMana = maxMana;
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
	private void loadStats(CharacterTemplate t) {
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
		PRECISION,
		STRENGTH,
		INTELLIGENCE,
		FAITH,
		LETHALITY
	}
	public EntityType getEntityType() {
		return null;
	}
	public void refresh() {
		this.currentActions=this.maxActions;
		this.currentMovement=this.maxMovement;
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
	public void addEffect(Effect e) {
		this.currentEffects.add(e);
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
			return calcCurrentResourceString(this.currentLife>0?this.currentLife:0, this.maxLife);
		case "mana":
			return calcCurrentResourceString(this.currentMana>0?this.currentMana:0, this.maxMana);
		case "movement":
			return "m " + calcCurrentResourceString(this.currentMovement,this.maxMovement);
		case "action":
			return "a " + calcCurrentResourceString(this.currentActions,this.maxActions);
		default:
			return " ";
		}
	}
	private String calcCurrentResourceString(int current, int max) {
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
	public DamageType getBasicDamageType() {
		return this.stdDamageType;
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
