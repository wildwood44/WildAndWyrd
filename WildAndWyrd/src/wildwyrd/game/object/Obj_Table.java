package wildwyrd.game.object;

import java.io.IOException;
import javax.imageio.ImageIO;
import wildwyrd.game.Entity;
import wildwyrd.game.GamePanel;

public class Obj_Table extends Entity {
	GamePanel gp;

	public Obj_Table(GamePanel gp) {
		super(gp);
		this.gp = gp;
		this.name = "Tables";
		this.type = 3;
		this.collision = true;

		try {
			this.image = ImageIO.read(this.getClass().getResourceAsStream("/res/objects/img_table2.png"));
		} catch (IOException var3) {
			var3.printStackTrace();
		}

		this.setDialogue();
		this.solidArea.width = 60;
	}

	public void setDialogue() {
		this.dialogues[0][0] = new Dialoge(
				"A single round wooden table sat a safe :distance from the fire, but close enough to feel its warmth.",
				1);
	}

	public void interact() {
		this.startDialogue(this, 0);
		this.gp.keyH.enterPressed = false;
	}
}