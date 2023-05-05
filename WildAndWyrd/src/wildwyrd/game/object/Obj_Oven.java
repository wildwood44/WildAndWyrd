package wildwyrd.game.object;

import wildwyrd.game.Entity;
import wildwyrd.game.EntityType;
import wildwyrd.game.GamePanel;

public class Obj_Oven extends Entity {
	GamePanel gp;

	public Obj_Oven(GamePanel gp) {
		super(gp);
		this.gp = gp;
		name = "Oven";
		type = EntityType.Object;
		collision = true;

		image = setup("/res/objects/img_Oven", gp.tileSize, gp.tileSize);

		solidArea.width = 42;
		solidArea.x = 10;
		solidAreaDefaultX = solidArea.x;
		setDialogue();
	}

	public void setDialogue() {
		dialogues[0][0] = new Dialoge("The oven was unlit.", 1);
	}

	public void interact() {
		startDialogue(this, 0);
		gp.keyH.enterPressed = false;
	}
}