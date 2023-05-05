package wildwyrd.game.items;

import wildwyrd.game.Entity;
import wildwyrd.game.GamePanel;

public class Itm_Hunting_Knife extends Entity {
	GamePanel gp;

	public Itm_Hunting_Knife(GamePanel gp) {
		super(gp);
		this.gp = gp;
		name = "Hunting Knife";
		description = "A knife used to hunt insects.";

		image = setup("/res/items/img_hunting_knife", gp.tileSize, gp.tileSize);

	}

	public void use() {
	}
}