package rogue.game.pvp.individualcharacters;

import java.util.Arrays;

import rogue.framework.resources.Resources;
import rogue.game.combat.skills.Skill.DamageType;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.world.objects.entities.Entity.Proficiency;
import rogue.game.world.objects.entities.PlayableCharacter;
import util.DraftColor;

public class GreenMage extends PlayableCharacter{
	public GreenMage() {
		super();
		this.id=			Resources.GREEN_MAGE;
		this.tier=			1;
		this.name=			"Green Mage";
		this.portraitId=	Resources.P_BALROG;
		this.maxLife=		63;
		this.lifeRegain=	4;
		this.maxMana=		40;
		this.manaRegain=	10;
		this.currentLife 	= this.maxLife;
		this.currentMana 	= this.maxMana;
		this.maxActions=	2;
		this.maxMovement=	2;
		this.currentActions=this.maxActions;
		this.currentMovement=this.maxMovement;
		this.range=			1;
		this.setSkills(getSkills(
				SkillLibrary.PLANT,0,0,0,0,0));
		this.stdDamageType=DamageType.MAGICAL;
		this.stdDamageProf=Proficiency.STRENGTH;
		this.resistances=resistance(
				32,//NORMAL
				16,//BURNING
				38,//DARK
				28,//FREEZE
				35,//LIGHT
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
				38,//STRENGTH
				19, //FAITH
				20,//INTELLIGENCE
				0, //LETHALITY
				0);//PRECISION
		this.colors=Arrays.asList(new DraftColor[] {DraftColor.GREEN});
	}
}
