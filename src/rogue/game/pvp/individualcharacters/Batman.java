package rogue.game.pvp.individualcharacters;

import java.util.Arrays;

import rogue.framework.resources.Resources;
import rogue.game.combat.skills.Skill.DamageType;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.world.objects.entities.Entity.Proficiency;
import util.DraftColor;
import rogue.game.world.objects.entities.PlayableCharacter;

public class Batman extends PlayableCharacter{
	public Batman(){
		super();
		this.id=			Resources.BATMAN;
		this.tier=			3;
		this.name=			"Batman";
		this.portraitId=	Resources.P_BATMAN;
		this.maxLife=		50;
		this.lifeRegain=	3;
		this.maxMana=		70;
		this.manaRegain=	10;
		this.currentLife 	= this.maxLife;
		this.currentMana 	= this.maxMana;
		this.maxActions=	3;
		this.maxMovement=	2;
		this.currentActions=this.maxActions;
		this.currentMovement=this.maxMovement;
		this.range=			1;
		this.setSkills(getSkills(
				SkillLibrary.BATARANG,
				SkillLibrary.SMOKE_SCREEN,
				SkillLibrary.GRAPPLING_HOOK,0,0,0));
		this.stdDamageType=DamageType.NORMAL;
		this.stdDamageProf=Proficiency.LETHALITY;
		this.resistances=resistance(
				45,//NORMAL
				39,//BURNING
				47,//DARK
				36,//FREEZE
				32,//LIGHT
				27,//PSYCH
				45);//SHOCK
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
				39,//STRENGTH
				0, //FAITH
				35,//INTELLIGENCE
				44, //LETHALITY
				32);//PRECISION
		this.colors=Arrays.asList(new DraftColor[] {DraftColor.BLACK,DraftColor.WHITE});
	}
}
