package rogue.framework.eventhandling;

import rogue.game.world.objects.entities.Entity;
import rogue.game.world.objects.entities.Entity.CharacterTab;
import rogue.game.world.objects.entities.PlayableCharacter;

public class Event {

	private String eventId;
	
	private PlayableCharacter character;
	private Entity entity;
	private int skill;
	private CharacterTab tab;
	private int cardnr;
	private Event afterConfirmEvent;
	
	private int x,y;
	private int width,height;
	
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
	public int getSkill() {
		return skill;
	}
	public void setSkill(int skill) {
		this.skill = skill;
	}
	//	@Override
//	public String toString() {
//		return "Event [eventId=" + eventId + ", character=" + character + ", object=" + object + ", skill=" + skill
//				+ ", tab=" + tab + ", x=" + x + ", y=" + y + "]";
//	}
	public Entity getEntity() {
		return entity;
	}
	public void setEntity(Entity entity) {
		this.entity = entity;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getCardnr() {
		return cardnr;
	}
	public void setCardnr(int cardnr) {
		this.cardnr = cardnr;
	}
	public Event getAfterConfirmEvent() {
		return afterConfirmEvent;
	}
	public void setAfterConfirmEvent(Event afterConfirmEvent) {
		this.afterConfirmEvent = afterConfirmEvent;
	}
}
