package rogue.game.world;

import java.util.ArrayList;
import java.util.List;

import rogue.framework.resources.Property;
import rogue.game.pvp.Team;
import rogue.game.pvp.individualcharacters.Balrog;
import rogue.game.pvp.individualcharacters.Batman;
import rogue.game.pvp.individualcharacters.Baumbart;
import rogue.game.pvp.individualcharacters.LukeSkywalker;
import rogue.game.world.objects.entities.Entity;
import util.CharacterCard;

public class Draft {
	
	private int nrChoices=1;
	private int nrOptions=4;
	private int hMargin,vMargin;
	private Entity[] entities = new Entity[] {new Balrog(),new Baumbart(),new Batman(), new LukeSkywalker()};
	
	public void buildDraftFor(int teamNr, List<Team> teams) {
		
	}
	public List<int[]> render(){
		List<int[]> compartments = new ArrayList<int[]>();
		int[] options = new int[Property.ROOM_SIZE*Property.ROOM_SIZE];
		options=renderOptions(options);
		compartments.add(options);
		
		return compartments;
	}
	private int[] renderOptions(int[] p) {
		switch(nrOptions) {
		case 2:
			show2Options(p);
			break;
		case 3:
			show3Options(p);
			break;
		case 4:
			show4Options(p);
			break;
		}
		return p;
	}
	private int[] show2Options(int[] p) {
		calcMargins(1,2);
		showOneLine(p,0,2,0);
		return p;
	}
	private int[] show3Options(int[] p) {
		calcMargins(1,3);
		showOneLine(p,0,3,0);
		return p;
	}
	private int[] showOneLine(int[] p,int indexf, int indexu, int overflow) {
		int xoff = hMargin;
		int yOff = vMargin+overflow;
		for(int i = indexf; i < indexu; i++) {
			CharacterCard cc = new CharacterCard(this.entities[i]);
			cc.finish();
			int[] graphics = cc.getPixels();
			int index = 0;
			for(int y = yOff; y <=yOff+CharacterCard.CARD_HEIGHT-1; y++) {
				for(int x = xoff; x <= xoff+CharacterCard.CARD_WIDTH-1; x++) {
				
					if(graphics[index]!=-12450784) {
						p[x + y * Property.ROOM_SIZE] = graphics[index];
					}
					index++;
				}
			}
			xoff+=hMargin;
			xoff+=CharacterCard.CARD_WIDTH;
		}
		return p;
	}
	private int[] show4Options(int[] p) {
		calcMargins(2,2);
		showOneLine(p,0,2,0);
		int overFlow = vMargin+CharacterCard.CARD_HEIGHT;
		showOneLine(p,2,4,overFlow);
		return p;
	}
	private void calcMargins(int rows, int columns) {
		this.hMargin = (Property.ROOM_SIZE-CharacterCard.CARD_WIDTH*columns) / (columns+1);
		this.vMargin = (Property.ROOM_SIZE-CharacterCard.CARD_HEIGHT*rows) / (rows+1);
	}
	
}
