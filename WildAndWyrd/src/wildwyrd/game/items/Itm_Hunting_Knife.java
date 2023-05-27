package wildwyrd.game.items;

import wildwyrd.game.Entity;
import wildwyrd.game.EntityType;
import wildwyrd.game.GamePanel;

public class Itm_Hunting_Knife extends Entity {
	GamePanel gp;

	public Itm_Hunting_Knife(GamePanel gp) {
		super(gp);
		this.gp = gp;
		name = "Hunting Knife";
		type = EntityType.Primary;
		attackValue = 5;
		description = "A knife used to hunt insects.";

		image = setup("/res/items/img_hunting_knife", gp.tileSize, gp.tileSize);

	}
	public void selectedItem() {
		int itemIndex = gp.ui.getItemIndexOnSlot();
	}

	public void use() {
	}
}