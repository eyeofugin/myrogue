package rogue.graphics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import rogue.framework.eventhandling.Connector;
import rogue.framework.resources.Property;
import rogue.game.pvp.CharacterLibrary;
import rogue.game.pvp.Team;
import rogue.game.pvp.individualcharacters.Batman;
import rogue.game.world.objects.entities.Entity;
import rogue.game.world.objects.entities.PlayableCharacter;
import util.CharacterCard;
import util.DraftColor;

public class CardOverview extends InformationContainer{
	private Map<Integer,double[]> turnProbabilities = new HashMap<>();
	private Entity[] entities = new Entity[] {new Batman(),new Batman(),new Batman(), new Batman()};
	private int nrChoices=1;
	private int nrOptions=3;
	private int hMargin,vMargin;
	
	public CardOverview(int xanch,int yanch,Connector connector) {
		this.xanchor=xanch;
		this.yanchor=yanch;
		this.connector=connector;
		turnProbabilities();
		this.pixels=new int[Property.ROOM_SIZE*Property.ROOM_SIZE];
	}
	private void turnProbabilities() {
		turnProbabilities.put(1, new double[] {0.75,0.2, 0.05,0.0, 0.0});
		turnProbabilities.put(2, new double[] {0.65,0.25,0.1, 0.0, 0.0});
		turnProbabilities.put(3, new double[] {0.5, 0.35,0.15,0.0, 0.0});
		turnProbabilities.put(4, new double[] {0.25,0.6, 0.2, 0.05,0.0});
		turnProbabilities.put(5, new double[] {0.0, 0.5, 0.25,0.2, 0.05});
	}
	public void buildDraft(Team t, List<Team> enemies,int turn) {
		List<DraftColor> enemyDraftColors = getDraftColors(enemies);
		List<DraftColor> ownDraftColors = t.getDraftColors();
		adjustDraftParameters(enemyDraftColors,ownDraftColors);
		entities = getRandomEntities(nrOptions, turn);
		finish();
	}
	public void deactivate(Entity ent) {
		for(int i = 0 ; i < this.entities.length; i++) {
			if(this.entities[i]!=null&&this.entities[i].equals(ent)) {
				this.entities[i]=null;
				this.nrChoices--;
			}
		}
	}
	private Entity[] getRandomEntities(int amount, int turn) {
		Entity[] result = new Entity[amount];
		Random rand = new Random();
		List<PlayableCharacter> inUse = new ArrayList<>();
		for(int i = 0; i < amount; i++) {
			boolean foundUnique = false;
			PlayableCharacter pc = new PlayableCharacter();
			while(!foundUnique) {
				int tier = getTier(turn);
				List<PlayableCharacter> tiered = CharacterLibrary.getTier(tier);
				pc = tiered.get(rand.nextInt(tiered.size()));
				if(!inUse.contains(pc)) {
					foundUnique=true;
					inUse.add(pc);
				}
			}

			result[i] = pc;
		}
		return result;
	}
	private int getTier(int turn) {
		double rand = Math.random();
		double[] a = turnProbabilities.get(turn);
		if(rand < a[4]) {
			return 5;
		}else if(rand < a[4]+a[3]) {
			return 4;
		}else if(rand < a[4]+a[3]+a[2]) {
			return 3;
		}else if(rand < a[4]+a[3]+a[2]+a[1]) {
			return 2;
		}else {
			return 1;
		}
		
	}
	private void adjustDraftParameters(List<DraftColor> enemy, List<DraftColor> own) {
		if(enemy.contains(DraftColor.WHITE)) {
			nrOptions-=2;
		}else if(enemy.contains(DraftColor.BLACK)) {
			nrOptions-=1;
		}
		if(own.contains(DraftColor.RED)) {
			nrChoices++;
		}
		if(own.contains(DraftColor.BLACK)) {
			nrOptions++;
		}
		if(own.contains(DraftColor.BLUE)) {
			nrOptions+=2;
		}
	}
	private List<DraftColor> getDraftColors(List<Team> teams){
		List<DraftColor> draftColors = new ArrayList<>();;
		for(Team t : teams) {
			draftColors.addAll(t.getDraftColors());
		}
		return draftColors.stream().distinct().collect(Collectors.toList());
	}
	public int[] finish() {
		renderOptions();
		return this.pixels;
	}
	private void renderOptions() {
		switch(nrOptions) {
		case 1:
			show1Option();
			break;
		case 2:
			show2Options();
			break;
		case 3:
			show3Options();
			break;
		case 4:
			show4Options();
			break;
		}
	}
	private void show1Option() {
		calcMargins(1,1);
		showOneLine(0,1,0);
	}
	private void show2Options() {
		calcMargins(1,2);
		showOneLine(0,2,0);
	}
	private void show3Options() {
		calcMargins(1,3);
		showOneLine(0,3,0);
	}

	private void show4Options() {
		calcMargins(2,2);
		showOneLine(0,2,0);
		int overFlow = vMargin+CharacterCard.CARD_HEIGHT;
		showOneLine(2,4,overFlow);
	}
	private void showOneLine(int indexf, int indexu, int overflow) {
		int xoff = hMargin;
		int yOff = vMargin+overflow;
		for(int i = indexf; i < indexu; i++) {
			CharacterCard cc = new CharacterCard(this.entities[i],this.xanchor+xoff,this.yanchor+yOff,this.connector);
			cc.finish();
			int[] graphics = cc.getPixels();
			int index = 0;
			for(int y = yOff; y <=yOff+CharacterCard.CARD_HEIGHT-1; y++) {
				for(int x = xoff; x <= xoff+CharacterCard.CARD_WIDTH-1; x++) {
				
					if(graphics[index]!=-12450784) {
						this.pixels[x + y * Property.ROOM_SIZE] = graphics[index];
					}
					index++;
				}
			}
			xoff+=hMargin;
			xoff+=CharacterCard.CARD_WIDTH;
		}
	}
	private void calcMargins(int rows, int columns) {
		this.hMargin = (Property.ROOM_SIZE-CharacterCard.CARD_WIDTH*columns) / (columns+1);
		this.vMargin = (Property.ROOM_SIZE-CharacterCard.CARD_HEIGHT*rows) / (rows+1);
	}
	public int getChoices() {
		return this.nrChoices;
	}
	public int getOptions() {
		return this.nrOptions;
	}
}
