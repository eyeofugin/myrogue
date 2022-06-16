package rogue.game.pvp.individualcharacters;

import java.util.Arrays;

import rogue.framework.resources.Resources;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.combat.skills.Skill.DamageType;
import rogue.game.world.objects.entities.PlayableCharacter;
import rogue.game.world.objects.entities.Entity.Proficiency;
import util.DraftColor;

public class Yisan extends PlayableCharacter{
	public Yisan() {
		super();
		this.id=			Resources.YISAN;
		this.tier=			2;
		this.name=			"Yisan";
		this.portraitId=	Resources.P_BALROG;
		this.maxLife=		50;
		this.lifeRegain=	2;
		this.maxMana=		50;
		this.manaRegain=	9;
		this.currentLife 	= this.maxLife;
		this.currentMana 	= this.maxMana;
		this.maxActions=	1;
		this.maxMovement=	2;
		this.currentActions=this.maxActions;
		this.currentMovement=this.maxMovement;
		this.range=			2;
		this.setSkills(getSkills(
				SkillLibrary.ARAGOG,
				SkillLibrary.CALM_BEAST,0,0,0,0));
		this.stdDamageType=DamageType.MAGICAL;
		this.stdDamageProf=Proficiency.FAITH;
		this.resistances=resistance(
				32,//NORMAL
				21,//BURNING
				38,//DARK
				28,//FREEZE
				31,//LIGHT
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
				19,//STRENGTH
				26, //FAITH
				20,//INTELLIGENCE
				0, //LETHALITY
				0);//PRECISION
		this.colors=Arrays.asList(new DraftColor[] {DraftColor.GREEN});
	}
}
