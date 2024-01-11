package wildwyrd.game.items;

import wildwyrd.game.GamePanel;

public class Itm_Leaf extends Primary {
	GamePanel gp;
	public static final int itemId = 302;
	public static final String itemName = "Leif";

	public Itm_Leaf(GamePanel gp) {
		super(gp);
		this.gp = gp;
		id = itemId;
		name = itemName;
		wpType = WeaponType.Sword;
		attackValue = 500;
		description = "Legendary sword of the Scion.";

		image = setup("/res/items/img_Leif1", gp.tileSize, gp.tileSize);
		combat_image = setup("/res/equipment/hunting_knife_equiped", gp.tileSize, gp.tileSize);

	}
}