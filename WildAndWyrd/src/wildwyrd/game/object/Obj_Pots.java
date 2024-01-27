package wildwyrd.game.object;

import wildwyrd.game.Entity;
import wildwyrd.game.EntityType;
import wildwyrd.game.GamePanel;
import wildwyrd.game.npc.NPC_Florence;

public class Obj_Pots extends Entity {
	GamePanel gp;
	public static final int objId = 4;
	public static final String objName = "Pots";

	public Obj_Pots(GamePanel gp) {
		super(gp);
		this.gp = gp;
		id = objId;
		name = objName;
		type = EntityType.Object;
		collision = true;

		image = setup("/res/objects/img_pots", gp.tileSize, gp.tileSize);

		solidArea.height = 60;
		solidArea.y = 5;
		solidAreaDefaultY = solidArea.y;
		setDialogue();
		getImage(image);
	}

	public void setDialogue() {
		dialogues[0][0] = new Dialoge("Various pots and pans were hung on hooks on the wall.", 1);
	}

	public void interact() {
		startDialogue(this, 0);
		gp.keyH.enterPressed = false;
	}
}