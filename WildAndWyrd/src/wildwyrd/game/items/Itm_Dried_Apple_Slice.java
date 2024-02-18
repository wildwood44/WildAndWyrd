package wildwyrd.game.items;

import wildwyrd.game.GamePanel;

public class Itm_Dried_Apple_Slice extends Food {
	GamePanel gp;
	public static final int itemId = 105;
	public static final String itemName = "Slice of Dried Apple";

	public Itm_Dried_Apple_Slice(GamePanel gp) {
		super(gp);
		this.gp = gp;
		id = itemId;
		name = itemName;
		staminaRcvd = 20;
		description = "";
		price = 1;
		image = setup("/res/items/img_Dried_Apple_Slice", gp.tileSize, gp.tileSize);
	}
}