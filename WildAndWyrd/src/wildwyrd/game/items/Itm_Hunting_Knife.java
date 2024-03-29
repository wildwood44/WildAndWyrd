package wildwyrd.game.items;

import wildwyrd.game.GamePanel;

public class Itm_Hunting_Knife extends Primary {
	GamePanel gp;
	public static final int itemId = 301;
	public static final String itemName = "Hunting Knife";

	public Itm_Hunting_Knife(GamePanel gp) {
		super(gp);
		this.gp = gp;
		id = itemId;
		name = itemName;
		wpType = WeaponType.Dagger;
		attackValue = 5;
		description = "A knife used to hunt insects.";

		image = setup("/res/items/img_hunting_knife", gp.tileSize, gp.tileSize);
		combat_image = setup("/res/equipment/hunting_knife_equiped", gp.tileSize, gp.tileSize);

	}
}