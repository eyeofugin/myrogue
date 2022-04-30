package rogue.graphics;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import rogue.framework.eventhandling.Connector;
import rogue.framework.eventhandling.Event;
import rogue.framework.resources.Property;
import rogue.framework.resources.Resources;
import rogue.game.pvp.Team;
import rogue.game.world.objects.entities.PlayableCharacter;
import util.CharacterSlide;
import util.MyColor;
import util.TextAlignment;
import util.TextEditor;

public class TeamOverview extends InformationContainer{
	
	
	private static int ACTIVE_HEADER_Y_FROM = 0;
	private static int ACTIVE_HEADER_Y_UNTIL = 20;
	private static int R1_Y_FROM = 32;
	private static int R2_Y_FROM = 187;
	private static int R3_Y_FROM = 339;
	private static int R4_Y_FROM = 575;
	private static int R5_Y_FROM = 730;
	private static int R6_Y_FROM = 885;
	private static int R_MARGIN = 12;
	private static int C_MARGIN = 12;
	private static int ACTIVE_INFO_Y_FROM = 500;
	private static int ACTIVE_TIER_INFO_X = 10;
	private static int ACTIVE_TIER_INFO_WIDTH=200;
	private static int ACTIVE_TIER_INFO_HEIGHT=40;
	private static int ACTIVE_AMOUNT_INFO_X = 210;
	private static int ACTIVE_AMOUNT_INFO_WIDTH = 200;
	private static int ACTIVE_AMOUNT_INFO_HEIGHT = 40;
	
	private static int PASSIVE_HEADER_Y_FROM = 540;
	private static int PASSIVE_HEADER_Y_YUNTIL = 560;
	private static int PASSIVE_INFO_Y_FROM = 1040;
	private static int PASSIVE_AMOUNT_INFO_X = 210;
	private static int PASSIVE_AMOUNT_INFO_WIDTH = 200;
	private static int PASSIVE_AMOUNT_INFO_HEIGHT = 40;
	
	private static int END_BUTTON_X_FROM = 340;
	private static int END_BUTTON_Y_FROM = 540;
	
	private static int C1_X_FROM = 15;
	private static int C2_X_FROM = 212;
	
	private Team team=new Team();
	private List<PlayableCharacter> active = new ArrayList<>();
	private List<PlayableCharacter> passive = new ArrayList<>();
	private int slideid=0;
	
	private List<CharacterSlide> activeSlides = new ArrayList<>();
	private List<CharacterSlide> passiveSlides = new ArrayList<>();
	
	
	public TeamOverview(Team t,int xanchor,int yanchor,Connector connector) {
		this.team=t;
		this.active = t.getCharacters();
		this.passive = t.getBench();
		this.width=420;
		this.height=1080;
		this.xanchor=xanchor;
		this.yanchor=yanchor;
		this.connector=connector;
		this.pixels=new int[width*height];
		this.editor= new TextEditor(Resources.textEditorConfig);
		newFinish();
	}
	
	public int[] render() {
		this.connector.wipe(this.xanchor,this.yanchor,this.width,this.height);
		this.pixels=new int[width*height];
		header();
		slides();
		activeInfo();
		passiveInfo();
		buttons();
		return this.pixels;
	}
	public void add(PlayableCharacter e) {
		if(this.active.size()<this.team.getMaxTeamSize()) {
			this.active.add(e);
			this.activeSlides.add(new CharacterSlide(this.slideid++,e,this.connector));
		}else {
			this.passive.add(e);
			this.passiveSlides.add(new CharacterSlide(this.slideid++,e,this.connector));
		}
	}
	public void trySwitch(int id) {
		CharacterSlide slide = getSlideViaId(id);
		if(slide!=null) {
			if(this.activeSlides.stream().map(s->s.getId()).collect(Collectors.toList()).contains(id)) {
				PlayableCharacter pc = slide.getPC();
				if(this.passive.size()<=this.team.getMaxBenchSize()) {
					for(int i = 0; i < this.active.size(); i++) {
						if(this.active.get(i).equals(pc)) {
							this.active.remove(i);
							break;
						}
					}
//					this.active.removeIf(i->i.getId(id));
					this.passive.add(pc);
					this.activeSlides.removeIf(s->s.equals(slide));
					this.passiveSlides.add(slide);
				}
			}else {
				PlayableCharacter pc = slide.getPC();
				if(this.active.size()<this.team.getMaxTeamSize()) {
					for(int i = 0; i < this.passive.size(); i++) {
						if(this.passive.get(i).equals(pc)) {
							this.passive.remove(i);
							break;
						}
					}
//					this.passive.removeIf(i->i.equals(pc));
					this.active.add(pc);
					this.passiveSlides.removeIf(s->s.equals(slide));
					this.activeSlides.add(slide);
				}
			}
		}
	}
	public void delete(int id) {
		CharacterSlide slide = getSlideViaId(id);
		if(slide!=null) {
			if(this.activeSlides.contains(slide)) {
				PlayableCharacter pc = slide.getPC();
				this.active.removeIf(i->i.equals(pc));
				this.activeSlides.removeIf(s->s.equals(slide));
			}else {
				PlayableCharacter pc = slide.getPC();
				this.passive.removeIf(i->i.equals(pc));
				this.passiveSlides.removeIf(s->s.equals(slide));
			}
		}
	}
	private CharacterSlide getSlideViaId(int id) {
		for(CharacterSlide slide : this.activeSlides) {
			if(slide.getId()==id) {
				return slide;
			}
		}
		for(CharacterSlide slide : this.passiveSlides) {
			if(slide.getId()==id) {
				return slide;
			}
		}
		return null;
	}
	private void newFinish() {
		initSlides();
	}
	private void initSlides() {
		for(PlayableCharacter pc : active) {
			this.activeSlides.add(new CharacterSlide(this.slideid++,pc,this.connector));
		}
		for(PlayableCharacter pc : passive) {
			this.passiveSlides.add(new CharacterSlide(this.slideid++,pc,this.connector));
		}
	}
	private void header() {
		writeLine("Current Team", 0, 420, ACTIVE_HEADER_Y_FROM, ACTIVE_HEADER_Y_UNTIL, 0, TextAlignment.CENTER, MyColor.DARKGREY, MyColor.GREEN);
		writeLine("Bench",0,420,PASSIVE_HEADER_Y_FROM,PASSIVE_HEADER_Y_YUNTIL,0,TextAlignment.CENTER,MyColor.DARKGREY,MyColor.GREEN);
	}
	private void slides() {
		int yoff = R_MARGIN+ACTIVE_HEADER_Y_UNTIL;
		for(int i = 0; i < activeSlides.size(); i++) {
			CharacterSlide s = activeSlides.get(i);
			s.setXanch(this.xanchor+C_MARGIN);
			s.setYanch(this.yanchor+yoff);
			s.finish();
			fillWithGraphics(C_MARGIN, C_MARGIN-1+s.width, yoff, yoff-1+s.height, s.getPixels(), false);
			yoff+=(R_MARGIN+s.height);
		}
		yoff = R_MARGIN+PASSIVE_HEADER_Y_YUNTIL;
		for(int i = 0; i < passive.size(); i++) {
			CharacterSlide s = passiveSlides.get(i);
			s.setXanch(this.xanchor+C_MARGIN);
			s.setYanch(this.yanchor+yoff);
			s.finish();
			fillWithGraphics(C_MARGIN, C_MARGIN-1+s.width, yoff, yoff-1+s.height, s.getPixels(), false);
			yoff+=(R_MARGIN+s.height);
		}
	}
	private void activeInfo() {
		writeLine(getTierInfo(),ACTIVE_TIER_INFO_X, ACTIVE_TIER_INFO_X-1+ACTIVE_TIER_INFO_WIDTH,ACTIVE_INFO_Y_FROM,ACTIVE_INFO_Y_FROM-1+ACTIVE_TIER_INFO_HEIGHT,3,TextAlignment.LEFT,MyColor.BLACK,MyColor.WHITE);
		writeLine(getActiveAmountInfo(),ACTIVE_AMOUNT_INFO_X, ACTIVE_AMOUNT_INFO_X-1+ACTIVE_AMOUNT_INFO_WIDTH,ACTIVE_INFO_Y_FROM,ACTIVE_INFO_Y_FROM-1+ACTIVE_AMOUNT_INFO_HEIGHT,3,TextAlignment.RIGHT,MyColor.BLACK,MyColor.WHITE);
	}
	private void passiveInfo() {
		writeLine(getPassiveAmountInfo(),PASSIVE_AMOUNT_INFO_X, PASSIVE_AMOUNT_INFO_X-1+PASSIVE_AMOUNT_INFO_WIDTH,PASSIVE_INFO_Y_FROM,PASSIVE_INFO_Y_FROM-1+PASSIVE_AMOUNT_INFO_HEIGHT,3,TextAlignment.RIGHT,MyColor.BLACK,MyColor.WHITE);
	}
	private void buttons() {
		writeButton("End Draft",END_BUTTON_X_FROM,END_BUTTON_Y_FROM);
		Event follow = new Event();
		follow.setEventId(Connector.END_DRAFT);
		Event e = new Event();
		e.setEventId(Connector.REQUEST_CONFIRMATION);
		e.setAfterConfirmEvent(follow);
		this.connector.addEvent(this.xanchor+END_BUTTON_X_FROM, this.yanchor+END_BUTTON_Y_FROM, Property.BUTTON_NORMAL_WIDTH, Property.BUTTON_NORMAL_HEIGHT, e);
	}
	private String getTierInfo() {
		int currentTier=0;
		for(PlayableCharacter pc : this.active) {
			currentTier+=pc.getTier();
		}
		return "Tier "+currentTier+"/"+Team.TIER_THRESHHOLD;
	}
	private String getActiveAmountInfo() {
		return "Amount "+this.active.size()+"/"+this.team.getMaxTeamSize();
	}
	private String getPassiveAmountInfo() {
		return "Amount "+this.passive.size()+"/"+this.team.getMaxBenchSize();
	}

	
	
}
