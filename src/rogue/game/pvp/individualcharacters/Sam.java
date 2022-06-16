package rogue.game.pvp.individualcharacters;

import java.util.Arrays;

import rogue.framework.resources.Resources;
import rogue.game.combat.skills.Skill.DamageType;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.world.objects.entities.PlayableCharacter;
import rogue.game.world.objects.entities.Entity.Proficiency;
import util.DraftColor;

public class Sam extends PlayableCharacter{

	public Sam() {
		super();
		this.id=			Resources.SAMWISE;
		this.tier=			2;
		this.name=			"Samwise";
		this.portraitId=	Resources.P_SAMWISE;
		this.maxLife=		40;
		this.lifeRegain=	3;
		this.maxMana=		40;
		this.manaRegain=	4;
		this.currentLife 	= this.maxLife;
		this.currentMana 	= this.maxMana;
		this.maxActions=	1;
		this.maxMovement=	2;
		this.currentActions=this.maxActions;
		this.currentMovement=this.maxMovement;
		this.range=			1;
		this.setSkills(getSkills(
				SkillLibrary.ALLY_HEAL,
				SkillLibrary.TARNING,0,0,0,0));
		this.stdDamageType=DamageType.NORMAL;
		this.stdDamageProf=Proficiency.STRENGTH;
		this.resistances=resistance(
				32,//NORMAL
				28,//BURNING
				27,//DARK
				28,//FREEZE
				26,//LIGHT
				17,//PSYCH
				23);//SHOCK
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
				23,//STRENGTH
				10, //FAITH
				25,//INTELLIGENCE
				0, //LETHALITY
				9);//PRECISION
		this.colors=Arrays.asList(new DraftColor[] {DraftColor.WHITE});}
}
