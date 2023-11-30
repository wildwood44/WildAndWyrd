package wildwyrd.game.items;

import wildwyrd.game.GamePanel;

public class Itm_Primative_Arrow extends Projectile {
	GamePanel gp;
	public static final int itemId = 501;
	public static final String itemName = "Primative Arrow";

	public Itm_Primative_Arrow(GamePanel gp) {
		super(gp);
		this.gp = gp;
		id = itemId;
		name = itemName;
		wpType = WeaponType.Arrow;
		attackValue = 5;
		description = "A stick with small white feathers pressed into the end.";
		image = setup("/res/items/img_primative_arrow", gp.tileSize, gp.tileSize);

	}
}