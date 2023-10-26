package wildwyrd.game.object;

import java.io.IOException;
import javax.imageio.ImageIO;
import wildwyrd.game.Entity;
import wildwyrd.game.GamePanel;

public class Obj_Larder extends Entity {
	GamePanel gp;

	public Obj_Larder(GamePanel gp) {
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
		this.dialogues[0][0] = new Dialoge(
				"A small cool cupboard full of shelves and :hooks to store food, mostly empty except :for a loaf of bread.",
				1);
		this.dialogues[0][1] = new Dialoge(
				"Kyla’s familiar Tawie was out resupplying. :Alder was not looking forward to her return.", 1);
		this.dialogues[1][0] = new Dialoge("There was only a single loaf of bread in the larder. It would have to do.",
				1);
		this.dialogues[2][0] = new Dialoge("The larder was empty.", 1);
	}

	public void interact() {
		this.startDialogue(this, 0);
		this.gp.keyH.enterPressed = false;
	}
}