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
	}

	public String getMainObjective() {
		if(gp.s.c1Switch[0] == true) {
			return "Exit cottage";
		} else if (gp.s.c1Switch[1] == true) {
			return "Speak to Florence";
		} else if (gp.s.c1Switch[2] == true) {
			return "Speak to Thay";
		} else if (gp.s.c1Switch[3] == true) {
			return "Hunt insects";
		}
		return "";
	}
}
