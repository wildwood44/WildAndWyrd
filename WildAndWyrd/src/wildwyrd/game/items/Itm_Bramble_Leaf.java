package wildwyrd.game.items;

import wildwyrd.game.GamePanel;

public class Itm_Bramble_Leaf extends Ingredients {
	GamePanel gp;
	public static final int itemId = 601;
	public static final String itemName = "Bramble leaf";

	public Itm_Bramble_Leaf(GamePanel gp) {
		super(gp);
		this.gp = gp;
		id = itemId;
		name = itemName;
		description = "Leaves from a blackberry bush. Mind the thorns.";
		image = setup("/res/items/img_bramble_leaf", gp.tileSize, gp.tileSize);

	}
}