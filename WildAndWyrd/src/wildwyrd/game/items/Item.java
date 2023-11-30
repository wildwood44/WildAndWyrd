package wildwyrd.game.items;

import wildwyrd.game.Entity;
import wildwyrd.game.GamePanel;

public class Item extends Entity {
	public Item(GamePanel gp) {
		super(gp);
		this.gp = gp;
		description = "";
		stackable = true;
	}
	public void selectedItem() {
		int itemIndex = gp.ui.getItemIndexOnSlot();
	}
	
	public void use() {}
}
