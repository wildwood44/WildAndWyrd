package wildwyrd.game.object;

import wildwyrd.game.Entity;
import wildwyrd.game.EntityType;
import wildwyrd.game.GamePanel;

public class Obj_Pots extends Entity {
	GamePanel gp;

	public Obj_Pots(GamePanel gp) {
		super(gp);
		this.gp = gp;
		name = "Pots";
		type = EntityType.Object;
		collision = true;

		image = setup("/res/objects/img_pots", gp.tileSize, gp.tileSize);

		solidArea.height = 60;
		solidArea.y = 5;
		solidAreaDefaultY = solidArea.y;
		setDialogue();
	}

	public void setDialogue() {
		dialogues[0][0] = new Dialoge("Various pots and pans were hung on hooks :on the wall.", 1);
	}

	public void interact() {
		startDialogue(this, 0);
		gp.keyH.enterPressed = false;
	}
}