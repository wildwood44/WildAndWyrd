package wildwyrd.game.items;

import wildwyrd.game.GamePanel;

public class Itm_Hazelnut extends Food {
	GamePanel gp;
	public static final int itemId = 101;
	public static final String itemName = "Hazelnut";

	public Itm_Hazelnut(GamePanel gp) {
		super(gp);
		this.gp = gp;
		id = itemId;
		name = itemName;
		staminaRcvd = 5;
		description = "Seed of a hazel tree.";

		image = setup("/res/items/img_hazelnut", gp.tileSize, gp.tileSize);

	}
}