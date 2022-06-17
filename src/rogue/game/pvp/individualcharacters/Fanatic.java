package rogue.game.pvp.individualcharacters;

import java.util.Arrays;

import rogue.framework.resources.Resources;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.combat.skills.Skill.DamageType;
import rogue.game.world.objects.entities.PlayableCharacter;
import rogue.game.world.objects.entities.Entity.Proficiency;
import util.DraftColor;

public class Fanatic extends PlayableCharacter{
	public Fanatic() {
		super();
		this.id=			Resources.FANATIC;
		this.tier=			1;
		this.name=			"Fanatic";
		this.portraitId=	Resources.P_BALROG;
		this.maxLife=		50;
		this.lifeRegain=	2;
		this.maxMana=		0;
		this.manaRegain=	0;
		this.currentLife 	= this.maxLife;
		this.currentMana 	= this.maxMana;
		this.maxActions=	2;
		this.maxMovement=	2;
		this.currentActions=this.maxActions;
		this.currentMovement=this.maxMovement;
		this.range=			1;
		this.setSkills(getSkills(
				SkillLibrary.SACRIFICIAL_CURSE,0,0,0,0,0));
		this.stdDamageType=DamageType.NORMAL;
		this.stdDamageProf=Proficiency.FAITH;
		this.resistances=resistance(
				20,//NORMAL
				32,//BURNING
				30,//DARK
				32,//FREEZE
				30,//LIGHT
				19,//PSYCH
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
				36,//STRENGTH
				42, //FAITH
				27,//INTELLIGENCE
				5, //LETHALITY
				0);//PRECISION
		this.colors=Arrays.asList(new DraftColor[] {DraftColor.BLACK});
	}
}
