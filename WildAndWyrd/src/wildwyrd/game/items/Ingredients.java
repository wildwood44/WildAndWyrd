package wildwyrd.game.items;

import wildwyrd.game.EntityType;
import wildwyrd.game.GamePanel;

public class Ingredients extends Item {
	public Ingredients(GamePanel gp) {
		super(gp);
		this.gp = gp;
		description = "";
		stackable = true;
		type = EntityType.ingredient;
	}
}
