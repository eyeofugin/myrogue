package rogue.game.pvp.individualcharacters;

import java.util.Arrays;

import rogue.framework.resources.Resources;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.combat.skills.Skill.DamageType;
import rogue.game.world.objects.entities.PlayableCharacter;
import rogue.game.world.objects.entities.Entity.Proficiency;
import util.DraftColor;

public class Goblin extends PlayableCharacter{
	public Goblin() {
		super();
		this.id=			Resources.GOBLIN;
		this.tier=			1;
		this.name=			"Goblin";
		this.portraitId=	Resources.P_BALROG;
		this.maxLife=		33;
		this.lifeRegain=	2;
		this.maxMana=		40;
		this.manaRegain=	2;
		this.currentLife 	= this.maxLife;
		this.currentMana 	= this.maxMana;
		this.maxActions=	4;
		this.maxMovement=	2;
		this.currentActions=this.maxActions;
		this.currentMovement=this.maxMovement;
		this.range=			1;
		this.setSkills(getSkills(
				SkillLibrary.SUMMON_GOBLIN,0,0,0,0,0));
		this.stdDamageType=DamageType.NORMAL;
		this.stdDamageProf=Proficiency.STRENGTH;
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
				37,//STRENGTH
				20, //FAITH
				5,//INTELLIGENCE
				5, //LETHALITY
				5);//PRECISION
		this.colors=Arrays.asList(new DraftColor[] {DraftColor.RED});
	}
}
