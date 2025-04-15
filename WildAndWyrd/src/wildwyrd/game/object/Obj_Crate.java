package wildwyrd.game.object;

import wildwyrd.game.Entity;
import wildwyrd.game.EntityType;
import wildwyrd.game.GamePanel;

public class Obj_Crate extends Entity {
	GamePanel gp;
	public static final int objId = 15;
	public static final String objName = "Crate";

	public Obj_Crate(GamePanel gp) {
		super(gp);
		this.gp = gp;
		id = objId;
		name = objName;
		type = EntityType.Object;
		collision = true;

		image = setup("/res/objects/img_create", gp.tileSize, gp.tileSize);

		setDialogue();
		getImage(image);
		solidArea.width = 60;
	}

	public void setDialogue() {
		dialogues[0][0] = new Dialoge(
				"Kyla had put spare magical tools in sturdy crates to keep them from breaking.",
				1);
	}

	public void interact() {
		startDialogue(this, 0);
		gp.keyH.enterPressed = false;
	}
}