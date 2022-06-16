package rogue.game.pvp.individualcharacters;

import java.util.Arrays;

import rogue.framework.resources.Resources;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.combat.skills.Skill.DamageType;
import rogue.game.world.objects.entities.PlayableCharacter;
import rogue.game.world.objects.entities.Entity.Proficiency;
import util.DraftColor;

public class Rebel extends PlayableCharacter{
	public Rebel() {
		super();
		this.id=			Resources.REBEL;
		this.tier=			1;
		this.name=			"Rebel";
		this.portraitId=	Resources.P_BALROG;
		this.maxLife=		45;
		this.lifeRegain=	4;
		this.maxMana=		0;
		this.manaRegain=	0;
		this.currentLife 	= this.maxLife;
		this.currentMana 	= this.maxMana;
		this.maxActions=	1;
		this.maxMovement=	2;
		this.currentActions=this.maxActions;
		this.currentMovement=this.maxMovement;
		this.range=			1;
		this.setSkills(getSkills(
				SkillLibrary.UNDERDOG,0,0,0,0,0));
		this.stdDamageType=DamageType.NORMAL;
		this.stdDamageProf=Proficiency.PRECISION;
		this.resistances=resistance(
				35,//NORMAL
				32,//BURNING
				34,//DARK
				32,//FREEZE
				30,//LIGHT
				15,//PSYCH
				30);//SHOCK
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
				19,//STRENGTH
				0, //FAITH
				20,//INTELLIGENCE
				0, //LETHALITY
				45);//PRECISION
		this.colors=Arrays.asList(new DraftColor[] {DraftColor.WHITE});
	}
}
