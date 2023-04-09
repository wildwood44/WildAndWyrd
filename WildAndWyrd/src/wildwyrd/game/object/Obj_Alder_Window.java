package wildwyrd.game.object;

import java.io.IOException;

import javax.imageio.ImageIO;

import wildwyrd.game.Entity;
import wildwyrd.game.GamePanel;

public class Obj_Alder_Window extends Entity {
	GamePanel gp;
	public Obj_Alder_Window(GamePanel gp) {
		super(gp);
		this.gp = gp;
		this.name = "Window";
		this.type = 3;
		this.collision = false;
	
		image = setup("/res/objects/img_window_2", gp.tileSize, gp.tileSize);

		this.setDialogue();
		this.solidArea.y = 56;
		this.solidAreaDefaultY = this.solidArea.y;
	}

	public void setDialogue() {
		this.dialogues[0][0] = new Dialoge(
				"Through the small, curtainless window, Alder could see sunlight breaching the branches of the Fuller Woods near the edge of the clearing.", 1);
	}

	public void interact() {
		startDialogue(this, 0);
		gp.keyH.enterPressed = false;
	}
}
