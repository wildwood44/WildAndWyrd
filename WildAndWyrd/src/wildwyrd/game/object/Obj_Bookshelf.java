package wildwyrd.game.object;

import wildwyrd.game.Entity;
import wildwyrd.game.GamePanel;

public class Obj_Bookshelf extends Entity {
	GamePanel gp;
	public Obj_Bookshelf(GamePanel gp) {
		super(gp);
		this.gp = gp;
		name = "Bookshelf";
		type = 3;
		collision = true;

		image = setup("/res/objects/img_bookshelf", gp.tileSize, gp.tileSize);

		solidArea.width = 64;
		solidArea.y = 5;
		solidAreaDefaultY = solidArea.y;
		setDialogue();
	}

	public void setDialogue() {
		
	}

	public void interact() {
		startDialogue(this, 0);
		gp.keyH.enterPressed = false;
	}

	
}
