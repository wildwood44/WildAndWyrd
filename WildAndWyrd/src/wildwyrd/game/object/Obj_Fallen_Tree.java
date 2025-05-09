package wildwyrd.game.object;

import wildwyrd.game.Entity;
import wildwyrd.game.EntityType;
import wildwyrd.game.GamePanel;

public class Obj_Fallen_Tree extends Entity {
	GamePanel gp;
	//public static final int objId = 23;
	public static final String objName = "Fallen Tree";

	public Obj_Fallen_Tree(GamePanel gp) {
		super(gp);
		this.gp = gp;
		//id = objId;
		name = objName;
		type = EntityType.Object;
		collision = true;
		image = setup("/res/objects/img_fallen_tree", gp.tileSize*2, gp.tileSize*2);

		int size = gp.tileSize * 2;
		solidArea.width = size;
		solidArea.height = size;
		solidAreaDefaultX = solidArea.x;
		//solidAreaDefaultY = solidArea.y;
		setDialogue();
		getImage(image);
	}

	public void setDialogue() {
		dialogues[0][0] = new Dialoge("A tree had fallen.", 1);
	}

	public void interact() {
		startDialogue(this, 0);
		gp.keyH.enterPressed = false;
	}
}