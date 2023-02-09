package wildwyrd.game.object;

import java.io.IOException;
import javax.imageio.ImageIO;
import wildwyrd.game.Entity;
import wildwyrd.game.GamePanel;

public class Obj_Cupboard extends Entity {
	GamePanel gp;

	public Obj_Cupboard(GamePanel gp) {
		super(gp);
		this.gp = gp;
		this.name = "Cupboard";
		this.type = 3;
		this.collision = true;

		try {
			this.image = ImageIO.read(this.getClass().getResourceAsStream("/res/objects/img_cupboard.png"));
		} catch (IOException var3) {
			var3.printStackTrace();
		}

		this.setDialogue();
		this.solidArea.width = 10;
	}

	public void setDialogue() {
		this.dialogues[0][0] = new Dialoge("The cupboard was full of plates, bowls and :other kitchen and dining ware.",
				1);
	}

	public void interact() {
		this.startDialogue(this, 0);
		this.gp.keyH.enterPressed = false;
	}
}