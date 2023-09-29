package wildwyrd.game.objective;

import wildwyrd.game.Entity;
import wildwyrd.game.GamePanel;
import wildwyrd.game.items.Itm_Bug_Meat;

public class Quest extends Entity {
	GamePanel gp;
	public int id;
	public String name;
	protected String client;
	private String desc;
	private boolean accepted;
	private boolean completed;
	private boolean submitted;
	public boolean[] rqmt;
	public Entity reward;
	public int qnt;

	public Quest(GamePanel gp) {
		super(gp);
		accepted = false;
		completed = false;
		submitted = false;
		this.gp = gp;
	}

	public void acceptQuest() {
		accepted = true;
	}
	
	public int findItemInInventory(Entity item) {
		for(int i = 0; i < gp.player.inventory.size(); i++) {
			if(gp.player.inventory.get(i).id == item.id) {
				return gp.player.inventory.get(i).amount;		}
		}
		return 0;
	}
	
	public String printQuest() {
		if (submitted) {
			return desc + ": Complete";
		} else if (completed) {
			return desc + ": Turn in";
		} else if (accepted) {
			return desc + ": Accepted";
		}
		return "";
	}
	
	public void reward() {
		if(reward != null) {
			for(int i = 0; i < amount; i++) {
				gp.player.pickUpObject(reward);
			}
		} else {
			gp.player.pickUpShillings(amount);
		}
	}
}
