package wildwyrd.game.object;

import wildwyrd.game.Entity;
import wildwyrd.game.GamePanel;

public class Obj_Table_Right extends Entity {
	GamePanel gp;

	public Obj_Table_Right(GamePanel gp) {
		super(gp);
		this.gp = gp;
		name = "Tables";
		type = 3;
		collision = true;

		image = setup("/res/objects/Table_Tile_Right", gp.tileSize, gp.tileSize);
		
		solidArea.width = 40;
		setDialogue();
	}

	public void setDialogue() {
		dialogues[0][0] = new Dialoge(
				"A rectangular wooden table stood in the centre of the room while a thinner one with a basin stood by the window.",
				1);
	}

	public void interact() {
		startDialogue(this, 0);
		gp.keyH.enterPressed = false;
	}
}