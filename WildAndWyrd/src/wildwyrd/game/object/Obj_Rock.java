package wildwyrd.game.object;

import wildwyrd.game.Entity;
import wildwyrd.game.EntityType;
import wildwyrd.game.GamePanel;

public class Obj_Rock extends Entity {
	GamePanel gp;
	Entity loot;
	boolean opened = false;

	public Obj_Rock(GamePanel gp, int varient) {
		super(gp);
		this.gp = gp;
		name = "Rock";
		type = EntityType.Object;
		collision = true;
		if (varient == 1) {
			image = setup("/res/objects/nettle_rock_tile", gp.tileSize, gp.tileSize);
		} else {
			image = setup("/res/objects/img_rock", gp.tileSize, gp.tileSize);
		}
		

		setDialogue();
	}

	public void setDialogue() {
		dialogues[0][0] = new Dialoge("There were a few boulders in the area. There was a large one were the cottage was.", 1);
	}

	public void interact() {
		startDialogue(this, 0);
		gp.keyH.enterPressed = false;
	}
}