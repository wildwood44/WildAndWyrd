package wildwyrd.game.object;

import wildwyrd.game.Entity;
import wildwyrd.game.GamePanel;

public class Obj_Basin extends Entity {
	GamePanel gp;

	public Obj_Basin(GamePanel gp) {
		super(gp);
		this.gp = gp;
		name = "Basin";
		type = 3;
		collision = true;

		image = setup("/res/objects/img_basin", gp.tileSize, gp.tileSize);

		setDialogue();
		solidArea.x = 15;
		solidArea.y = 40;
		solidArea.height = 50;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
	}

	public void setDialogue() {
		dialogues[0][0] = new Dialoge(
				"The basin Alder was washing in was full of :water from Alder cleaning dishes. Clean :plates were lined up to the side.",
				1);
	}

	public void interact() {
		startDialogue(this, 0);
		gp.keyH.enterPressed = false;
	}
}