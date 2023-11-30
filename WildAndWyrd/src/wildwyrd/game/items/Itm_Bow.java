package wildwyrd.game.items;

import wildwyrd.game.GamePanel;

public class Itm_Bow extends Secondary {
	GamePanel gp;
	public static final int itemId = 302;
	public static final String itemName = "Bow";

	public Itm_Bow(GamePanel gp) {
		super(gp);
		this.gp = gp;
		id = itemId;
		name = itemName;
		wpType = WeaponType.Bow;
		attackValue = 5;
		description = "A bow made for practise.";

		image = setup("/res/items/img_bow", gp.tileSize, gp.tileSize);
		combat_image = setup("/res/equipment/bow_equiped", gp.tileSize, gp.tileSize);
	}
}