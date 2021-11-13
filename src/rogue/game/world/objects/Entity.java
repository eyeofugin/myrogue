package rogue.game.world.objects;

import java.util.ArrayList;
import java.util.List;

import rogue.framework.eventhandling.Connector;
import rogue.game.world.objects.PlayableCharacter.CharacterTab;
import util.MovementOption;

public class Entity extends SecondLayerObject{

	private boolean isPlayer = false;
	
	private Skill[] skills;
	private List<Equipment> equipments = new ArrayList<>();
	private CharacterTab activeTab = CharacterTab.STATS;
	
	protected int level = 0;
//Stats
	private int maxLife;
	private int currentLife;
	private int maxMana;
	private int currentMana;
	
	private int meeleeAtk1;
	private int meeleeAtk2;
	private int rangedAtk1;
	private int rangedAtk2;
	private int magicAtk1;
	private int magicAtk2;
	
	private int meeleeDef1;
	private int meeleeDef2;
	private int rangedDef1;
	private int rangedDef2;
	private int magicDef1;
	private int magicDef2;
	
	
//Playing
	private int currentActions;
	private int maxActions;
	
	private int currentMovement;
	private int maxMovement;
	
	public Entity() {
		super();
	}
	
	public Entity(int x, int y, byte id, Connector connector, String name, byte portraitId, boolean isPlayer, MovementOption movement) {
		super(id,x,y,portraitId,name,movement,connector);
		this.isPlayer = isPlayer;
		this.currentLife = 10;
		this.maxLife = 10;
		this.currentMana = 5;
		this.maxMana = 5;
	}
	
	
//Getters and Setters
	
	public int getMaxLife() {
		return maxLife;
	}
	public int getMeeleeDef1() {
		return meeleeDef1;
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

	public void setMeeleeDef1(int meeleeDef1) {
		this.meeleeDef1 = meeleeDef1;
	}


	public int getMeeleeDef2() {
		return meeleeDef2;
	}


	public void setMeeleeDef2(int meeleeDef2) {
		this.meeleeDef2 = meeleeDef2;
	}


	public int getRangedDef1() {
		return rangedDef1;
	}


	public void setRangedDef1(int rangedDef1) {
		this.rangedDef1 = rangedDef1;
	}


	public int getRangedDef2() {
		return rangedDef2;
	}


	public void setRangedDef2(int rangedDef2) {
		this.rangedDef2 = rangedDef2;
	}


	public int getMagicDef1() {
		return magicDef1;
	}


	public void setMagicDef1(int magicDef1) {
		this.magicDef1 = magicDef1;
	}


	public int getMagicDef2() {
		return magicDef2;
	}


	public void setMagicDef2(int magicDef2) {
		this.magicDef2 = magicDef2;
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
	public int getMeeleeAtk1() {
		return meeleeAtk1;
	}
	public void setMeeleeAtk1(int meeleeAtk1) {
		this.meeleeAtk1 = meeleeAtk1;
	}
	public int getMeeleeAtk2() {
		return meeleeAtk2;
	}
	public void setMeeleeAtk2(int meeleeAtk2) {
		this.meeleeAtk2 = meeleeAtk2;
	}
	public int getRangedAtk1() {
		return rangedAtk1;
	}
	public void setRangedAtk1(int rangedAtk1) {
		this.rangedAtk1 = rangedAtk1;
	}
	public int getRangedAtk2() {
		return rangedAtk2;
	}
	public void setRangedAtk2(int rangedAtk2) {
		this.rangedAtk2 = rangedAtk2;
	}
	public int getMagicAtk1() {
		return magicAtk1;
	}
	public void setMagicAtk1(int magicAtk1) {
		this.magicAtk1 = magicAtk1;
	}
	public int getMagicAtk2() {
		return magicAtk2;
	}
	public void setMagicAtk2(int magicAtk2) {
		this.magicAtk2 = magicAtk2;
	}
	public CharacterTab getActiveTab() {
		return activeTab;
	}
	public void setActiveTab(CharacterTab activeTab) {
		this.activeTab = activeTab;
	}
	public void setPlayer() {
		this.isPlayer = true;
	}
	public boolean isPlayer() {
		return this.isPlayer;
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
			return calcCurrentResourceString(this.currentLife, this.maxLife);
		case "mana":
			return calcCurrentResourceString(this.currentMana, this.maxMana);
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
	
	public int getNormalMeleeDamage() {
		int result = getMeeleeAtk1();
		for(Equipment e : getEquipments()) {
			result+=e.getMeleeDamageBonus();
		}
		return result;
		
	}
	public int getNormalMeleeDefense() {
		int result = getMeeleeDef1();
		for(Equipment e:getEquipments()) {
			result+=e.getMeleeDefenseBonus();
		}
		return result;
	}
	public void damage(int damage) {
		this.currentLife-=damage;
	}
}
