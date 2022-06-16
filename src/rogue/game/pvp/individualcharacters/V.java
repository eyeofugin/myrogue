
package rogue.game.pvp.individualcharacters;

import java.util.Arrays;

import rogue.framework.resources.Resources;
import rogue.game.combat.skills.Skill.DamageType;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.world.objects.entities.PlayableCharacter;
import rogue.game.world.objects.entities.Entity.Proficiency;
import util.DraftColor;

public class V extends PlayableCharacter{
	public V(){
		super();
		this.id=			Resources.V;
		this.tier=			2;
		this.name=			"V";
		this.portraitId=	Resources.P_V;
		this.maxLife=		43;
		this.lifeRegain=	2;
		this.maxMana=		43;
		this.manaRegain=	7;
		this.currentLife 	= this.maxLife;
		this.currentMana 	= this.maxMana;
		this.maxActions=	2;
		this.maxMovement=	2;
		this.currentActions=this.maxActions;
		this.currentMovement=this.maxMovement;
		this.range=			1;
		this.setSkills(getSkills(
				SkillLibrary.BATARANG,
				SkillLibrary.ROCKET,0,0,0,0));
		this.stdDamageType=DamageType.NORMAL;
		this.stdDamageProf=Proficiency.LETHALITY;
		this.resistances=resistance(
				42,//NORMAL
				46,//BURNING
				22,//DARK
				30,//FREEZE
				23,//LIGHT
				39,//PSYCH
				32);//SHOCK
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
				38,//STRENGTH
				0, //FAITH
				31,//INTELLIGENCE
				30, //LETHALITY
				6);//PRECISION
		this.colors=Arrays.asList(new DraftColor[] {DraftColor.WHITE,DraftColor.BLACK});}
}
