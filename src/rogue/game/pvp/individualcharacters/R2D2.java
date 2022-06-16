package rogue.game.pvp.individualcharacters;

import java.util.Arrays;

import rogue.framework.resources.Resources;
import rogue.game.combat.skills.Skill.DamageType;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.world.objects.entities.PlayableCharacter;
import rogue.game.world.objects.entities.Entity.Proficiency;
import util.DraftColor;

public class R2D2 extends PlayableCharacter{
	public R2D2() {
		super();
		this.id=			Resources.R2D2;
		this.tier=			2;
		this.name=			"R2D2";
		this.portraitId=	Resources.P_R2D2;
		this.maxLife=		21;
		this.lifeRegain=	1;
		this.maxMana=		40;
		this.manaRegain=	8;
		this.currentLife 	= this.maxLife;
		this.currentMana 	= this.maxMana;
		this.maxActions=	1;
		this.maxMovement=	2;
		this.currentActions=this.maxActions;
		this.currentMovement=this.maxMovement;
		this.range=			1;
		this.setSkills(getSkills(
				SkillLibrary.SHOW_AREA,
				SkillLibrary.SMOKE_SCREEN,0,0,0,0));
		this.stdDamageType=DamageType.SHOCK;
		this.stdDamageProf=Proficiency.KNOWLEDGE;
		this.resistances=resistance(
				24,//NORMAL
				31,//BURNING
				22,//DARK
				26,//FREEZE
				30,//LIGHT
				39,//PSYCH
				22);//SHOCK
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
				32,//INTELLIGENCE
				0, //LETHALITY
				0);//PRECISION
		this.colors=Arrays.asList(new DraftColor[] {DraftColor.BLUE});
	}
}
