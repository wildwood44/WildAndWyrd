package wildwyrd.game.object;

import java.io.IOException;
import javax.imageio.ImageIO;
import wildwyrd.game.Entity;
import wildwyrd.game.GamePanel;

public class Obj_Oven extends Entity {
	GamePanel gp;

	public Obj_Oven(GamePanel gp) {
		super(gp);
		this.gp = gp;
		this.name = "Oven";
		this.type = 3;
		this.collision = true;

		try {
			this.image = ImageIO.read(this.getClass().getResourceAsStream("/res/objects/img_Oven.png"));
		} catch (IOException var3) {
			var3.printStackTrace();
		}

		this.solidArea.width = 42;
		this.solidArea.x = 10;
		this.solidAreaDefaultX = this.solidArea.x;
		this.setDialogue();
	}

	public void setDialogue() {
		this.dialogues[0][0] = new Dialoge("The oven was unlit.", 1);
	}

	public void interact() {
		this.startDialogue(this, 0);
		this.gp.keyH.enterPressed = false;
	}
}