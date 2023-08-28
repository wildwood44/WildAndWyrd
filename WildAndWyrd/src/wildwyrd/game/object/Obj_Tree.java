package wildwyrd.game.object;

import wildwyrd.game.Entity;
import wildwyrd.game.EntityType;
import wildwyrd.game.GamePanel;

public class Obj_Tree extends Entity {
	GamePanel gp;
	public static final int objId = 24;
	public static final String objName = "Tree";

	public Obj_Tree(GamePanel gp, int num) {
		super(gp);
		this.gp = gp;
		name = objName;
		type = EntityType.Object;
		collision = true;

		image = setup("/res/objects/img_tree" + num, gp.tileSize, gp.tileSize);

		setDialogue();
	}

	public void setDialogue() {
		dialogues[0][0] = new Dialoge("Various trees made up the woodland, the majority were birch, rowen and holly.", 1);
	}

	public void interact() {
		startDialogue(this, 0);
		gp.keyH.enterPressed = false;
	}
}