package wildwyrd.game.items;

import wildwyrd.game.Entity;
import wildwyrd.game.EntityType;
import wildwyrd.game.GamePanel;

public class Itm_Hazelnut extends Entity {
	GamePanel gp;
	public static final int itemId = 101;
	public static final String itemName = "Hazelnut";

	public Itm_Hazelnut(GamePanel gp) {
		super(gp);
		this.gp = gp;
		id = itemId;
		name = itemName;
		type = EntityType.Food;
		staminaRcvd = 5;
		description = "Seed of a hazel tree.";
		stackable = true;

		image = setup("/res/items/img_hazelnut", gp.tileSize, gp.tileSize);

	}
	public void selectedItem() {
		int itemIndex = gp.ui.getItemIndexOnSlot();
	}

	public void use() {
	}
}