package wildwyrd.game.objective;

import wildwyrd.game.GamePanel;
import wildwyrd.game.items.Itm_Bug_Meat;
import wildwyrd.game.Entity;

public class Quest_Main extends Quest {

	private int qId = 0;
	private String qName = "Main";
	
	public Quest_Main(GamePanel gp) {
		super(gp);
		id = qId;
		name = qName;
		require = new boolean[1];
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
		}
		return "";
	}
}
