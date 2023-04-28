package wildwyrd.game.items;

import wildwyrd.game.Entity;
import wildwyrd.game.GamePanel;

public class Itm_Bandage extends Entity {
	GamePanel gp;

	public Itm_Bandage(GamePanel gp) {
		super(gp);
		this.gp = gp;
		name = "Bandage";
		description = "A cloth bandage to treat wounds.";

		image = setup("/res/items/img_bandage", gp.tileSize, gp.tileSize);

	}

	public void use() {
	}
}