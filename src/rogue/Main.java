package rogue;

import rogue.framework.Engine;
import rogue.framework.resources.Loader;

public class Main {
	
	public static void main(String[] args) {
		Loader.load();
		Engine.init();
//		BattleLog battleLog = new BattleLog(Resources.textEditorConfig,new Connector(0,0));
//		battleLog.addRow("this is a test of my current battlelog, lets see if it works like i want it to work. player has dealt 5 normal damage to skeleotn");
	}
}
