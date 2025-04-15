package wildwyrd.game.combat;

import wildwyrd.game.items.Item;
import wildwyrd.game.playable.Combatant;
import wildwyrd.game.skill.Skill;

public class RecordFrame {
	public CombatStatus cs;
	//public String desc;
	public int value;
	public Combatant user;
	public Combatant target;
	public Item item;
	public Skill skill;
	public boolean inRear;
	
	public RecordFrame(CombatStatus cs, int value, Combatant user, Combatant target) {
		this(cs, value, user);
		this.target = target;
	}
	
	public RecordFrame(CombatStatus cs, int value, Combatant user, Item item) {
		this(cs, value, user);
		this.item = item;
	}
	
	public RecordFrame(CombatStatus cs, int value, Combatant user, Skill skill) {
		this(cs, value, user);
		this.skill = skill;
	}
	
	public RecordFrame(CombatStatus cs, int value, Combatant user, boolean inRear) {
		this(cs, value, user);
		this.inRear = inRear;
	}
	
	public RecordFrame(CombatStatus cs, int value, Combatant user) {
		this.cs = cs;
		this.value = value;
		this.user = user;
	}
}
