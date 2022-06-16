package rogue.game.pvp.individualcharacters;

import java.util.Arrays;

import rogue.framework.resources.Resources;
import rogue.game.combat.skills.Skill.DamageType;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.world.objects.entities.PlayableCharacter;
import rogue.game.world.objects.entities.Entity.Proficiency;
import util.DraftColor;

public class ProfessorX extends PlayableCharacter{
	public ProfessorX(){
		super();
		this.id=			Resources.PROFESSOR;
		this.tier=			4;
		this.name=			"Professor X";
		this.portraitId=	Resources.P_PROF_X;
		this.maxLife=		28;
		this.lifeRegain=	1;
		this.maxMana=		72;
		this.manaRegain=	18;
		this.currentLife 	= this.maxLife;
		this.currentMana 	= this.maxMana;
		this.maxActions=	2;
		this.maxMovement=	2;
		this.currentActions=this.maxActions;
		this.currentMovement=this.maxMovement;
		this.range=			1;
		this.setSkills(getSkills(
				SkillLibrary.PSYSHOCK,
				SkillLibrary.BLUDGER,
				SkillLibrary.TARNING,
				SkillLibrary.CEREBRO,0,0));
		this.stdDamageType=DamageType.NORMAL;
		this.stdDamageProf=Proficiency.STRENGTH;
		this.resistances=resistance(
				16,//NORMAL
				24,//BURNING
				18,//DARK
				24,//FREEZE
				20,//LIGHT
				27,//MAGIC
				14);//SHOCK
		this.multipliers=multipliers(
				1.0,//NORMAL
				1.0,//BURNING
				1.0,//DARK
				1.0,//FREEZE
				1.0,//LIGHT
				1.0,//PSYCH
				1.0,//SHOCK
				1.0);//HEAL
		this.proficiencies=proficiencies(
				5,//STRENGTH
				0, //FAITH
				82,//INTELLIGENCE
				0, //LETHALITY
				10);//PRECISION
		this.colors=Arrays.asList(new DraftColor[] {DraftColor.BLUE,DraftColor.WHITE});	
	}
}
