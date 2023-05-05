package wildwyrd.game.object;

import java.io.IOException;
import javax.imageio.ImageIO;
import wildwyrd.game.Entity;
import wildwyrd.game.EntityType;
import wildwyrd.game.GamePanel;

public class Obj_Alder_Bed extends Entity {
	GamePanel gp;
	int loot;
	boolean opened = false;

	public Obj_Alder_Bed(GamePanel gp, int loot) {
		super(gp);
		this.gp = gp;
		this.loot = loot;
		name = "Alder's bed";
		type = EntityType.Object;
		collision = true;

		try {
			image = ImageIO.read(getClass().getResourceAsStream("/res/objects/Img_Alder_Bed.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		setDialogue();
	}

	public void setDialogue() {
		dialogues[0][0] = new Dialoge("It was cheaply made from dry wood and partridge feathers. It was powdered with herbs to stop it smelling. Thay helped Alder make it.", 1);
		dialogues[1][0] = new Dialoge("Among the feathers were Alder's savings.",1);
		dialogues[1][1] = new Dialoge("Take them? ", 2);
	}

	public void choiceResponce() {
		if (gp.ui.choiceSlot == 0) {
			gp.player.pickUpShillings(loot);
			opened = true;
		}

	}

	public void interact() {
		startDialogue(this, 0);
		if (!opened) {
			startDialogue(this, 1);
		}
		gp.keyH.enterPressed = false;
	}
}