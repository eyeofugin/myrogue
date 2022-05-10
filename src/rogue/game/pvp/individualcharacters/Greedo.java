package rogue.game.pvp.individualcharacters;

import java.util.Arrays;

import rogue.framework.resources.Resources;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.combat.skills.Skill.DamageType;
import rogue.game.world.objects.entities.PlayableCharacter;
import rogue.game.world.objects.entities.Entity.Proficiency;
import util.DraftColor;

public class Greedo extends PlayableCharacter{
	public Greedo() {
		super();
		this.id=			Resources.GREEDO;
		this.tier=			2;
		this.name=			"Greedo";
		this.portraitId=	Resources.P_BALROG;
		this.maxLife=		48;
		this.lifeRegain=	2;
		this.maxMana=		50;
		this.manaRegain=	10;
		this.currentLife 	= this.maxLife;
		this.currentMana 	= this.maxMana;
		this.maxActions=	2;
		this.maxMovement=	2;
		this.currentActions=this.maxActions;
		this.currentMovement=this.maxMovement;
		this.range=			1;
		this.setSkills(getSkills(
				SkillLibrary.EMPTY_REVOLVER,
				SkillLibrary.C4,0,0,0,0));
		this.stdDamageType=DamageType.NORMAL;
		this.stdDamageProf=Proficiency.PRECISION;
		this.resistances=resistance(
				35,//NORMAL
				32,//BURNING
				30,//DARK
				32,//FREEZE
				30,//LIGHT
				15,//PSYCH
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
				25,//STRENGTH
				0, //FAITH
				30,//INTELLIGENCE
				10, //LETHALITY
				42);//PRECISION
		this.colors=Arrays.asList(new DraftColor[] {DraftColor.RED});
	}
}
