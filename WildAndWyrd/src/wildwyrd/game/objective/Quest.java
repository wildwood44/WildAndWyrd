package wildwyrd.game.objective;

import wildwyrd.game.Entity;
import wildwyrd.game.GamePanel;
import wildwyrd.game.items.Item;

public class Quest extends Entity {
	GamePanel gp;
	public int id;
	public String name;
	protected String client;
	protected String desc;
	protected boolean accepted;
	protected boolean completed;
	protected boolean submitted;
	public boolean[] require;
	public int amount;
	public Item reward;
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
	
	public boolean checkRequirements() {
		for (boolean r : require) {
			if(!r) {
				return false;
			}
		}
		return true;
		
	}
	
	public void completeQuest() {
		completed = checkRequirements();
	}
	
	public boolean submitQuest() {
		if(completed) {
			submitted = true;
			reward();
		}
		return submitted;
	}
	
	public int findItemInInventory(Entity item) {
		for(int i = 0; i < gp.player.inventory.size(); i++) {
			if(gp.player.inventory.get(i).id == item.id) {
				return gp.player.inventory.get(i).amount;		}
		}
		return 0;
	}
	
	public String printQuest() {return "";}
	
	public String printQuestStatus() {
		if (submitted) {
			return "Complete";
		} else if (completed) {
			return "Turn in";
		} else if (accepted) {
			return "Accepted";
		}
		return "";
	}
	
	public void reward() {
		System.out.println(reward);
		if(reward != null) {
			for(int i = 0; i < qnt; i++) {
				gp.player.pickUpObject(reward);
			}
		} else {
			gp.player.pickUpShillings(qnt);
		}
	}
	
	public void progress(int i) {
		require[i] = true;
		completeQuest();
	}

	public boolean isAccepted() {
		return accepted;
	}

	public boolean isCompleted() {
		return completed;
	}

	public boolean isSubmitted() {
		return submitted;
	}
}
