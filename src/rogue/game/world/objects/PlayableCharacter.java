package rogue.game.world.objects;

import rogue.framework.eventhandling.Connector;

public class PlayableCharacter extends Entity{
	
	private CharacterTab activeTab = CharacterTab.STATS;
	private Skill[] skills;
	
	public PlayableCharacter() {
		super();
	}
	
	public PlayableCharacter(int x, int y, byte id,String name, byte portraitId, Connector connector) {
		super(x, y, id, connector,name,portraitId,true);
		loadSkills(CharacterTemplate.NONE);
	}
	
	public PlayableCharacter(int x, int y, byte id,String name, byte portraitId, Connector connector, CharacterTemplate template) {
		super(x, y, id, connector,name,portraitId,true);
		loadSkills(template);
	}
	
	private void loadSkills(CharacterTemplate t) {
		if(t.equals(CharacterTemplate.NONE)) {
			this.skills = new Skill[] {Skill.NONE,Skill.NONE,Skill.NONE,Skill.NONE,Skill.NONE,Skill.NONE,Skill.NONE};
		}
	}
	public static enum CharacterTab{
		STATS,
		SKILLS,
		ITEMS,
		GEAR,
	}
	public static enum CharacterTemplate{
		KNIGHT,
		NONE,
	}
	
	public CharacterTab getActiveTab() {
		return activeTab;
	}
	public void setActiveTab(CharacterTab activeTab) {
		this.activeTab = activeTab;
	}
	public Skill[] getSkills() {
		return skills;
	}
	public void setSkills(Skill[]skills) {
		this.skills = skills;
	}
}
