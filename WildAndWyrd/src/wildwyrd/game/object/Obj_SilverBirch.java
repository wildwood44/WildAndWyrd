package wildwyrd.game.object;

import wildwyrd.game.Entity;
import wildwyrd.game.GamePanel;

public class Obj_SilverBirch extends Entity {
	GamePanel gp;
	Entity loot;
	boolean opened = false;

	public Obj_SilverBirch(GamePanel gp, int num) {
		super(gp);
		this.gp = gp;
		name = "Tree";
		type = 3;
		collision = true;
		image = setup("/res/objects/img_silver_birch" + num, gp.tileSize, gp.tileSize);

		setDialogue();
	}

	public void setDialogue() {
		dialogues[0][0] = new Dialoge("Various trees made up the woodland, the :majority were birch, rowen and holly.", 1);
	}

	public void interact() {
		startDialogue(this, 0);
		gp.keyH.enterPressed = false;
	}
}