package rogue.game.combat.skills.individualskills;

import rogue.game.combat.skills.Skill;
import rogue.game.combat.skills.SkillLibrary;

public class DemonicForm extends Skill{

	public DemonicForm() {
		super(SkillLibrary.DEMONIC_FORM);
		this.name="Hellboys Demonic Form";
		this.description="Hellboy transforms into his Demonic Form";
		this.isPassive=true;
	}
}
