package wildwyrd.game.items;

import wildwyrd.game.Entity;
import wildwyrd.game.EntityType;
import wildwyrd.game.GamePanel;

public class Itm_P_Mushroom extends Entity {
	GamePanel gp;
	public static final int itemId = 102;
	public static final String itemName = "Parasol Mushroom";

	public Itm_P_Mushroom(GamePanel gp) {
		super(gp);
		this.gp = gp;
		id = itemId;
		name = itemName;
		type = EntityType.Food;
		staminaRcvd = 5;
		description = "";
		stackable = true;

		image = setup("/res/items/Parasol_Mushroom", gp.tileSize, gp.tileSize);
	}
	public void selectedItem() {
		int itemIndex = gp.ui.getItemIndexOnSlot();
	}

	public void use() {
	}
}