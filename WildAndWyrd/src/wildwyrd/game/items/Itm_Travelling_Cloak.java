package wildwyrd.game.items;

import wildwyrd.game.GamePanel;

public class Itm_Travelling_Cloak extends Armour {
	GamePanel gp;
	public static final int itemId = 401;
	public static final String itemName = "Travelling Cloak";

	public Itm_Travelling_Cloak(GamePanel gp) {
		super(gp);
		this.gp = gp;
		id = itemId;
		name = itemName;
		armType = ArmourType.Armour;
		defenceValue = 10;
		description = "";
		price = 5;
		image = setup("/res/items/img_Cloak", gp.tileSize, gp.tileSize);
		//combat_image = setup("/res/equipment/cloak_equiped", gp.tileSize, gp.tileSize);

	}
}