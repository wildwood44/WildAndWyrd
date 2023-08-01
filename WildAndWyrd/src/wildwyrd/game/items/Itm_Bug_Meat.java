package wildwyrd.game.items;

import wildwyrd.game.Entity;
import wildwyrd.game.EntityType;
import wildwyrd.game.GamePanel;

public class Itm_Bug_Meat extends Entity {
	GamePanel gp;

	public Itm_Bug_Meat(GamePanel gp) {
		super(gp);
		this.gp = gp;
		name = "Bug Meat";
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