package wildwyrd.game.object;

import java.awt.image.BufferedImage;

import wildwyrd.game.Entity;
import wildwyrd.game.EntityType;
import wildwyrd.game.GamePanel;

public class Obj_Chair extends Entity {
	GamePanel gp;
	public static final int objId = 12;
	public static final String objName = "Chair";

	public Obj_Chair(GamePanel gp) {
		super(gp);
		this.gp = gp;
		id = objId;
		name = objName;
		type = EntityType.Object;
		collision = true;
		//solidArea.x = 20;
		solidArea.y = 40;
		//solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;

		image = setup("/res/objects/img_chair",gp.tileSize,gp.tileSize);
		setDialogue();
		getImage(image);
	}
	
	public void getImage(BufferedImage image) {
		down1 = image;
		left1 = setup("/res/objects/img_chair_left",gp.tileSize,gp.tileSize);
		right1 = setup("/res/objects/img_chair_right",gp.tileSize,gp.tileSize);
		up1 = setup("/res/objects/img_chair_back",gp.tileSize,gp.tileSize);
	}

	public void setDialogue() {
		dialogues[0][0] = new Dialoge(
				"There were two wooden chairs both with partridge feather cushions.",
				1);
	}

	public void interact() {
		startDialogue(this, 0);
		gp.keyH.enterPressed = false;
	}
}