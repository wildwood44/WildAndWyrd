package wildwyrd.game.object;

import wildwyrd.game.Entity;
import wildwyrd.game.GamePanel;

public class Obj_Crate extends Entity {
	GamePanel gp;

	public Obj_Crate(GamePanel gp) {
		super(gp);
		this.gp = gp;
		name = "Crate";
		type = 3;
		collision = true;

		image = setup("/res/objects/img_create");

		setDialogue();
		solidArea.width = 60;
	}

	public void setDialogue() {
		dialogues[0][0] = new Dialoge(
				"Kyla had put spare magical tools in sturdy :crates to keep them from breaking.",
				1);
	}

	public void interact() {
		startDialogue(this, 0);
		gp.keyH.enterPressed = false;
	}
}