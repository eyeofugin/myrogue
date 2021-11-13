package rogue.game.world.objects;

public class Equipment {

	private int meleeDamageBonus = 0;
	private int meleeDefenseBonus = 0;
	
	public Equipment(int meleeDamageBonus, int meleeDefenseBonus) {
		this.meleeDamageBonus = meleeDamageBonus;
		this.meleeDefenseBonus = meleeDefenseBonus;
	}
	
	public int getMeleeDamageBonus() {
		return this.meleeDamageBonus;
	}
	public int getMeleeDefenseBonus() {
		return this.meleeDefenseBonus;
	}
}
