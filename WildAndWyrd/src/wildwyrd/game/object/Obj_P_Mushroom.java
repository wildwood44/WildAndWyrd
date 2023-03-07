package wildwyrd.game.object;

import java.io.IOException;
import javax.imageio.ImageIO;
import wildwyrd.game.Entity;
import wildwyrd.game.GamePanel;

public class Obj_P_Mushroom extends Entity {
	GamePanel gp;
	Entity loot;
	boolean opened = false;

	public Obj_P_Mushroom(GamePanel gp, Entity loot) {
		super(gp);
		this.gp = gp;
		this.loot = loot;
		name = "Parasol Mushroom";
		type = 3;
		collision = true;

		try {
			image = ImageIO.read(getClass().getResourceAsStream("/res/items/Parasol_Mushroom.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		solidArea.x = 20;
		solidAreaDefaultX = solidArea.x;
		setDialogue();
	}

	public void setDialogue() {
		dialogues[0][0] = new Dialoge(
				"There were a variety of late-summer mushrooms growing around the area.",
				1);
		dialogues[0][1] = new Dialoge("Pick " + loot.name, 2);
		dialogues[1][0] = new Dialoge(
				"There were a variety of late-summer mushrooms growing around the area.",
				1);
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