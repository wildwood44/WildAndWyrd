package wildwyrd.game.items;

import wildwyrd.game.Entity;
import wildwyrd.game.EntityType;
import wildwyrd.game.GamePanel;

public class Itm_Bug_Meat extends Entity {
	GamePanel gp;
	public static final int itemId = 103;
	public static final String itemName = "Bug meat";

	public Itm_Bug_Meat(GamePanel gp) {
		super(gp);
		this.gp = gp;
		id = itemId;
		name = itemName;
		type = EntityType.Food;
		staminaRcvd = 10;
		description = "";
		stackable = true;

		image = setup("/res/items/img_bug_meat", gp.tileSize, gp.tileSize);

	}
	public void selectedItem() {
		int itemIndex = gp.ui.getItemIndexOnSlot();
	}

	public void use() {
	}
}