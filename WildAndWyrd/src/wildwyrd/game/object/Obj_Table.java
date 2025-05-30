package wildwyrd.game.object;

import wildwyrd.game.Entity;
import wildwyrd.game.EntityType;
import wildwyrd.game.GamePanel;

public class Obj_Table extends Entity {
	GamePanel gp;
	public static final int objId = 14;
	public static final String objName = "Table";

	public Obj_Table(GamePanel gp) {
		super(gp);
		this.gp = gp;
		id = objId;
		name = objName;
		type = EntityType.Object;
		collision = true;
		solidArea.x = 32;
		solidArea.y = 40;
		solidArea.height = 24;
		solidArea.width = 128;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		image = setup("/res/objects/img_table2", gp.tileSize*3, gp.tileSize);

		setDialogue();
		getImage(image);
	}

	public void setDialogue() {
		dialogues[0][0] = new Dialoge(
				"A single round wooden table sat a safe distance from the fire, but close enough to feel its warmth.",
				1);
	}

	public void interact() {
		startDialogue(this, 0);
		gp.keyH.enterPressed = false;
	}
}