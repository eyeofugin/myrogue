package rogue.game.pvp.individualcharacters;

import java.util.Arrays;

import rogue.framework.resources.Resources;
import rogue.game.combat.skills.Skill.DamageType;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.world.objects.entities.Entity.Proficiency;
import util.DraftColor;
import rogue.game.world.objects.entities.PlayableCharacter;

public class Dumbledore extends PlayableCharacter{

	public Dumbledore() {
		super();
		this.id=			Resources.DUMBLEDORE;
		this.tier=			5;
		this.name=			"Albus Dumbledore";
		this.portraitId=	Resources.P_DUMBLEDORE;
		this.maxLife=		43;
		this.lifeRegain=	1;
		this.maxMana=		70;
		this.manaRegain=	15;
		this.currentLife 	= this.maxLife;
		this.currentMana 	= this.maxMana;
		this.maxActions=	3;
		this.maxMovement=	2;
		this.currentActions=this.maxActions;
		this.currentMovement=this.maxMovement;
		this.range=			1;
		this.setSkills(getSkills(
				SkillLibrary.STUPOR,
				SkillLibrary.ALLY_SHIELD,
				SkillLibrary.FIRE_RING,
				SkillLibrary.PHOENIX_TELEPORTATION,0,0));
		this.stdDamageType=DamageType.MAGICAL;
		this.stdDamageProf=Proficiency.KNOWLEDGE;
		this.resistances=resistance(
				28,//NORMAL
				25,//BURNING
				39,//DARK
				25,//FREEZE
				34,//LIGHT
				39,//PSYCH
				25);//SHOCK
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
				21,//STRENGTH
				10, //FAITH
				65,//INTELLIGENCE
				0, //LETHALITY
				30);//PRECISION
		this.colors=Arrays.asList(new DraftColor[] {DraftColor.BLUE,DraftColor.WHITE,DraftColor.GREEN});	
	}
}
