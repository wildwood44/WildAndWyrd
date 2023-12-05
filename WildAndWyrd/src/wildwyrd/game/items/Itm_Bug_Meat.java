package wildwyrd.game.items;

import wildwyrd.game.GamePanel;

public class Itm_Bug_Meat extends Food {
	GamePanel gp;
	public static final int itemId = 103;
	public static final String itemName = "Bug meat";

	public Itm_Bug_Meat(GamePanel gp) {
		super(gp);
		this.gp = gp;
		id = itemId;
		name = itemName;
		staminaRcvd = 10;
		description = "";
		image = setup("/res/items/img_bug_meat", gp.tileSize, gp.tileSize);
	}

	public void use() {
		if(gp.s.chapter == 1) {
			
		} else {
			super.use();
		}
	}
}