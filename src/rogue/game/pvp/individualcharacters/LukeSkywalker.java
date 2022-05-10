package rogue.game.pvp.individualcharacters;

import java.util.Arrays;

import rogue.framework.resources.Resources;
import rogue.game.combat.skills.Skill.DamageType;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.world.objects.entities.Entity.Proficiency;
import util.DraftColor;
import rogue.game.world.objects.entities.PlayableCharacter;

public class LukeSkywalker extends PlayableCharacter{

	
	public LukeSkywalker() {
		super();
		this.id=			Resources.LUKE;
		this.tier=			5;
		this.name=			"Luke Skywalker";
		this.portraitId=	Resources.P_LUKE;
		this.maxLife=		50;
		this.lifeRegain=	3;
		this.maxMana=		70;
		this.manaRegain=	16;
		this.currentLife 	= this.maxLife;
		this.currentMana 	= this.maxMana;
		this.maxActions=	3;
		this.maxMovement=	2;
		this.currentActions=this.maxActions;
		this.currentMovement=this.maxMovement;
		this.range=			1;
		this.setSkills(getSkills(
				SkillLibrary.RIGHTEOUS_SWING,
				SkillLibrary.SHOW_AREA,
				SkillLibrary.FORCE_PUSH,
				SkillLibrary.SHOCKWAVE_JUMP,0,0));
		this.stdDamageType=DamageType.NORMAL;
		this.stdDamageProf=Proficiency.FAITH;
		this.resistances=resistance(
				35,//NORMAL
				32,//BURNING
				30,//DARK
				32,//FREEZE
				30,//LIGHT
				38,//PSYCH
				32);//SHOCK
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
				40,//STRENGTH
				89, //FAITH
				31,//INTELLIGENCE
				0, //LETHALITY
				34);//PRECISION
		this.colors=Arrays.asList(new DraftColor[] {DraftColor.BLUE,DraftColor.GREEN,DraftColor.WHITE});
	}
}
