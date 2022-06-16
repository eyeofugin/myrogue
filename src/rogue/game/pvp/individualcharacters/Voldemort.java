package rogue.game.pvp.individualcharacters;

import java.util.Arrays;

import rogue.framework.resources.Resources;
import rogue.game.combat.skills.Skill.DamageType;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.world.objects.entities.Entity.Proficiency;
import util.DraftColor;
import rogue.game.world.objects.entities.PlayableCharacter;

public class Voldemort extends PlayableCharacter{
	public Voldemort() {
		super();
		this.id=			Resources.VOLDEMORT;
		this.tier=			5;
		this.name=			"Voldemort";
		this.portraitId=	Resources.P_VOLDEMORT;
		this.maxLife=		45;
		this.lifeRegain=	2;
		this.maxMana=		72;
		this.manaRegain=	10;
		this.currentLife 	= this.maxLife;
		this.currentMana 	= this.maxMana;
		this.maxActions=	3;
		this.maxMovement=	2;
		this.currentActions=this.maxActions;
		this.currentMovement=this.maxMovement;
		this.range=			1;
		this.setSkills(getSkills(
				SkillLibrary.FLAMETHROWER,
				SkillLibrary.TELEPORT,
				SkillLibrary.TARNING,
				SkillLibrary.MURDER_FEST,0,0));
		this.stdDamageType=DamageType.MAGICAL;
		this.stdDamageProf=Proficiency.KNOWLEDGE;
		this.resistances=resistance(
				30,//NORMAL
				30,//BURNING
				34,//DARK
				30,//FREEZE
				41,//LIGHT
				33,//PSYCH
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
				12,//STRENGTH
				0, //FAITH
				66,//INTELLIGENCE
				0, //LETHALITY
				30);//PRECISION
		this.colors=Arrays.asList(new DraftColor[] {DraftColor.BLUE,DraftColor.BLACK,DraftColor.RED});}
}
