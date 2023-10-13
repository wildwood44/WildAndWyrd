package wildwyrd.game.objective;

import wildwyrd.game.GamePanel;

public class Quest_1 extends Quest {

	private int qId = 1;
	private String qName = "Chores";
	
	public Quest_1(GamePanel gp) {
		super(gp);
		id = qId;
		name = qName;
		require = new boolean[3];
		//desc = 
		this.gp = gp;
	}
	
	public void progress(int i) {
		require[i] = true;
		completeQuest();
	}
	
	public String printQuest() {
		return "Clean fireplace: " + require[0] + 
				"£Scrub pots: " + require[1] +
				"£Grind bramble leaves in mortar: " + require[2];
	}
}
