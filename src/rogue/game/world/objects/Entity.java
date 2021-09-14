package rogue.game.world.objects;

import rogue.framework.eventhandling.Connector;

public class Entity extends SecondLayerObject{

	private boolean isPlayer = false;
	
	protected String name = "";
	protected int level = 0;
//Stats
	private short maxLife;
	private short currentLife;
	private short maxMana;
	private short currentMana;
	
	private short meeleeAtk1;
	private short meeleeAtk2;
	private short rangedAtk1;
	private short rangedAtk2;
	private short magicAtk1;
	private short magicAtk2;
	
	private short meeleeDef1;
	private short meeleeDef2;
	private short rangedDef1;
	private short rangedDef2;
	private short magicDef1;
	private short magicDef2;
	
	
//Playing
	private int currentActions;
	private int maxActions;
	
	private int currentMovement;
	private int maxMovement;
	
	public Entity() {
		super();
	}
	
	public Entity(int x, int y, byte id, Connector connector, String name, byte portraitId, boolean isPlayer) {
		super(id,x,y,portraitId,connector);
		this.name = name;
		this.isPlayer = isPlayer;
		this.currentLife = 700;
		this.maxLife = 1000;
		this.currentMana = 20;
		this.maxMana = 100;
	}
	
	
//Getters and Setters
	
	public short getMaxLife() {
		return maxLife;
	}
	public short getMeeleeDef1() {
		return meeleeDef1;
	}


	public short getMaxMana() {
		return maxMana;
	}

	public void setMaxMana(short maxMana) {
		this.maxMana = maxMana;
	}

	public short getCurrentMana() {
		return currentMana;
	}

	public void setCurrentMana(short currentMana) {
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

	public void setMeeleeDef1(short meeleeDef1) {
		this.meeleeDef1 = meeleeDef1;
	}


	public short getMeeleeDef2() {
		return meeleeDef2;
	}


	public void setMeeleeDef2(short meeleeDef2) {
		this.meeleeDef2 = meeleeDef2;
	}


	public short getRangedDef1() {
		return rangedDef1;
	}


	public void setRangedDef1(short rangedDef1) {
		this.rangedDef1 = rangedDef1;
	}


	public short getRangedDef2() {
		return rangedDef2;
	}


	public void setRangedDef2(short rangedDef2) {
		this.rangedDef2 = rangedDef2;
	}


	public short getMagicDef1() {
		return magicDef1;
	}


	public void setMagicDef1(short magicDef1) {
		this.magicDef1 = magicDef1;
	}


	public short getMagicDef2() {
		return magicDef2;
	}


	public void setMagicDef2(short magicDef2) {
		this.magicDef2 = magicDef2;
	}


	public void setMaxLife(short maxLife) {
		this.maxLife = maxLife;
	}
	public short getCurrentLife() {
		return currentLife;
	}
	public void setCurrentLife(short currentLife) {
		this.currentLife = currentLife;
	}
	public short getMeeleeAtk1() {
		return meeleeAtk1;
	}
	public void setMeeleeAtk1(short meeleeAtk1) {
		this.meeleeAtk1 = meeleeAtk1;
	}
	public short getMeeleeAtk2() {
		return meeleeAtk2;
	}
	public void setMeeleeAtk2(short meeleeAtk2) {
		this.meeleeAtk2 = meeleeAtk2;
	}
	public short getRangedAtk1() {
		return rangedAtk1;
	}
	public void setRangedAtk1(short rangedAtk1) {
		this.rangedAtk1 = rangedAtk1;
	}
	public short getRangedAtk2() {
		return rangedAtk2;
	}
	public void setRangedAtk2(short rangedAtk2) {
		this.rangedAtk2 = rangedAtk2;
	}
	public short getMagicAtk1() {
		return magicAtk1;
	}
	public void setMagicAtk1(short magicAtk1) {
		this.magicAtk1 = magicAtk1;
	}
	public short getMagicAtk2() {
		return magicAtk2;
	}
	public void setMagicAtk2(short magicAtk2) {
		this.magicAtk2 = magicAtk2;
	}
	public void setPlayer() {
		this.isPlayer = true;
	}
	public boolean isPlayer() {
		return this.isPlayer;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
}
