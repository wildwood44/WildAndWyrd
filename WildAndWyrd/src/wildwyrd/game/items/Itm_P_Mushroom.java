package wildwyrd.game.items;

import wildwyrd.game.GamePanel;

public class Itm_P_Mushroom extends Food {
	GamePanel gp;
	public static final int itemId = 102;
	public static final String itemName = "Parasol Mushroom";

	public Itm_P_Mushroom(GamePanel gp) {
		super(gp);
		this.gp = gp;
		id = itemId;
		name = itemName;
		staminaRcvd = 5;
		description = "";

		image = setup("/res/items/Parasol_Mushroom", gp.tileSize, gp.tileSize);
	}
}