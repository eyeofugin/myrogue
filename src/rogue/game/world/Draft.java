package rogue.game.world;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import rogue.framework.eventhandling.Connector;
import rogue.framework.eventhandling.Event;
import rogue.framework.resources.Property;
import rogue.game.pvp.Team;
import rogue.game.world.objects.entities.Entity;
import rogue.game.world.objects.entities.PlayableCharacter;
import rogue.graphics.CardOverview;
import rogue.graphics.TeamOverview;

public class Draft {
	
	private TeamOverview teamOverview;
	private CardOverview cardOverview;
	
	private Connector connector;
	private Map<Integer,double[]> turnProbabilities = new HashMap<>();
	private Team ownTeam=new Team();
	
	public Draft(Connector connector) {
		this.connector=connector;
		turnProbabilities.put(1, new double[] {0.75,0.2, 0.05,0.0, 0.0});
		turnProbabilities.put(2, new double[] {0.65,0.25,0.1, 0.0, 0.0});
		turnProbabilities.put(3, new double[] {0.5, 0.35,0.15,0.0, 0.0});
		turnProbabilities.put(4, new double[] {0.25,0.6, 0.2, 0.05,0.0});
		turnProbabilities.put(5, new double[] {0.0, 0.5, 0.25,0.2, 0.05});
	}
	
	public void buildDraftFor(int teamNr, List<Team> teams, int turn) {
		this.ownTeam = getTeam(teams,teamNr);
		teamOverview = new TeamOverview(this.ownTeam,Property.START_OF_ACTIVE_CHAR_X,Property.START_OF_ACTIVE_CHAR_Y,this.connector);
		cardOverview = new CardOverview(Property.START_OF_ROOM_X,Property.START_OF_ROOM_Y,this.connector);
		cardOverview.buildDraft(this.ownTeam, teams.stream().filter(i->i.getTeamNr()!=teamNr).collect(Collectors.toList()), turn);
		
	}
	private Team getTeam(List<Team> teams, int nr) {
		for(Team t : teams) {
			if(t.getTeamNr()==nr) {
				return t;
			}
		}
		return null;
	}

	public List<int[]> render(){
		List<int[]> compartments = new ArrayList<int[]>();
		compartments.add(this.cardOverview.finish());
		compartments.add(this.teamOverview.render());
		return compartments;
	}
	private boolean isChoosingValid(int newTier) {
		if(cardOverview.getChoices()<1) {
			return false;
		}
		if(this.ownTeam.isFull(newTier)) {
			return false;
		}
		return true;
	}
	private void chooseCard(Entity e) {
		if(PlayableCharacter.class.isInstance(e)) {
			PlayableCharacter pc = PlayableCharacter.class.cast(e);
			if(isChoosingValid(pc.getTier())) {
				
				cardOverview.deactivate(pc);
				teamOverview.add(pc);
			}
		}

	}
	private void moveCard(Event e) {
		teamOverview.trySwitch(e.getCardnr());
	}

	
	public void mouseClicked(Event e) {
		if(e.getEventId().equals(Connector.CHOOSE_CARD)) {
			chooseCard(e.getEntity());
		}
		if(e.getEventId().equals(Connector.MOVE_TEAM_MEMBER)) {
			System.out.println("id "+e.getCardnr());
			moveCard(e);
		}
	}
	
}
