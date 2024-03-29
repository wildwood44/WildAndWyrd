package wildwyrd.game.object;

import wildwyrd.game.Entity;
import wildwyrd.game.EntityType;
import wildwyrd.game.GamePanel;

public class Obj_Rock extends Entity {
	GamePanel gp;
	public static final int objId = 21;
	public static final String objName = "Rock";

	public Obj_Rock(GamePanel gp, int varient) {
		super(gp);
		this.gp = gp;
		id = objId;
		name = objName;
		type = EntityType.Object;
		collision = true;
		if (varient == 1) {
			image = setup("/res/objects/nettle_rock_tile", gp.tileSize, gp.tileSize);
		} else {
			image = setup("/res/objects/img_rock", gp.tileSize, gp.tileSize);
		}
		getImage(image);
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