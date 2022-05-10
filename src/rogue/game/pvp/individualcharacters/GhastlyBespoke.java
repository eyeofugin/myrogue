package rogue.game.pvp.individualcharacters;

import java.util.Arrays;

import rogue.framework.resources.Resources;
import rogue.game.combat.skills.Skill.DamageType;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.world.objects.entities.Entity.Proficiency;
import util.DraftColor;
import rogue.game.world.objects.entities.PlayableCharacter;

public class GhastlyBespoke extends PlayableCharacter{
	public GhastlyBespoke() {
		super();
		this.id=			Resources.GRAESSLICH;
		this.tier=			3;
		this.name=			"Graesslich Schneider";
		this.portraitId=	Resources.P_GRAESSLICH;
		this.maxLife=		54;
		this.lifeRegain=	5;
		this.maxMana=		55;
		this.manaRegain=	9;
		this.currentLife 	= this.maxLife;
		this.currentMana 	= this.maxMana;
		this.maxActions=	2;
		this.maxMovement=	2;
		this.currentActions=this.maxActions;
		this.currentMovement=this.maxMovement;
		this.range=			1;
		this.setSkills(getSkills(
				SkillLibrary.FIREBALL,
				SkillLibrary.WIND_WALL,
				SkillLibrary.FIST_BARRAGE,0,0,0));
		this.stdDamageType=DamageType.NORMAL;
		this.stdDamageProf=Proficiency.STRENGTH;
		this.resistances=resistance(
				56,//NORMAL
				32,//BURNING
				40,//DARK
				32,//FREEZE
				40,//LIGHT
				40,//PSYCH
				35);//SHOCK
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
				60,//STRENGTH
				10, //FAITH
				30,//INTELLIGENCE
				0, //LETHALITY
				40);//PRECISION
		this.colors=Arrays.asList(new DraftColor[] {DraftColor.WHITE});	
	}
}
