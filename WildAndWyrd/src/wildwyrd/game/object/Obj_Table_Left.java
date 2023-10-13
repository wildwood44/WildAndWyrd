package wildwyrd.game.object;

import wildwyrd.game.Entity;
import wildwyrd.game.EntityType;
import wildwyrd.game.GamePanel;

public class Obj_Table_Left extends Entity {
	GamePanel gp;
	public static final int objId = 1;
	public static final String objName = "Table";

	public Obj_Table_Left(GamePanel gp) {
		super(gp);
		this.gp = gp;
		name = objName;
		id  = objId;
		type = EntityType.Object;
		collision = true;

		image = setup("/res/objects/Table_Tile_left", gp.tileSize, gp.tileSize);
		
		solidArea.x = 20;
		solidArea.width = 40;
		solidArea.y = 30;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		setDialogue();
		getImage(image);
	}

	public void setDialogue() {
		dialogues[0][0] = new Dialoge(
				"A rectangular wooden table stood in the centre of the room while a thinner one with a basin stood by the window.",
				1);
	}

	public void interact() {
		startDialogue(this, 0);
		gp.keyH.enterPressed = false;
	}
}