package wildwyrd.game.object;

import wildwyrd.game.Entity;
import wildwyrd.game.EntityType;
import wildwyrd.game.GamePanel;

public class Obj_Tree extends Entity {
	GamePanel gp;
	public static final int objId = 24;
	public static final String objName = "Tree";

	public Obj_Tree(GamePanel gp) {
		super(gp);
		this.gp = gp;
		id = objId;
		name = objName;
		type = EntityType.Object;
		collision = true;
		//int size = gp.tileSize * 3;
		//solidArea.x = size - 40;
		//solidArea.y = size - 40*2;
		//solidAreaDefaultX = solidArea.x;
		//solidAreaDefaultY = solidArea.y;
		image = setup("/res/objects/img_tree", gp.tileSize*3, gp.tileSize*2);

		setDialogue();
		getImage(image);
	}

	public void setDialogue() {
		dialogues[0][0] = new Dialoge("Various trees made up the woodland, the majority were birch, rowen and holly.", 1);
	}

	public void interact() {
		startDialogue(this, 0);
		gp.keyH.enterPressed = false;
	}
}