package wildwyrd.game.items;

import wildwyrd.game.Entity;
import wildwyrd.game.EntityType;
import wildwyrd.game.GamePanel;

public class Itm_Bramble_Leaf extends Entity {
	GamePanel gp;
	public static final int itemId = 601;
	public static final String itemName = "Bramble leaf";

	public Itm_Bramble_Leaf(GamePanel gp) {
		super(gp);
		this.gp = gp;
		id = itemId;
		name = itemName;
		type = EntityType.ingredient;
		description = "Leaves from a blackberry bush. Mind the thorns.";
		stackable = true;

		image = setup("/res/items/img_bramble_leaf", gp.tileSize, gp.tileSize);

	}
	public void selectedItem() {
		int itemIndex = gp.ui.getItemIndexOnSlot();
	}

	public void use() {
	}
}