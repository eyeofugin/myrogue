package rogue.game.pvp.individualcharacters;

import java.util.Arrays;

import rogue.framework.resources.Resources;
import rogue.game.combat.skills.Skill.DamageType;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.world.objects.entities.Entity.Proficiency;
import util.DraftColor;
import rogue.game.world.objects.entities.PlayableCharacter;

public class SolomonWreath extends PlayableCharacter{
	public SolomonWreath() {
		super();
		this.id=			Resources.SOLOMON;
		this.tier=			3;
		this.name=			"Solomon Wreath";
		this.portraitId=	Resources.P_SOLOMON;
		this.maxLife=		50;
		this.lifeRegain=	2;
		this.maxMana=		60;
		this.manaRegain=	10;
		this.currentLife 	= this.maxLife;
		this.currentMana 	= this.maxMana;
		this.maxActions=	2;
		this.maxMovement=	2;
		this.currentActions=this.maxActions;
		this.currentMovement=this.maxMovement;
		this.range=			1;
		this.setSkills(getSkills(
				SkillLibrary.SHADOW_SPEARS,
				SkillLibrary.SHADOW_WALK,
				SkillLibrary.SHADOW_WAVE,0,0,0));
		this.stdDamageType=DamageType.DARK;
		this.stdDamageProf=Proficiency.FAITH;
		this.resistances=resistance(
				32,//NORMAL
				35,//BURNING
				20,//DARK
				28,//FREEZE
				18,//LIGHT
				23,//PSYCH
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
				10,//STRENGTH
				52, //FAITH
				30,//INTELLIGENCE
				5, //LETHALITY
				10);//PRECISION
		this.colors=Arrays.asList(new DraftColor[] {DraftColor.BLACK});}
}
