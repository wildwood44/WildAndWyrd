package wildwyrd.game.object;

import java.io.IOException;
import javax.imageio.ImageIO;
import wildwyrd.game.Entity;
import wildwyrd.game.GamePanel;
import wildwyrd.game.GameState;

public class Obj_Kitchen_Window extends Entity {
	GamePanel gp;

	public Obj_Kitchen_Window(GamePanel gp) {
		super(gp);
		this.gp = gp;
		name = "Window";
		type = 3;
		collision = true;

		image = setup("/res/objects/img_window_light", gp.tileSize, gp.tileSize);

		setDialogue();
		solidArea.x = 15;
		solidArea.y = 40;
		solidArea.height = 50;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
	}

	public void setDialogue() {
		dialogues[0][0] = new Dialoge(
				"The clearing at the front of of the cottage :could be seen through the window.", 1);
		dialogues[1][0] = new Dialoge("Florence and Thay were talking outside the :window.", 1);
		dialogues[1][1] = new Dialoge("Alder","I'm coming out", 1);
	}

	public void interact() {
		GamePanel gp = this.gp;
		gp.gameState = GameState.examineState;
		if (gp.s.tutorialSwitch[2]) {
			startDialogue(this, 1);
			System.out.println(dialogueSet + " " + gp.s.tutorialSwitch[2]);
			gp.s.tutorialSwitch[2] = false;
		} else {
			startDialogue(this, 0);
		}

		gp.keyH.enterPressed = false;
	}
}