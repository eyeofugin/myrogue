package rogue.game.pvp.individualcharacters;

import java.util.Arrays;

import rogue.framework.resources.Resources;
import rogue.game.combat.skills.Skill.DamageType;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.world.objects.entities.PlayableCharacter;
import rogue.game.world.objects.entities.Entity.Proficiency;
import util.DraftColor;

public class Radagast extends PlayableCharacter{

	public Radagast() {
		super();
		this.id=			Resources.RADAGAST;
		this.tier=			2;
		this.name=			"Radagast";
		this.portraitId=	Resources.P_RADAGAST;
		this.maxLife=		48;
		this.lifeRegain=	2;
		this.maxMana=		58;
		this.manaRegain=	7;
		this.currentLife 	= this.maxLife;
		this.currentMana 	= this.maxMana;
		this.maxActions=	1;
		this.maxMovement=	2;
		this.currentActions=this.maxActions;
		this.currentMovement=this.maxMovement;
		this.range=			1;
		this.setSkills(getSkills(
				SkillLibrary.ALLY_HEAL,
				SkillLibrary.CROW_SCOUT,0,0,0,0));
		this.stdDamageType=DamageType.MAGICAL;
		this.stdDamageProf=Proficiency.FAITH;
		this.resistances=resistance(
				29,//NORMAL
				25,//BURNING
				40,//DARK
				25,//FREEZE
				34,//LIGHT
				39,//PSYCH
				22);//SHOCK
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
				10,//STRENGTH
				23, //FAITH
				46,//INTELLIGENCE
				0, //LETHALITY
				5);//PRECISION
		this.colors=Arrays.asList(new DraftColor[] {DraftColor.GREEN,DraftColor.GREEN});	
	}
}
