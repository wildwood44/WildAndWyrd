package wildwyrd.game.items;

import wildwyrd.game.Entity;
import wildwyrd.game.EntityType;
import wildwyrd.game.GamePanel;

public class Itm_Bandage extends Entity {
	GamePanel gp;

	public Itm_Bandage(GamePanel gp) {
		super(gp);
		this.gp = gp;
		name = "Bandage";
		type = EntityType.Health;
		healthRcvd = 10;
		description = "A cloth bandage to treat wounds.";
		stackable = true;
		image = setup("/res/items/img_bandage", gp.tileSize, gp.tileSize);

	}
	public void selectedItem() {
		int itemIndex = gp.ui.getItemIndexOnSlot();
	}

	public void use() {
	}
}