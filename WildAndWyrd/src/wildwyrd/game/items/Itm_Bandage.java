package wildwyrd.game.items;

import wildwyrd.game.GamePanel;

public class Itm_Bandage extends Status {
	GamePanel gp;
	public static final int itemId = 201;
	public static final String itemName = "Bandage";

	public Itm_Bandage(GamePanel gp) {
		super(gp);
		this.gp = gp;
		id = itemId;
		name = itemName;
		healthRcvd = 10;
		price = 5;
		description = "A cloth bandage to treat wounds.";
		image = setup("/res/items/img_bandage", gp.tileSize, gp.tileSize);

	}
}