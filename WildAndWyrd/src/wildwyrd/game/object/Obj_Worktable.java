package wildwyrd.game.object;

import wildwyrd.game.Entity;
import wildwyrd.game.GamePanel;

public class Obj_Worktable extends Entity {
	GamePanel gp;

	public Obj_Worktable(GamePanel gp) {
		super(gp);
		this.gp = gp;
		name = "Worktable";
		type = 3;
		collision = true;

		image = setup("/res/objects/img_worktable", gp.tileSize, gp.tileSize);

		setDialogue();
		solidArea.width = 60;
	}

	public void setDialogue() {
		dialogues[0][0] = new Dialoge(
				"On the table was a mortar and pestle.",
				1);
	}

	public void interact() {
		startDialogue(this, 0);
		gp.keyH.enterPressed = false;
	}
}