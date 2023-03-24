package wildwyrd.game.items;

import wildwyrd.game.Entity;
import wildwyrd.game.GamePanel;

public class Itm_Hazelnut extends Entity {
	GamePanel gp;

	public Itm_Hazelnut(GamePanel gp) {
		super(gp);
		this.gp = gp;
		name = "Hazelnut";
		description = "Seed of a hazel tree.";

		image = setup("/res/items/img_hazelnut");

	}

	public void use() {
	}
}