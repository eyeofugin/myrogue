package rogue.game.pvp.individualcharacters;

import java.util.Arrays;

import rogue.framework.resources.Resources;
import rogue.game.combat.skills.Skill.DamageType;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.world.objects.entities.Entity.Proficiency;
import rogue.game.world.objects.entities.PlayableCharacter;
import util.DraftColor;

public class Rogue extends PlayableCharacter{
	public Rogue() {
		super();
		this.id=			Resources.ROGUE;
		this.tier=			1;
		this.name=			"Hada Spy Patrol";
		this.portraitId=	Resources.P_BALROG;
		this.maxLife=		39;
		this.lifeRegain=	2;
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
				SkillLibrary.HEXPROOF,0,0,0,0,0));
		this.stdDamageType=DamageType.NORMAL;
		this.stdDamageProf=Proficiency.LETHALITY;
		this.resistances=resistance(
				34,//NORMAL
				30,//BURNING
				20,//DARK
				21,//FREEZE
				22,//LIGHT
				15,//PSYCH
				20);//SHOCK
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
				30,//STRENGTH
				0, //FAITH
				18,//INTELLIGENCE
				34, //LETHALITY
				12);//PRECISION
		this.colors=Arrays.asList(new DraftColor[] {DraftColor.BLUE});
	}
}
