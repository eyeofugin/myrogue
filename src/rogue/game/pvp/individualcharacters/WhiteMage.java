package rogue.game.pvp.individualcharacters;

import java.util.Arrays;

import rogue.framework.resources.Resources;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.combat.skills.Skill.DamageType;
import rogue.game.world.objects.entities.PlayableCharacter;
import rogue.game.world.objects.entities.Entity.Proficiency;
import util.DraftColor;

public class WhiteMage extends PlayableCharacter {
	public WhiteMage() {
		super();
		this.id=			Resources.WHITE_MAGE;
		this.tier=			1;
		this.name=			"White Mage";
		this.portraitId=	Resources.P_BALROG;
		this.maxLife=		45;
		this.lifeRegain=	5;
		this.maxMana=		32;
		this.manaRegain=	6;
		this.currentLife 	= this.maxLife;
		this.currentMana 	= this.maxMana;
		this.maxActions=	1;
		this.maxMovement=	2;
		this.currentActions=this.maxActions;
		this.currentMovement=this.maxMovement;
		this.range=			1;
		this.setSkills(getSkills(
				SkillLibrary.ALLY_SHIELD,0,0,0,0,0));
		this.stdDamageType=DamageType.MAGICAL;
		this.stdDamageProf=Proficiency.FAITH;
		this.resistances=resistance(
				32,//NORMAL
				35,//BURNING
				21,//DARK
				28,//FREEZE
				40,//LIGHT
				22,//PSYCH
				24);//SHOCK
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
				20,//STRENGTH
				30, //FAITH
				16,//INTELLIGENCE
				0, //LETHALITY
				0);//PRECISION
		this.colors=Arrays.asList(new DraftColor[] {DraftColor.WHITE});
	}
}
