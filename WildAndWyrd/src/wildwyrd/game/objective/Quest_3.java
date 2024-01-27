package wildwyrd.game.objective;

import wildwyrd.game.GamePanel;
import wildwyrd.game.items.Item;
import wildwyrd.game.items.Itm_Bread;

public class Quest_3 extends Quest {

	private final int qId = 3;
	private final String qName = "Supplies";
	
	public Quest_3(GamePanel gp) {
		super(gp);
		id = qId;
		name = qName;
		require = new boolean[1];
		amount = 1;
		qnt = 2;
		this.gp = gp;
	}
	
	public void progress(int i) {
		require[i] = true;
		completeQuest();
	}
	
	public boolean checkRequirements() {
		Item item = new Itm_Bread(gp);
		if(findItemInInventory(item) >= amount) {
			return true;
		}
		return false;
		
	}
	
	public String printQuest() {
		Item item = new Itm_Bread(gp);
		return "Collect supplies (" + findItemInInventory(item) + "/"+amount+")";
	}
}
