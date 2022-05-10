package rogue.game.pvp.individualcharacters;

import java.util.Arrays;

import rogue.framework.resources.Resources;
import rogue.game.combat.skills.Skill.DamageType;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.world.objects.entities.Entity.Proficiency;
import util.DraftColor;
import rogue.game.world.objects.entities.PlayableCharacter;

public class BobaFett extends PlayableCharacter{

	public BobaFett() {
		super();
		this.id=			Resources.BOBA;
		this.tier=			2;
		this.name=			"Boba Fett";
		this.portraitId=	Resources.P_BOBA;
		this.maxLife=		52;
		this.lifeRegain=	2;
		this.maxMana=		55;
		this.manaRegain=	10;
		this.currentLife 	= this.maxLife;
		this.currentMana 	= this.maxMana;
		this.maxActions=	2;
		this.maxMovement=	2;
		this.currentActions=this.maxActions;
		this.currentMovement=this.maxMovement;
		this.range=			3;
		this.setSkills(getSkills(
				SkillLibrary.FLAMETHROWER,
				SkillLibrary.ROCKET,0,0,0,0));
		this.stdDamageType=DamageType.NORMAL;
		this.stdDamageProf=Proficiency.PRECISION;
		this.resistances=resistance(
				68,//NORMAL
				62,//BURNING
				41,//DARK
				62,//FREEZE
				41,//LIGHT
				31,//MAGIC
				62);//SHOCK
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
				32,//STRENGTH
				5, //FAITH
				28,//INTELLIGENCE
				10, //LETHALITY
				42);//PRECISION
		this.colors=Arrays.asList(new DraftColor[] {DraftColor.RED,DraftColor.BLUE});
	}
}
