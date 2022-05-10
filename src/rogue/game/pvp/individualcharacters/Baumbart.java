package rogue.game.pvp.individualcharacters;

import java.util.Arrays;

import rogue.framework.resources.Resources;
import rogue.game.combat.skills.Skill.DamageType;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.world.objects.entities.Entity.Proficiency;
import util.DraftColor;
import rogue.game.world.objects.entities.PlayableCharacter;

public class Baumbart extends PlayableCharacter{
	public Baumbart() {
		super();
		this.id=			Resources.BAUMBART;
		this.tier=			3;
		this.name=			"Baumbart";
		this.portraitId=	Resources.P_BAUMBART;
		this.maxLife=		142;
		this.lifeRegain=	9;
		this.maxMana=		75;
		this.manaRegain=	12;
		this.currentLife 	= this.maxLife;
		this.currentMana 	= this.maxMana;
		this.maxActions=	1;
		this.maxMovement=	2;
		this.currentActions=this.maxActions;
		this.currentMovement=this.maxMovement;
		this.range=			1;
		this.setSkills(getSkills(
				SkillLibrary.TEND_THE_GARDEN,
				SkillLibrary.TALL_GRASS,
				SkillLibrary.PLANT,0,0,0));
		this.stdDamageType=DamageType.NORMAL;
		this.stdDamageProf=Proficiency.LETHALITY;
		this.resistances=resistance(
				73,//NORMAL
				28,//BURNING
				60,//DARK
				67,//FREEZE
				72,//LIGHT
				66,//PSYCH
				78);//SHOCK
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
				59,//STRENGTH
				2, //FAITH
				32,//INTELLIGENCE
				0, //LETHALITY
				12);//PRECISION
		this.colors=Arrays.asList(new DraftColor[] {DraftColor.GREEN});
	}
}
