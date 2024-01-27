package wildwyrd.game.items;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import wildwyrd.game.GamePanel;

public class Itm_Leif extends Primary {
	GamePanel gp;
	public static final int itemId = 303;
	public static final String itemName = "Leif";
	public boolean withered = false;

	public Itm_Leif(GamePanel gp) {
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