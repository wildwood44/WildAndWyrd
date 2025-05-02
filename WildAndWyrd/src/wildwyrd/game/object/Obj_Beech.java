package wildwyrd.game.object;

import wildwyrd.game.Entity;
import wildwyrd.game.EntityType;
import wildwyrd.game.GamePanel;

public class Obj_Beech extends Entity {
	GamePanel gp;
	public static final int objId = 28;
	public static final String objName = "Tree";

	public Obj_Beech(GamePanel gp) {
		super(gp);
		this.gp = gp;
		id = objId;
		name = objName;
		type = EntityType.Object;
		collision = true;
		image = setup("/res/objects/img_beech", gp.tileSize*2, gp.tileSize*2);

		int size = gp.tileSize * 2;
		solidArea.x = 30;
		solidArea.width = size - 60;
		solidArea.height = size;
		solidAreaDefaultX = solidArea.x;
		//solidAreaDefaultY = solidArea.y;
		setDialogue();
		getImage(image);
	}

	public void setDialogue() {
		dialogues[0][0] = new Dialoge("Various trees made up the woodland, the majority were birch, beech and holly.", 1);
	}

	public void interact() {
		//gp.glossary.unlock("plants", "beech");
		startDialogue(this, 0);
		gp.keyH.enterPressed = false;
	}
}