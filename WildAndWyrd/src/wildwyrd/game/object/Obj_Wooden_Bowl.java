package wildwyrd.game.object;

import wildwyrd.game.Entity;
import wildwyrd.game.GamePanel;

public class Obj_Wooden_Bowl extends Entity {
	GamePanel gp;
	Entity loot;
	boolean opened = false;

	public Obj_Wooden_Bowl(GamePanel gp, Entity loot) {
		super(gp);
		this.gp = gp;
		this.loot = loot;
		name = "Wooden Bowl";
		type = 3;
		collision = true;

		image = setup("/res/objects/Table_Tile_Bowl", gp.tileSize, gp.tileSize);
		

		setDialogue();
	}

	public void setDialogue() {
		dialogues[0][0] = new Dialoge("A wooden bowl.", 1);
		dialogues[0][1] = new Dialoge("It has hazelnuts in it.", 1);
		dialogues[0][2] = new Dialoge("Pick up " + loot.name, 2);
		dialogues[1][0] = new Dialoge("A wooden bowl.", 1);
		dialogues[1][1] = new Dialoge("It's empty.", 1);
	}

	public void choiceResponce() {
		if (gp.ui.slotyn == 0) {
			gp.player.inventory.add(loot);
			opened = true;
		}

	}

	public void interact() {
		if (!opened) {
			startDialogue(this, 0);
		} else {
			startDialogue(this, 1);
		}

		gp.keyH.enterPressed = false;
	}
}