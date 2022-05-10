package rogue.game.pvp.individualcharacters;

import java.util.Arrays;

import rogue.framework.resources.Resources;
import rogue.game.combat.skills.Skill.DamageType;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.world.objects.entities.Entity.Proficiency;
import util.DraftColor;
import rogue.game.world.objects.entities.PlayableCharacter;

public class Gimli extends PlayableCharacter{

	public Gimli() {
		super();
		this.id=			Resources.GIMLI;
		this.tier=			3;
		this.name=			"Gimli";
		this.portraitId=	Resources.P_GIMLI;
		this.maxLife=		63;
		this.lifeRegain=	8;
		this.maxMana=		40;
		this.manaRegain=	10;
		this.currentLife 	= this.maxLife;
		this.currentMana 	= this.maxMana;
		this.maxActions=	2;
		this.maxMovement=	2;
		this.currentActions=this.maxActions;
		this.currentMovement=this.maxMovement;
		this.range=			1;
		this.setSkills(getSkills(
				SkillLibrary.WEAPON_SWING,
				SkillLibrary.UNSTOPPABLE,
				SkillLibrary.MERCILESS_MASSACRE,0,0,0));
		this.stdDamageType=DamageType.NORMAL;
		this.stdDamageProf=Proficiency.STRENGTH;
		this.resistances=resistance(
				50,//NORMAL
				40,//BURNING
				45,//DARK
				41,//FREEZE
				38,//LIGHT
				30,//PSYCH
				48);//SHOCK
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
				40,//STRENGTH
				9, //FAITH
				34,//INTELLIGENCE
				5, //LETHALITY
				10);//PRECISION
		this.colors=Arrays.asList(new DraftColor[] {DraftColor.GREEN,DraftColor.RED});
	}
}
