package rogue.game.pvp.individualcharacters;

import java.util.Arrays;

import rogue.framework.resources.Resources;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.combat.skills.Skill.DamageType;
import rogue.game.world.objects.entities.PlayableCharacter;
import util.DraftColor;

public class Ghost extends PlayableCharacter{
	public Ghost() {
		super();
		this.id=			Resources.GHOST;
		this.tier=			1;
		this.name=			"Ghost";
		this.portraitId=	Resources.P_BALROG;
		this.maxLife=		20;
		this.lifeRegain=	10;
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
				SkillLibrary.ALL_TERRAIN,0,0,0,0,0));
		this.stdDamageType=DamageType.DARK;
		this.stdDamageProf=Proficiency.LETHALITY;
		this.resistances=resistance(
				90,//NORMAL
				90,//BURNING
				1,//DARK
				90,//FREEZE
				1,//LIGHT
				1,//PSYCH
				90);//SHOCK
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
				0,//STRENGTH
				0, //FAITH
				20,//INTELLIGENCE
				40, //LETHALITY
				10);//PRECISION
		this.colors=Arrays.asList(new DraftColor[] {DraftColor.BLUE});
	}
}
