package wildwyrd.game.objective;

import wildwyrd.game.GamePanel;
import wildwyrd.game.items.Itm_Bug_Meat;
import wildwyrd.game.items.Itm_Travelling_Cloak;
import wildwyrd.game.Entity;

public class Quest_Main extends Quest {

	private int qId = 0;
	private String qName = "Main";
	
	public Quest_Main(GamePanel gp) {
		super(gp);
		id = qId;
		name = qName;
		require = new boolean[1];
		accepted = true;
		this.gp = gp;
	}
	
	public String printQuest() {
		if(gp.s.chapter == 1) {
			if(gp.s.c1Switch[0] == true) {
				return "Exit cottage";
			} else if (gp.s.c1Switch[1] == true) {
				return "Speak to Florence";
			} else if (gp.s.c1Switch[2] == true) {
				return "Speak to Thay";
			} else if (gp.s.c1Switch[3] == true) {
				Entity item = new Itm_Bug_Meat(gp);
				return "Collect 2 bug meat (" + findItemInInventory(item) + "/2)";
			} else if (gp.s.c1Switch[4] == true) {
				return "Speak to Florence";
			} else {
				return "Go to bed";
			}
		} else if (gp.s.chapter == 2) {
			if(gp.s.c2Switch[0] == true) {
				return "Enter cottage";
			} else if (gp.s.c2Switch[1] == true) {
				return "Speak to Trissie";
			} else if (gp.s.c2Switch[2] == true) {
				return "Fight dummy";
			} else {
				return "Speak to Trissie";
			}
		} else if (gp.s.chapter == 3) {
			if(gp.s.c3Switch[0]) {
				return "Talk to Florence";
			} else if(gp.s.c3Switch[1]) {
				return "Talk to Kyla";
			} else if(gp.s.c3Switch[2]) {
				return "Examine bookshelf";
			} else if(gp.s.c3Switch[3]) {
				return "Talk to Kyla";
			} else if(gp.s.c3Switch[4]) {
				return "Look out window";
			} else if(gp.s.c3Switch[5]) {
				Entity item = new Itm_Travelling_Cloak(gp);
				if(findItemInInventory(item) >= 2) {
					return "Leave cottage";
				}
				return "Buy 2 traveling cloaks (" + findItemInInventory(item) + "/2)";
			}
		}
		return "";
	}
}
