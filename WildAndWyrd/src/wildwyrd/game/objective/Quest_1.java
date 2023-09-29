package wildwyrd.game.objective;

import wildwyrd.game.GamePanel;
import wildwyrd.game.items.Itm_Bug_Meat;
import wildwyrd.game.Entity;

public class Quest_1 extends Quest {

	private int qId = 1;
	private String qName = "Chores";
	
	public Quest_1(GamePanel gp) {
		super(gp);
		id = qId;
		name = qName;
		rqmt = new boolean[3];
		this.gp = gp;
	}
	
	public String printQuest() {
		return "Clean fireplace: " + rqmt[0] + 
				"£Scrub cauldron: " + rqmt[1] +
				"£Grind bramble leaves in mortar: " + rqmt[2];
	}
}
