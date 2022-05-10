package rogue.game.pvp.individualcharacters;

import java.util.Arrays;

import rogue.framework.resources.Resources;
import rogue.game.combat.skills.Skill.DamageType;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.world.objects.entities.Entity.Proficiency;
import util.DraftColor;
import rogue.game.world.objects.entities.PlayableCharacter;

public class DarthSion extends PlayableCharacter{

	public DarthSion() {
		super();
		this.id=			Resources.DARTH_SION;
		this.tier=			3;
		this.name=			"Darth Sion";
		this.portraitId=	Resources.P_D_SION;
		this.maxLife=		90;
		this.lifeRegain=	10;
		this.maxMana=		40;
		this.manaRegain=	8;
		this.currentLife 	= this.maxLife;
		this.currentMana 	= this.maxMana;
		this.maxActions=	2;
		this.maxMovement=	2;
		this.currentActions=this.maxActions;
		this.currentMovement=this.maxMovement;
		this.range=			1;
		this.setSkills(getSkills(
				SkillLibrary.HATEFUL_SWING,
				SkillLibrary.CONDEMN,
				SkillLibrary.UNSTOPPABLE,
				0,0,0));
		this.stdDamageType=DamageType.DARK;
		this.stdDamageProf=Proficiency.STRENGTH;
		this.resistances=resistance(
				45,//NORMAL
				62,//BURNING
				77,//DARK
				54,//FREEZE
				34,//LIGHT
				39,//PSYCH
				71);//SHOCK
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
				56,//STRENGTH
				59, //FAITH
				35,//INTELLIGENCE
				5, //LETHALITY
				7);//PRECISION
		this.colors=Arrays.asList(new DraftColor[] {DraftColor.BLACK,DraftColor.BLACK});
	}
}
