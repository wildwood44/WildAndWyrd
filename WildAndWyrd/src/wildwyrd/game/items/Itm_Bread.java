package wildwyrd.game.items;

import wildwyrd.game.GamePanel;

public class Itm_Bread extends Food {
	GamePanel gp;
	public static final int itemId = 104;
	public static final String itemName = "Bread";

	public Itm_Bread(GamePanel gp) {
		super(gp);
		this.gp = gp;
		id = itemId;
		name = itemName;
		staminaRcvd = 20;
		description = "";

		image = setup("/res/items/img_bread", gp.tileSize, gp.tileSize);
	}
}