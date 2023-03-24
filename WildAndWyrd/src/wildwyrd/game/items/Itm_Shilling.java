package wildwyrd.game.items;

import wildwyrd.game.Entity;
import wildwyrd.game.GamePanel;

public class Itm_Shilling extends Entity {
	GamePanel gp;

	public Itm_Shilling(GamePanel gp) {
		super(gp);
		this.gp = gp;
		name = "Shilling";
	}
	
	
}
