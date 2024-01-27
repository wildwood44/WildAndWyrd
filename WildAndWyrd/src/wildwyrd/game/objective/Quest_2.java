package wildwyrd.game.objective;

import wildwyrd.game.GamePanel;
import wildwyrd.game.items.Itm_Bread;

public class Quest_2 extends Quest {

	private final int qId = 2;
	private final String qName = "Packing up";
	
	public Quest_2(GamePanel gp) {
		super(gp);
		id = qId;
		name = qName;
		require = new boolean[2];
		reward = new Itm_Bread(gp);
		qnt = 1;
		this.gp = gp;
	}
	
	public void progress(int i) {
		require[i] = true;
		completeQuest();
	}
	
	public String printQuest() {
		return "Empty bookshelf" + require[0] + 
				"£Gather Pots: " + require[1];
	}
}
