package wildwyrd.game.object;

import java.io.IOException;
import javax.imageio.ImageIO;
import wildwyrd.game.Entity;
import wildwyrd.game.GamePanel;

public class Obj_Kitchen_Window extends Entity {
	GamePanel gp;

	public Obj_Kitchen_Window(GamePanel gp) {
		super(gp);
		this.gp = gp;
		this.name = "Window";
		this.type = 3;
		this.collision = true;

		try {
			this.image = ImageIO.read(this.getClass().getResourceAsStream("/res/objects/img_window_light.png"));
		} catch (IOException var3) {
			var3.printStackTrace();
		}

		this.setDialogue();
		this.solidArea.x = 15;
		this.solidArea.y = 40;
		this.solidArea.height = 50;
		this.solidAreaDefaultX = this.solidArea.x;
		this.solidAreaDefaultY = this.solidArea.y;
	}

	public void setDialogue() {
		this.dialogues[0][0] = new Dialoge(
				"The clearing at the front of of the cottage :could be seen through the window.", 1);
		this.dialogues[1][0] = new Dialoge("Florence and Thay were talking outside the :window.", 1);
		this.dialogues[1][1] = new Dialoge("Alder:I'm coming out", 1);
	}

	public void interact() {
		GamePanel var10000 = this.gp;
		this.gp.getClass();
		var10000.gameState = 7;
		if (this.gp.s.tutorialSwitch[2]) {
			this.startDialogue(this, 1);
			System.out.println(this.dialogueSet + " " + this.gp.s.tutorialSwitch[2]);
			this.gp.s.tutorialSwitch[2] = false;
		} else {
			this.startDialogue(this, 0);
		}

		this.gp.keyH.enterPressed = false;
	}
}