package rogue.game.world.objects;

public class Item {

	private int id;
	
	private String name;
	
	private boolean isEquipment;
	
	private boolean isConsumable;
	
	private Rarity rarity;
	
	
	
	public static enum Rarity{
		COMMON,
		UNCOMMON,
		RARE,
		MYTHIC,
		UNIQUE
	}
}
