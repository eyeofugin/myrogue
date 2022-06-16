package rogue.game.pvp.individualcharacters;

import java.util.Arrays;

import rogue.framework.resources.Resources;
import rogue.game.combat.skills.Skill.DamageType;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.world.objects.entities.Entity.Proficiency;
import util.DraftColor;
import rogue.game.world.objects.entities.PlayableCharacter;

public class Obelix extends PlayableCharacter{
	public Obelix() {
		super();
		this.id=			Resources.OBELIX;
		this.tier=			3;
		this.name=			"Obelix";
		this.portraitId=	Resources.P_OBELIX;
		this.maxLife=		109;
		this.lifeRegain=	10;
		this.maxMana=		34;
		this.manaRegain=	12;
		this.currentLife 	= this.maxLife;
		this.currentMana 	= this.maxMana;
		this.maxActions=	1;
		this.maxMovement=	2;
		this.currentActions=this.maxActions;
		this.currentMovement=this.maxMovement;
		this.range=			1;
		this.setSkills(getSkills(
				SkillLibrary.ROCK_THROW,
				SkillLibrary.FELL_IN_THE_POT,
				SkillLibrary.SEND_IDEFIX,0,0,0));
		this.stdDamageType=DamageType.NORMAL;
		this.stdDamageProf=Proficiency.STRENGTH;
		this.resistances=resistance(
				66,//NORMAL
				49,//BURNING
				56,//DARK
				67,//FREEZE
				55,//LIGHT
				54,//PSYCH
				60);//SHOCK
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
				59,//STRENGTH
				12, //FAITH
				21,//INTELLIGENCE
				0, //LETHALITY
				0);//PRECISION
		this.colors=Arrays.asList(new DraftColor[] {DraftColor.GREEN,DraftColor.RED});	
	}
}
