package rogue.game.pvp.individualcharacters;

import java.util.Arrays;

import rogue.framework.resources.Resources;
import rogue.game.combat.skills.Skill.DamageType;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.world.objects.entities.Entity.Proficiency;
import util.DraftColor;
import rogue.game.world.objects.entities.PlayableCharacter;

public class Serpine extends PlayableCharacter{
	public Serpine() {
		super();
		this.id=			Resources.SERPINE;
		this.tier=			3;
		this.name=			"Serpine";
		this.portraitId=	Resources.P_SERPINE;
		this.maxLife=		50;
		this.lifeRegain=	2;
		this.maxMana=		50;
		this.manaRegain=	12;
		this.currentLife 	= this.maxLife;
		this.currentMana 	= this.maxMana;
		this.maxActions=	2;
		this.maxMovement=	2;
		this.currentActions=this.maxActions;
		this.currentMovement=this.maxMovement;
		this.range=			1;
		this.setSkills(getSkills(
				SkillLibrary.ROPES,
				SkillLibrary.ZOMBIE_MINIONS,
				SkillLibrary.CRUCIO,0,0,0));
		this.stdDamageType=DamageType.MAGICAL;
		this.stdDamageProf=Proficiency.KNOWLEDGE;
		this.resistances=resistance(
				44,//NORMAL
				31,//BURNING
				39,//DARK
				21,//FREEZE
				30,//LIGHT
				21,//PSYCH
				28);//SHOCK
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
				34,//STRENGTH
				20, //FAITH
				41,//INTELLIGENCE
				15, //LETHALITY
				5);//PRECISION
		this.colors=Arrays.asList(new DraftColor[] {DraftColor.BLACK});}
}
