package rogue;

import rogue.framework.Engine;
import rogue.framework.resources.Loader;

public class Main {
	
	public static void main(String[] args) {
		Loader.load();
		Engine.init();
	}
}
