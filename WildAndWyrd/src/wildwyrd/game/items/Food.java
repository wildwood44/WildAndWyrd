package wildwyrd.game.items;

import wildwyrd.game.EntityType;
import wildwyrd.game.GamePanel;

public class Food extends Item {
	public Food(GamePanel gp) {
		super(gp);
		this.gp = gp;
		description = "";
		type = EntityType.Food;
		stackable = true;
	}
	
	public void use() {
		if(gp.playable.get(0).getStamina() < gp.playable.get(0).getMaxStamina()) {
			gp.playSE(17);
			gp.playable.get(0).eat(staminaRcvd);
			gp.player.removeFromInventory(this);
		}
	}
}
