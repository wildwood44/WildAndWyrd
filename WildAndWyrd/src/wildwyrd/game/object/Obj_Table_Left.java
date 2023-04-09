package wildwyrd.game.object;

import wildwyrd.game.Entity;
import wildwyrd.game.GamePanel;

public class Obj_Table_Left extends Entity {
	GamePanel gp;

	public Obj_Table_Left(GamePanel gp) {
		super(gp);
		this.gp = gp;
		name = "Tables";
		type = 3;
		collision = true;

		image = setup("/res/objects/Table_Tile_left", gp.tileSize, gp.tileSize);
		
		solidArea.x = 20;
		solidArea.width = 40;
		solidAreaDefaultX = solidArea.x;
		setDialogue();
	}

	public void setDialogue() {
		dialogues[0][0] = new Dialoge(
				"A rectangular wooden table stood in the :centre of the room while a thinner one with :a basin stood by the window.",
				1);
	}

	public void interact() {
		startDialogue(this, 0);
		gp.keyH.enterPressed = false;
	}
}