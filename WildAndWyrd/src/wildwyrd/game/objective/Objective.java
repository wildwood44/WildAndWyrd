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
}
