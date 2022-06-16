package rogue.game.pvp.individualcharacters;

import java.util.Arrays;

import rogue.framework.resources.Resources;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.combat.skills.Skill.DamageType;
import rogue.game.world.objects.entities.PlayableCharacter;
import rogue.game.world.objects.entities.Entity.Proficiency;
import util.DraftColor;

public class Vampire extends PlayableCharacter {
	public Vampire() {
		super();
		this.id=			Resources.VAMPIRE;
		this.tier=			1;
		this.name=			"Vampire";
		this.portraitId=	Resources.P_BALROG;
		this.maxLife=		34;
		this.lifeRegain=	0;
		this.maxMana=		0;
		this.manaRegain=	0;
		this.currentLife 	= this.maxLife;
		this.currentMana 	= this.maxMana;
		this.maxActions=	1;
		this.maxMovement=	2;
		this.currentActions=this.maxActions;
		this.currentMovement=this.maxMovement;
		this.range=			1;
		this.setSkills(getSkills(
				SkillLibrary.LIFELINK,0,0,0,0,0));
		this.stdDamageType=DamageType.DARK;
		this.stdDamageProf=Proficiency.LETHALITY;
		this.resistances=resistance(
				34,//NORMAL
				1,//BURNING
				23,//DARK
				30,//FREEZE
				9,//LIGHT
				41,//PSYCH
				36);//SHOCK
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
				0, //FAITH
				25,//INTELLIGENCE
				10, //LETHALITY
				0);//PRECISION
		this.colors=Arrays.asList(new DraftColor[] {DraftColor.BLACK});
	}
}
