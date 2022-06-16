package rogue.game.pvp.individualcharacters;

import java.util.Arrays;

import rogue.framework.resources.Resources;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.combat.skills.Skill.DamageType;
import rogue.game.world.objects.entities.PlayableCharacter;
import util.DraftColor;

public class Ooze extends PlayableCharacter{
	public Ooze() {
		super();
		this.id=			Resources.OOZE;
		this.tier=			1;
		this.name=			"Ooze";
		this.portraitId=	Resources.P_BALROG;
		this.maxLife=		44;
		this.lifeRegain=	13;
		this.maxMana=		30;
		this.manaRegain=	10;
		this.currentLife 	= this.maxLife;
		this.currentMana 	= this.maxMana;
		this.maxActions=	1;
		this.maxMovement=	2;
		this.currentActions=this.maxActions;
		this.currentMovement=this.maxMovement;
		this.range=			1;
		this.setSkills(getSkills(
				SkillLibrary.OUTLAST,0,0,0,0,0));
		this.stdDamageType=DamageType.NORMAL;
		this.stdDamageProf=Proficiency.STRENGTH;
		this.resistances=resistance(
				50,//NORMAL
				30,//BURNING
				32,//DARK
				18,//FREEZE
				31,//LIGHT
				48,//PSYCH
				29);//SHOCK
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
				24,//STRENGTH
				0, //FAITH
				5,//INTELLIGENCE
				0, //LETHALITY
				0);//PRECISION
		this.colors=Arrays.asList(new DraftColor[] {DraftColor.GREEN});
	}
}
