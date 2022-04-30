package util.calc;

import java.util.concurrent.ThreadLocalRandom;

public class MyMaths {

	public static int getPcProbability(int turn, int tier) {
		int m = 1;
		int t = 1;
		int apprTier = 3-tier;
		return 2;
	}
	public static int getDamage(int att, int def, int lethal) {
		return (int)(rdmize(att) * ((100+lethal)  /  (100+(double)rdmize(def))));
	}
	static private int rdmize(int a) {
		return a + ThreadLocalRandom.current().nextInt(-5, 5);
		
	}
}
