package rogue.game.pvp.individualcharacters;

import java.util.Arrays;

import rogue.framework.resources.Resources;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.combat.skills.Skill.DamageType;
import rogue.game.world.objects.entities.PlayableCharacter;
import rogue.game.world.objects.entities.Entity.Proficiency;
import util.DraftColor;

public class BlueMage extends PlayableCharacter{
	public BlueMage() {
		super();
		this.id=			Resources.BLUE_MAGE;
		this.tier=			1;
		this.name=			"Blue Mage";
		this.portraitId=	Resources.P_BALROG;
		this.maxLife=		34;
		this.lifeRegain=	2;
		this.maxMana=		45;
		this.manaRegain=	8;
		this.currentLife 	= this.maxLife;
		this.currentMana 	= this.maxMana;
		this.maxActions=	1;
		this.maxMovement=	2;
		this.currentActions=this.maxActions;
		this.currentMovement=this.maxMovement;
		this.range=			1;
		this.setSkills(getSkills(
				SkillLibrary.SHOW_AREA,0,0,0,0,0));
		this.stdDamageType=DamageType.MAGICAL;
		this.stdDamageProf=Proficiency.KNOWLEDGE;
		this.resistances=resistance(
				22,//NORMAL
				23,//BURNING
				18,//DARK
				24,//FREEZE
				20,//LIGHT
				28,//MAGIC
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
				10,//STRENGTH
				0, //FAITH
				35,//INTELLIGENCE
				0, //LETHALITY
				5);//PRECISION
		this.colors=Arrays.asList(new DraftColor[] {DraftColor.BLUE});
	}
}
