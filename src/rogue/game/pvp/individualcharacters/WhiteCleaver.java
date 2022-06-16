package rogue.game.pvp.individualcharacters;

import java.util.Arrays;

import rogue.framework.resources.Resources;
import rogue.game.combat.skills.Skill.DamageType;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.world.objects.entities.Entity.Proficiency;
import util.DraftColor;
import rogue.game.world.objects.entities.PlayableCharacter;

public class WhiteCleaver extends PlayableCharacter{
	public WhiteCleaver() {
		super();
		this.id=			Resources.WHITESCYTHE;
		this.tier=			3;
		this.name=			"White Cleaver";
		this.portraitId=	Resources.P_W_CLEAVER;
		this.maxLife=		114;
		this.lifeRegain=	28;
		this.maxMana=		40;
		this.manaRegain=	6;
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
				SkillLibrary.BEHEAD,0,0,0));
		this.stdDamageType=DamageType.NORMAL;
		this.stdDamageProf=Proficiency.STRENGTH;
		this.resistances=resistance(
				68,//NORMAL
				39,//BURNING
				38,//DARK
				52,//FREEZE
				55,//LIGHT
				27,//PSYCH
				50);//SHOCK
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
				31,//STRENGTH
				0, //FAITH
				20,//INTELLIGENCE
				15, //LETHALITY
				0);//PRECISION
		this.colors=Arrays.asList(new DraftColor[] {DraftColor.WHITE,DraftColor.WHITE});}
}
