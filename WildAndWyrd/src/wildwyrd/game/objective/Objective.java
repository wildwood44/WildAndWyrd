package wildwyrd.game.objective;

import wildwyrd.game.Entity;
import wildwyrd.game.GamePanel;

public class Objective extends Entity {

	public Quest[] quests = new Quest[100];
	
	public Objective(GamePanel gp) {
		super(gp);
		this.gp = gp;
		setQuests();
	}
	
	private void setQuests() {
		quests[0] = new Quest_Main(gp);
		quests[1] = new Quest_1(gp);
		quests[2] = new Quest_2(gp);
		quests[3] = new Quest_3(gp);
	}

	public String getMainObjective() {
		if (gp.s.chapter == 1) {
			if(gp.s.c1Switch[0]) {
				return "Exit cottage";
			} else if (gp.s.c1Switch[1]) {
				return "Speak to Florence";
			} else if (gp.s.c1Switch[2]) {
				return "Speak to Thay";
			} else if (gp.s.c1Switch[3]) {
				return "Hunt insects";
			} else if (gp.s.c1Switch[4]) {
				return "Go to bed";
			}
		} else if (gp.s.chapter == 2) {
			if(gp.s.c2Switch[0]) {
				return "Enter cottage";
			} else if(gp.s.c2Switch[1]) {
				return "Talk to Trissie";
			} else if(gp.s.c2Switch[2]) {
				return "Fight dummy";
			} else if(gp.s.c2Switch[3]) {
				return "Talk to Trissie";
			}
		} else if (gp.s.chapter == 3) {
			if(gp.s.c3Switch[0]) {
				return "Talk to Florence";
			} else if(gp.s.c3Switch[1]) {
				return "Examine bookshelf";
			} else if(gp.s.c3Switch[2]) {
				return "Talk to Kyla";
			} else if(gp.s.c3Switch[3] || gp.s.c3Switch[4]) {
				return "Look out window";
			} else if(gp.s.c3Switch[5]) {
				return "Talk to Jeb";
			}
		}
		return "";
	}
	
	public void clearObjective() {
		
	}
}
