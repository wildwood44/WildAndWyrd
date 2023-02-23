package wildwyrd.game.object;

import java.io.IOException;
import javax.imageio.ImageIO;
import wildwyrd.game.Entity;
import wildwyrd.game.GamePanel;

public class Obj_Chair_Left extends Entity {
	GamePanel gp;

	public Obj_Chair_Left(GamePanel gp) {
		super(gp);
		this.gp = gp;
		this.name = "Chair";
		this.type = 3;
		this.collision = true;

		try {
			this.image = ImageIO.read(this.getClass().getResourceAsStream("/res/objects/img_chair.png"));
		} catch (IOException var3) {
			var3.printStackTrace();
		}
		this.solidArea.x = 20;
		this.solidAreaDefaultX = this.solidArea.x;
		this.setDialogue();
	}

	public void setDialogue() {
		this.dialogues[0][0] = new Dialoge(
				"There were two wooden armchairs both with :partridge feather cushions.",
				1);
	}

	public void interact() {
		this.startDialogue(this, 0);
		this.gp.keyH.enterPressed = false;
	}
}