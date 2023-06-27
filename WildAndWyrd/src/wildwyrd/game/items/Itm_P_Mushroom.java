package wildwyrd.game.items;

import wildwyrd.game.Entity;
import wildwyrd.game.EntityType;
import wildwyrd.game.GamePanel;

public class Itm_P_Mushroom extends Entity {
	GamePanel gp;

	public Itm_P_Mushroom(GamePanel gp) {
		super(gp);
		this.gp = gp;
		name = "Parasol Mushroom";
		type = EntityType.Food;
		staminaRcvd = 5;
		description = "";

		image = setup("/res/items/Parasol_Mushroom", gp.tileSize, gp.tileSize);
	}
	public void selectedItem() {
		int itemIndex = gp.ui.getItemIndexOnSlot();
	}

	public void use() {
	}
}