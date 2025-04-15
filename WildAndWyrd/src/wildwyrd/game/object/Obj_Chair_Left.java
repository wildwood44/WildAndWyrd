package wildwyrd.game.object;

import java.awt.image.BufferedImage;

import wildwyrd.game.Entity;
import wildwyrd.game.EntityType;
import wildwyrd.game.GamePanel;

public class Obj_Chair_Left extends Entity {
	GamePanel gp;
	public static final int objId = 12;
	public static final String objName = "Chair";

	public Obj_Chair_Left(GamePanel gp) {
		super(gp);
		this.gp = gp;
		id = objId;
		name = objName;
		type = EntityType.Object;
		collision = true;
		worldX = gp.tileSize/3;
		worldY = gp.tileSize/2;
		x = gp.tileSize;
		y = gp.tileSize;
		solidArea.x = gp.tileSize/2;
		solidArea.y = gp.tileSize/2;
		solidArea.width = 24;
		solidArea.height = 12;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		direction = getDirection();
		image = setup("/res/objects/img_chair_left",gp.tileSize,gp.tileSize);
		setDialogue();
		getImage(image);
	}
	
	public void getImage(BufferedImage image) {
		down1 = image;
		//left1 = setup("/res/objects/img_chair_left",gp.tileSize/2,gp.tileSize/2);
		//right1 = setup("/res/objects/img_chair_right",gp.tileSize/2,gp.tileSize/2);
		//up1 = setup("/res/objects/img_chair_back",gp.tileSize/2,gp.tileSize/2);
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
	
	public String getDirection() {
		return direction;
	}
}