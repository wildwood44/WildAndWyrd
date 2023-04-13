package wildwyrd.game.object;

import java.io.IOException;
import javax.imageio.ImageIO;
import wildwyrd.game.Entity;
import wildwyrd.game.GamePanel;

public class Obj_Cauldron extends Entity {
	GamePanel gp;

	public Obj_Cauldron(GamePanel gp) {
		super(gp);
		this.gp = gp;
		name = "Cauldron";
		type = 3;
		collision = true;

		image = setup("/res/objects/img_hearth_left", gp.tileSize, gp.tileSize);

		solidArea.height = 60;
		solidArea.y = 5;
		solidAreaDefaultY = this.solidArea.y;
		setDialogue();
	}

	public void setDialogue() {
		dialogues[0][0] = new Dialoge(
				"A small metal cauldron was suspended above :the stove by a chain. It was empty.", 1);
	}

	public void interact() {
		startDialogue(this, 0);
		gp.keyH.enterPressed = false;
	}
}