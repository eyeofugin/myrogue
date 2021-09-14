package rogue.framework.eventhandling;

import rogue.game.world.objects.PlayableCharacter;
import rogue.game.world.objects.SecondLayerObject;
import rogue.game.world.objects.PlayableCharacter.CharacterTab;

public class Event {

	private String eventId;
	
	private PlayableCharacter character;
	private SecondLayerObject object;
	private CharacterTab tab;
	private int x,y;
	
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	public PlayableCharacter getCharacter() {
		return character;
	}
	public void setCharacter(PlayableCharacter character) {
		this.character = character;
	}
	public SecondLayerObject getObject() {
		return object;
	}
	public void setObject(SecondLayerObject object) {
		this.object = object;
	}
	public CharacterTab getTab() {
		return tab;
	}
	public void setTab(CharacterTab tab) {
		this.tab = tab;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
}
