package rogue.game.pvp.individualcharacters;

import java.util.Arrays;

import rogue.framework.resources.Resources;
import rogue.game.combat.skills.Skill.DamageType;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.world.objects.entities.Entity.Proficiency;
import util.DraftColor;
import rogue.game.world.objects.entities.PlayableCharacter;

public class Moody extends PlayableCharacter{
	public Moody() {
		super();
		this.id=			Resources.MOODY;
		this.tier=			2;
		this.name=			"Moody";
		this.portraitId=	Resources.P_MOODY;
		this.maxLife=		200;
		this.lifeRegain=	15;
		this.maxMana=		60;
		this.manaRegain=	10;
		this.currentLife 	= this.maxLife;
		this.currentMana 	= this.maxMana;
		this.maxActions=	4;
		this.maxMovement=	2;
		this.currentActions=this.maxActions;
		this.currentMovement=this.maxMovement;
		this.range=			1;
		this.setSkills(getSkills(
				SkillLibrary.STUPOR,
				SkillLibrary.TRUE_VISION,0,0,0,0));
		this.stdDamageType=DamageType.MAGICAL;
		this.stdDamageProf=Proficiency.KNOWLEDGE;
		this.resistances=resistance(
				35,//NORMAL
				32,//BURNING
				30,//DARK
				32,//FREEZE
				30,//LIGHT
				40,//PSYCH
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
				26,//STRENGTH
				0, //FAITH
				33,//INTELLIGENCE
				10, //LETHALITY
				30);//PRECISION
		this.colors=Arrays.asList(new DraftColor[] {DraftColor.WHITE});	
	}
}
