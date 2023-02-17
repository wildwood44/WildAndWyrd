package wildwyrd.game.object;

import wildwyrd.game.Entity;
import wildwyrd.game.GamePanel;

public class Obj_Bookshelf extends Entity {
	public Obj_Bookshelf(GamePanel gp) {
		super(gp);
		this.gp = gp;
		this.name = "Bookshelf";
		this.type = 3;
		this.collision = true;
	}

	GamePanel gp;
	
}
