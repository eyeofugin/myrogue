package rogue.game.combat.skills;

import java.util.List;

public class ActiveSkill extends BaseSkill{

	private int distance;
	private int manaCost;
	private int lifeCost;
	private int actionCost;
	private int accuracy;
	
	public ActiveSkill(
			byte id,
			String name,
			String description,
			TargetType target,
			DamageType damage,
			int radius,
			List<Effect> effects,
			List<Multiplier> multipliers,
			int power,
			int distance,
			int manaCost,
			int lifeCost,
			int actionCost,
			int accuracy
			) {
		super(id,name,description,damage,target,radius,effects,multipliers);
		this.distance=distance;
		this.manaCost=manaCost;
		this.lifeCost=lifeCost;
		this.actionCost=actionCost;
		this.accuracy=accuracy;
	}


	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public int getManaCost() {
		return manaCost;
	}

	public void setManaCost(int manaCost) {
		this.manaCost = manaCost;
	}

	public int getLifeCost() {
		return lifeCost;
	}

	public void setLifeCost(int lifeCost) {
		this.lifeCost = lifeCost;
	}

	public int getAccuracy() {
		return accuracy;
	}


	public void setAccuracy(int accuracy) {
		this.accuracy = accuracy;
	}


	public int getActionCost() {
		return actionCost;
	}
	public void setActionCost(int actionCost) {
		this.actionCost = actionCost;
	}
}
