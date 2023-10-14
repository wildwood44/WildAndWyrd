package wildwyrd.game.skill;

import wildwyrd.game.Entity;
import wildwyrd.game.GamePanel;

public class Skill extends Entity {
	public int id;
	String name;
	public String desc;
	SkillType skillType;
	SpellType spellType = SpellType.NONE;
	boolean active = false;
	
	public Skill(GamePanel gp) {
		super(gp);
	}
	
	public void activate() {
		
	}
}
