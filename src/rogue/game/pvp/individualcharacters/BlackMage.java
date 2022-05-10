package rogue.game.pvp.individualcharacters;

import java.util.Arrays;

import rogue.framework.resources.Resources;
import rogue.game.combat.skills.Skill.DamageType;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.world.objects.entities.PlayableCharacter;
import util.DraftColor;

public class BlackMage extends PlayableCharacter{
	public BlackMage() {
		super();
		this.id=			Resources.BLACK_MAGE;
		this.tier=			1;
		this.name=			"Black Mage";
		this.portraitId=	Resources.P_BALROG;
		this.maxLife=		45;
		this.lifeRegain=	4;
		this.maxMana=		40;
		this.manaRegain=	8;
		this.currentLife 	= this.maxLife;
		this.currentMana 	= this.maxMana;
		this.maxActions=	2;
		this.maxMovement=	2;
		this.currentActions=this.maxActions;
		this.currentMovement=this.maxMovement;
		this.range=			1;
		this.setSkills(getSkills(
				SkillLibrary.CONDEMN,0,0,0,0,0));
		this.stdDamageType=DamageType.MAGICAL;
		this.stdDamageProf=Proficiency.LETHALITY;
		this.resistances=resistance(
				32,//NORMAL
				35,//BURNING
				38,//DARK
				28,//FREEZE
				18,//LIGHT
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
				26,//STRENGTH
				35, //FAITH
				15,//INTELLIGENCE
				35, //LETHALITY
				8);//PRECISION
		this.colors=Arrays.asList(new DraftColor[] {DraftColor.BLACK});
	}
}
