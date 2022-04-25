package rogue.game.pvp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rogue.framework.resources.Resources;
import rogue.game.pvp.individualcharacters.Balrog;
import rogue.game.pvp.individualcharacters.BasicSoldier;
import rogue.game.pvp.individualcharacters.Baumbart;
import rogue.game.pvp.individualcharacters.BobaFett;
import rogue.game.pvp.individualcharacters.DarthSion;
import rogue.game.pvp.individualcharacters.DarthVader;
import rogue.game.pvp.individualcharacters.Dobby;
import rogue.game.pvp.individualcharacters.DoloresUmbridge;
import rogue.game.pvp.individualcharacters.Dumbledore;
import rogue.game.pvp.individualcharacters.Gimli;
import rogue.game.pvp.individualcharacters.Hagrid;
import rogue.game.pvp.individualcharacters.Legolas;
import rogue.game.pvp.individualcharacters.LukeSkywalker;
import rogue.game.pvp.individualcharacters.Moody;
import rogue.game.pvp.individualcharacters.MotherTalzin;
import rogue.game.pvp.individualcharacters.R2D2;
import rogue.game.pvp.individualcharacters.Radagast;
import rogue.game.pvp.individualcharacters.Sam;
import rogue.game.pvp.individualcharacters.Voldemort;
import rogue.game.world.objects.entities.PlayableCharacter;

public class CharacterLibrary {

	
	public static HashMap<Integer,PlayableCharacter> characters = new HashMap<>();
	public static HashMap<Integer,Class> chars = new HashMap<>();
	
	public static void init() {
		characters.put(Resources.DARTH_VADER,new DarthVader());
		characters.put(Resources.DARTH_SION, new DarthSion());
		characters.put(Resources.LUKE, new LukeSkywalker());
		characters.put(Resources.BOBA, new BobaFett());
		characters.put(Resources.TALZIN, new MotherTalzin());
		characters.put(Resources.R2D2, new R2D2());
		characters.put(Resources.GIMLI, new Gimli());
		characters.put(Resources.RADAGAST, new Radagast());
		characters.put(Resources.SAMWISE, new Sam());
		characters.put(Resources.LEGOLAS, new Legolas());
		characters.put(Resources.BAUMBART, new Baumbart());
		characters.put(Resources.BALROG, new Balrog());
		characters.put(Resources.DOBBY, new Dobby());
		characters.put(Resources.UMBRIDGE, new DoloresUmbridge());
		characters.put(Resources.MOODY, new Moody());
		characters.put(Resources.HAGRID, new Hagrid());
		characters.put(Resources.DUMBLEDORE, new Dumbledore());
		characters.put(Resources.VOLDEMORT, new Voldemort());
		characters.put(Resources.BASIC_SOLDIER,new BasicSoldier());
		chars.put(Resources.DARTH_VADER,DarthVader.class);
		chars.put(Resources.DARTH_SION, DarthSion.class);
		chars.put(Resources.LUKE, LukeSkywalker.class);
		chars.put(Resources.BOBA, BobaFett.class);
		chars.put(Resources.TALZIN, MotherTalzin.class);
		chars.put(Resources.R2D2, R2D2.class);
		chars.put(Resources.GIMLI, Gimli.class);
		chars.put(Resources.RADAGAST, Radagast.class);
		chars.put(Resources.SAMWISE,  Sam.class);
		chars.put(Resources.LEGOLAS,  Legolas.class);
		chars.put(Resources.BAUMBART,  Baumbart.class);
		chars.put(Resources.BALROG,  Balrog.class);
		chars.put(Resources.DOBBY,  Dobby.class);
		chars.put(Resources.UMBRIDGE,  DoloresUmbridge.class);
		chars.put(Resources.MOODY,  Moody.class);
		chars.put(Resources.HAGRID,  Hagrid.class);
		chars.put(Resources.DUMBLEDORE,  Dumbledore.class);
		chars.put(Resources.VOLDEMORT,  Voldemort.class);
		chars.put(Resources.BASIC_SOLDIER, BasicSoldier.class);
	}
	public static PlayableCharacter get(int id) {
		try {
			return PlayableCharacter.class.cast(chars.get(id).newInstance());
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static List<PlayableCharacter> getTier(int tier) {
		List<PlayableCharacter> tieredPc = new ArrayList<>();
		for(Map.Entry entry : characters.entrySet()) {
			PlayableCharacter pc = PlayableCharacter.class.cast(entry.getValue());
			if(pc.getTier()==tier) {
				tieredPc.add(pc);
			}
		}
		return tieredPc;
	}
	
}
