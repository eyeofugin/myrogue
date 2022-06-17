package rogue.game.pvp.individualcharacters;

import java.util.Arrays;

import rogue.framework.resources.Resources;
import rogue.game.combat.skills.Skill.DamageType;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.world.objects.entities.Entity.Proficiency;
import rogue.game.world.objects.entities.PlayableCharacter;
import util.DraftColor;

public class Wampa extends PlayableCharacter{
	public Wampa() {
		super();
		this.id=			Resources.WAMPA;
		this.tier=			2;
		this.name=			"Wampa";
		this.portraitId=	Resources.P_BALROG;
		this.maxLife=		108;
		this.lifeRegain=	14;
		this.maxMana=		30;
		this.manaRegain=	15;
		this.currentLife 	= this.maxLife;
		this.currentMana 	= this.maxMana;
		this.maxActions=	1;
		this.maxMovement=	2;
		this.currentActions=this.maxActions;
		this.currentMovement=this.maxMovement;
		this.range=			1;
		this.setSkills(getSkills(
				SkillLibrary.MAUL,
				SkillLibrary.FROSTWALK,0,0,0,0));
		this.stdDamageType=DamageType.NORMAL;
		this.stdDamageProf=Proficiency.STRENGTH;
		this.resistances=resistance(
				62,//NORMAL
				61,//BURNING
				79,//DARK
				88,//FREEZE
				79,//LIGHT
				51,//PSYCH
				81);//SHOCK
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
				50,//STRENGTH
				0, //FAITH
				12,//INTELLIGENCE
				0, //LETHALITY
				0);//PRECISION
		this.colors=Arrays.asList(new DraftColor[] {DraftColor.GREEN});
	}
}
