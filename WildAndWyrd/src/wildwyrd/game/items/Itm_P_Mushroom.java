package wildwyrd.game.items;

import wildwyrd.game.Entity;
import wildwyrd.game.GamePanel;

public class Itm_P_Mushroom extends Entity {
	GamePanel gp;

	public Itm_P_Mushroom(GamePanel gp) {
		super(gp);
		this.gp = gp;
		name = "Parasol Mushroom";
		description = "";

		image = setup("/res/items/Parasol_Mushroom");
	}

	public void use() {
	}
}