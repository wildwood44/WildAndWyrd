package wildwyrd.game.items;

import wildwyrd.game.EntityType;
import wildwyrd.game.GamePanel;

public class Itm_Bow extends Weapon {
	GamePanel gp;
	public static final int itemId = 302;
	public static final String itemName = "Bow";

	public Itm_Bow(GamePanel gp) {
		super(gp);
		this.gp = gp;
		id = itemId;
		name = itemName;
		type = EntityType.Secondary;
		wpType = WeaponType.Bow;
		attackValue = 5;
		description = "A bow made for practise.";

		image = setup("/res/items/img_bow", gp.tileSize, gp.tileSize);
		combat_image = setup("/res/equipment/bow_equiped", gp.tileSize, gp.tileSize);
	}
	public void selectedItem() {
		int itemIndex = gp.ui.getItemIndexOnSlot();
	}

	public void use() {
	}
}