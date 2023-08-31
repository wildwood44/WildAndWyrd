package wildwyrd.game.object;

import java.io.IOException;
import javax.imageio.ImageIO;
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

		image = setup("/res/objects/img_chair",gp.tileSize,gp.tileSize);
		solidArea.x = 20;
		solidAreaDefaultX = solidArea.x;
		setDialogue();
		getImage(image);
	}

	public void setDialogue() {
		dialogues[0][0] = new Dialoge(
				"There were two wooden armchairs both with partridge feather cushions.",
				1);
	}

	public void interact() {
		startDialogue(this, 0);
		gp.keyH.enterPressed = false;
	}
}