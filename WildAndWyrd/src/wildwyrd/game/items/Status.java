package wildwyrd.game.items;

import wildwyrd.game.EntityType;
import wildwyrd.game.GamePanel;

public class Status extends Item {
	public Status(GamePanel gp) {
		super(gp);
		this.gp = gp;
		description = "";
		stackable = true;
		type = EntityType.Health;
	}
	
	public void use() {
		if(gp.playable.get(0).getHealth() < gp.playable.get(0).getMaxHealth()) {
			gp.playSE(17);
			gp.playable.get(0).heal(healthRcvd);
			gp.player.removeFromInventory(this);
		}
	}
}
