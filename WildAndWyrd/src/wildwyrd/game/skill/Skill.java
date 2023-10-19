package wildwyrd.game.skill;

import wildwyrd.game.Entity;
import wildwyrd.game.GamePanel;
import wildwyrd.game.GameState;
import wildwyrd.game.object.Dialoge;

public class Skill extends Entity {
	public int id;
	String name;
	public String desc;
	SkillType skillType;
	SpellType spellType = SpellType.NONE;
	boolean active = false;
	
	public Skill(GamePanel gp) {
		super(gp);
		setDialogue();
	}

	public void setDialogue() {
		dialogues[0][0] = new Dialoge("No skill to use!",1);
	}
	
	public void activate() {
		//startDialogue(this, 0);
		//gp.gameState = GameState.combatState;
		//gp.keyH.enterPressed = false;
	}
}
