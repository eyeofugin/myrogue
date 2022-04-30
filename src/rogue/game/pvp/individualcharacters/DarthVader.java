package rogue.game.pvp.individualcharacters;

import java.util.Arrays;

import rogue.framework.resources.Resources;
import rogue.game.combat.skills.Skill.DamageType;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.world.objects.entities.Entity.Proficiency;
import util.DraftColor;
import rogue.game.world.objects.entities.PlayableCharacter;

public class DarthVader extends PlayableCharacter{

	public DarthVader() {
		super();
		this.id=			Resources.DARTH_VADER;
		this.tier=			4;
		this.name=			"Darth Vader";
		this.portraitId=	Resources.P_VADER;
		this.maxLife=		200;
		this.lifeRegain=	15;
		this.maxMana=		600;
		this.manaRegain=	10;
		this.currentLife 	= this.maxLife;
		this.currentMana 	= this.maxMana;
		this.maxActions=	4;
		this.maxMovement=	2;
		this.currentActions=this.maxActions;
		this.currentMovement=this.maxMovement;
		this.range=			1;
		this.setSkills(getSkills(
				SkillLibrary.HATEFUL_SWING,
				SkillLibrary.FORCE_CHOKE,
				SkillLibrary.WEAPON_THROW,
				SkillLibrary.MERCILESS_MASSACRE,0,0));
		this.stdDamageType=DamageType.DARK;
		this.stdDamageProf=Proficiency.STRENGTH;
		this.resistances=resistance(
				20,//NORMAL
				30,//BURNING
				20,//DARK
				20,//FREEZE
				30,//LIGHT
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
				1.1);//HEAL
		this.proficiencies=proficiencies(
				10,//STRENGTH
				0, //FAITH
				35,//INTELLIGENCE
				0, //LETHALITY
				5);//PRECISION
		this.colors=Arrays.asList(new DraftColor[] {DraftColor.RED,DraftColor.BLACK});
	}
}
