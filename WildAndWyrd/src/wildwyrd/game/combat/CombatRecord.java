package wildwyrd.game.combat;

import java.util.ArrayList;
import java.util.List;

import wildwyrd.game.items.Item;
import wildwyrd.game.playable.Combatant;
import wildwyrd.game.skill.Skill;

public class CombatRecord {
	List<RecordFrame> rf;
	
	public CombatRecord() {
		rf = new ArrayList<RecordFrame>();
	}
	
	public void addFrame(CombatStatus cs, int value, Combatant user, Combatant target) {
		rf.add(new RecordFrame(cs, value, user, target));
	}
	
	public void addFrame(CombatStatus cs, int value, Combatant user, Item item) {
		rf.add(new RecordFrame(cs, value, user, item));
	}
	
	public void addFrame(CombatStatus cs, int value, Combatant user, Skill skill) {
		rf.add(new RecordFrame(cs, value, user, skill));
	}
	
	public void addFrame(CombatStatus cs, int value, Combatant user, boolean inRear) {
		rf.add(new RecordFrame(cs, value, user, inRear));
	}
	
	public void addFrame(CombatStatus cs, int value, Combatant user) {
		rf.add(new RecordFrame(cs, value, user));
	}
	
	public void playRecord() {
		for(RecordFrame frame : rf) {
			System.out.println(readFrame(frame));
		}
	}
	
	private String readFrame(RecordFrame frame) {
		switch (frame.cs) {
		case Attacking:
			return frame.user.name + " melee attacked " + frame.target.name;
		case RAttacking:
			return frame.user.name + " ranged attacked " + frame.target.name;
		case Blocking:
			return frame.user.name + " blocked";
		case Shifting:
			if(!frame.user.inRange()) {
				return frame.user.name + " moved to the rear";
			} else {
				return frame.user.name + " moved to the front";
			}
		case Specializing:
			return frame.user.name + " cast " + frame.skill.getName();
		case Using:
			return frame.user.name + " used a " + frame.item.name;
		case Escaping:
			return frame.user.name + " fled";
		default: 
			return frame.user.name + " stood idle";
		}
	}
}
