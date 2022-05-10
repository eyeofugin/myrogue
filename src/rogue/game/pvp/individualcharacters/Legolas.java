package rogue.game.pvp.individualcharacters;

import java.util.Arrays;

import rogue.framework.resources.Resources;
import rogue.game.combat.skills.Skill.DamageType;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.world.objects.entities.Entity.Proficiency;
import util.DraftColor;
import rogue.game.world.objects.entities.PlayableCharacter;

public class Legolas extends PlayableCharacter{

	public Legolas() {
		super();
		this.id=			Resources.LEGOLAS;
		this.tier=			3;
		this.name=			"Legolas";
		this.portraitId=	Resources.P_LEGOLAS;
		this.maxLife=		43;
		this.lifeRegain=	5;
		this.maxMana=		69;
		this.manaRegain=	13;
		this.currentLife 	= this.maxLife;
		this.currentMana 	= this.maxMana;
		this.maxActions=	2;
		this.maxMovement=	2;
		this.currentActions=this.maxActions;
		this.currentMovement=this.maxMovement;
		this.range=			4;
		this.setSkills(getSkills(
				SkillLibrary.EMPTY_REVOLVER,
				SkillLibrary.WOOD_WALK,
				SkillLibrary.ARROW_BARRAGE,0,0,0));
		this.stdDamageType=DamageType.NORMAL;
		this.stdDamageProf=Proficiency.PRECISION;
		this.resistances=resistance(
				22,//NORMAL
				23,//BURNING
				28,//DARK
				24,//FREEZE
				20,//LIGHT
				28,//MAGIC
				20);//SHOCK
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
				11, //FAITH
				30,//INTELLIGENCE
				0, //LETHALITY
				60);//PRECISION
		this.colors=Arrays.asList(new DraftColor[] {DraftColor.GREEN,DraftColor.RED});
	}
}
