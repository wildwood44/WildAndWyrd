package wildwyrd.game.object;

import java.io.IOException;
import javax.imageio.ImageIO;
import wildwyrd.game.Entity;
import wildwyrd.game.EntityType;
import wildwyrd.game.GamePanel;

public class Obj_Chair_Left extends Entity {
	GamePanel gp;

	public Obj_Chair_Left(GamePanel gp) {
		super(gp);
		this.gp = gp;
		name = "Chair";
		type = EntityType.Object;
		collision = true;

		try {
			image = ImageIO.read(getClass().getResourceAsStream("/res/objects/img_chair.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		solidArea.x = 20;
		solidAreaDefaultX = solidArea.x;
		setDialogue();
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