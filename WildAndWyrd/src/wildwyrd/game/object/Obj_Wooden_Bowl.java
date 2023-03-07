package wildwyrd.game.object;

import java.io.IOException;
import javax.imageio.ImageIO;
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

		try {
			this.image = ImageIO.read(getClass().getResourceAsStream("/res/objects/Table_Tile_Bowl.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		setDialogue();
	}

	public void setDialogue() {
		this.dialogues[0][0] = new Dialoge("A wooden bowl.", 1);
		this.dialogues[0][1] = new Dialoge("It has hazelnuts in it.", 1);
		this.dialogues[0][2] = new Dialoge("Pick up " + this.loot.name, 2);
		this.dialogues[1][0] = new Dialoge("A wooden bowl.", 1);
		this.dialogues[1][1] = new Dialoge("It's empty.", 1);
	}

	public void choiceResponce() {
		if (this.gp.ui.slotyn == 0) {
			this.gp.player.inventory.add(this.loot);
			this.opened = true;
		}

	}

	public void interact() {
		if (!this.opened) {
			this.startDialogue(this, 0);
		} else {
			this.startDialogue(this, 1);
		}

		this.gp.keyH.enterPressed = false;
	}
}