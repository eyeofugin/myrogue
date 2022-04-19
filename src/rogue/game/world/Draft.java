package rogue.game.world;

import java.util.ArrayList;
import java.util.List;

import rogue.framework.resources.Property;
import rogue.game.pvp.individualcharacters.Baumbart;
import rogue.game.pvp.individualcharacters.Gimli;
import rogue.game.pvp.individualcharacters.Legolas;
import rogue.game.pvp.individualcharacters.LukeSkywalker;
import rogue.game.world.objects.entities.Entity;
import util.CharacterCard;

public class Draft {
	
	private int nrChoices=1;
	private int nrOptions=3;
	private int hMargin,vMargin;
	private Entity[] entities = new Entity[] {new Gimli(),new Baumbart(),new Legolas()/*, new LukeSkywalker()*/};
	
	public List<int[]> render(){
		List<int[]> compartments = new ArrayList<int[]>();
		int[] options = new int[Property.ROOM_SIZE*Property.ROOM_SIZE];
		options=renderOptions(options);
		compartments.add(options);
		
		return compartments;
	}
	private int[] renderOptions(int[] p) {
//		CharacterCard cc = new CharacterCard(this.entities[0]);
//		cc.finish();
//		int[] graphics = cc.getPixels();
//		int index = 0;
//		for(int y = 5; y < 405; y++) {
//			for(int x = 5; x <305; x++) {
//				if(graphics[index]!=-12450784) {
//					p[x + y * 300] = graphics[index];
//				}
//				index++;
//			}
//		}
		calcMargins();
		int xOffset = this.hMargin;
		int yOffset = this.vMargin;
		for(int i = 0; i < this.entities.length; i++) {
			CharacterCard cc = new CharacterCard(this.entities[i]);
			cc.finish();
			int[] graphics = cc.getPixels();
			int index = 0;
			for(int y = yOffset; y <=yOffset+CharacterCard.CARD_HEIGHT-1; y++) {
				for(int x = xOffset; x <= xOffset+CharacterCard.CARD_WIDTH-1; x++) {
				
					if(graphics[index]!=-12450784) {
						p[x + y * Property.ROOM_SIZE] = graphics[index];
					}
					index++;
				}
			}
			xOffset+=hMargin;
			xOffset+=CharacterCard.CARD_WIDTH;
		}
		return p;
	}
	private void calcMargins() {
		this.hMargin = (Property.ROOM_SIZE-CharacterCard.CARD_WIDTH*nrOptions) / (nrOptions+1);
		this.vMargin = (Property.ROOM_SIZE-CharacterCard.CARD_HEIGHT) / 2;
	}
	
}
