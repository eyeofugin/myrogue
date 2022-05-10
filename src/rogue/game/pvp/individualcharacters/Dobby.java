package rogue.game.pvp.individualcharacters;

import java.util.Arrays;

import rogue.framework.resources.Resources;
import rogue.game.combat.skills.Skill.DamageType;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.world.objects.entities.Entity.Proficiency;
import util.DraftColor;
import rogue.game.world.objects.entities.PlayableCharacter;

public class Dobby extends PlayableCharacter{
	public Dobby() {
		super();
		this.id=			Resources.DOBBY;
		this.tier=			2;
		this.name=			"Dobby";
		this.portraitId=	Resources.P_DOBBY;
		this.maxLife=		21;
		this.lifeRegain=	2;
		this.maxMana=		54;
		this.manaRegain=	8;
		this.currentLife 	= this.maxLife;
		this.currentMana 	= this.maxMana;
		this.maxActions=	2;
		this.maxMovement=	2;
		this.currentActions=this.maxActions;
		this.currentMovement=this.maxMovement;
		this.range=			1;
		this.setSkills(getSkills(
				SkillLibrary.TELEPORT,
				SkillLibrary.BLUDGER,0,0,0,0));
		this.stdDamageType=DamageType.MAGICAL;
		this.stdDamageProf=Proficiency.KNOWLEDGE;
		this.resistances=resistance(
				17,//NORMAL
				12,//BURNING
				14,//DARK
				12,//FREEZE
				19,//LIGHT
				18,//PSYCH
				12);//SHOCK
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
				8,//STRENGTH
				0, //FAITH
				31,//INTELLIGENCE
				0, //LETHALITY
				28);//PRECISION
		this.colors=Arrays.asList(new DraftColor[] {DraftColor.BLUE});	
	}
}
