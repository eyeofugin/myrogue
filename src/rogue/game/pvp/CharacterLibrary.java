package rogue.game.pvp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rogue.framework.resources.Resources;
import rogue.game.pvp.individualcharacters.Balrog;
import rogue.game.pvp.individualcharacters.BasicSoldier;
import rogue.game.pvp.individualcharacters.Batman;
import rogue.game.pvp.individualcharacters.Baumbart;
import rogue.game.pvp.individualcharacters.BlackMage;
import rogue.game.pvp.individualcharacters.BlueMage;
import rogue.game.pvp.individualcharacters.BobaFett;
import rogue.game.pvp.individualcharacters.ChinaSorrows;
import rogue.game.pvp.individualcharacters.DarthSion;
import rogue.game.pvp.individualcharacters.DarthVader;
import rogue.game.pvp.individualcharacters.Dobby;
import rogue.game.pvp.individualcharacters.DoloresUmbridge;
import rogue.game.pvp.individualcharacters.DoomedTraveler;
import rogue.game.pvp.individualcharacters.Dumbledore;
import rogue.game.pvp.individualcharacters.Ewok;
import rogue.game.pvp.individualcharacters.Fanatic;
import rogue.game.pvp.individualcharacters.Ghost;
import rogue.game.pvp.individualcharacters.Gimli;
import rogue.game.pvp.individualcharacters.Goblin;
import rogue.game.pvp.individualcharacters.Greedo;
import rogue.game.pvp.individualcharacters.GreenMage;
import rogue.game.pvp.individualcharacters.Hagrid;
import rogue.game.pvp.individualcharacters.Hellboy;
import rogue.game.pvp.individualcharacters.Legolas;
import rogue.game.pvp.individualcharacters.LukeSkywalker;
import rogue.game.pvp.individualcharacters.Moody;
import rogue.game.pvp.individualcharacters.MotherTalzin;
import rogue.game.pvp.individualcharacters.Obelix;
import rogue.game.pvp.individualcharacters.Ooze;
import rogue.game.pvp.individualcharacters.ProfessorX;
import rogue.game.pvp.individualcharacters.R2D2;
import rogue.game.pvp.individualcharacters.Radagast;
import rogue.game.pvp.individualcharacters.Rebel;
import rogue.game.pvp.individualcharacters.RedMage;
import rogue.game.pvp.individualcharacters.Rogue;
import rogue.game.pvp.individualcharacters.Sam;
import rogue.game.pvp.individualcharacters.Serpine;
import rogue.game.pvp.individualcharacters.SkulduggeryPleasant;
import rogue.game.pvp.individualcharacters.SolomonWreath;
import rogue.game.pvp.individualcharacters.Stormtrooper;
import rogue.game.pvp.individualcharacters.V;
import rogue.game.pvp.individualcharacters.Vampire;
import rogue.game.pvp.individualcharacters.Voldemort;
import rogue.game.pvp.individualcharacters.Wampa;
import rogue.game.pvp.individualcharacters.WhiteCleaver;
import rogue.game.pvp.individualcharacters.Yisan;
import rogue.game.world.objects.entities.PlayableCharacter;

public class CharacterLibrary {

	
	public static HashMap<Integer,PlayableCharacter> characters = new HashMap<>();
	public static HashMap<Integer,Class> chars = new HashMap<>();
	
	public static void init() {
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
		chars.put(Resources.BATMAN,  Batman.class);
//		chars.put(Resources.BASIC_SOLDIER, BasicSoldier.class);
		chars.put(Resources.BLACK_MAGE, BlackMage.class);
		chars.put(Resources.BLUE_MAGE, BlueMage.class);
		chars.put(Resources.CHINA, ChinaSorrows.class);
		chars.put(Resources.MTG_SOLDIER, DoomedTraveler.class);
		chars.put(Resources.EWOK, Ewok.class);
		chars.put(Resources.FANATIC, Fanatic.class);
		chars.put(Resources.GHOST, Ghost.class);
		chars.put(Resources.GOBLIN, Goblin.class);
		chars.put(Resources.GREEDO, Greedo.class);
		chars.put(Resources.GREEN_MAGE, GreenMage.class);
//		chars.put(Resources.HELLBOY, Hellboy.class);
		chars.put(Resources.OBELIX, Obelix.class);
		chars.put(Resources.OOZE, Ooze.class);
		chars.put(Resources.PROFESSOR, ProfessorX.class);
		chars.put(Resources.REBEL, Rebel.class);
		chars.put(Resources.RED_MAGE, RedMage.class);
		chars.put(Resources.ROGUE, Rogue.class);
		chars.put(Resources.SERPINE, Serpine.class);
//		chars.put(Resources.SKULDUGGERY, SkulduggeryPleasant.class);
		chars.put(Resources.SOLOMON, SolomonWreath.class);
		chars.put(Resources.STORMTROOPER, Stormtrooper.class);
		chars.put(Resources.V, V.class);
		chars.put(Resources.VAMPIRE, Vampire.class);
		chars.put(Resources.WAMPA, Wampa.class);
		chars.put(Resources.WHITESCYTHE, WhiteCleaver.class);
		chars.put(Resources.YISAN, Yisan.class);
		
		
		
		
		
		
		
		
		
		
		
		
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
		for(int key : chars.keySet()) {
			PlayableCharacter pc = get(key);
			if(pc.getTier()==tier) {
				tieredPc.add(pc);
			}
		}
		return tieredPc;
	}
	
}
