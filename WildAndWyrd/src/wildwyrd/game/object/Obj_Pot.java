package wildwyrd.game.object;

import wildwyrd.game.Entity;
import wildwyrd.game.GamePanel;

public class Obj_Pot extends Entity {
	GamePanel gp;
	Entity loot;
	boolean opened = false;

	public Obj_Pot(GamePanel gp, Entity loot) {
		super(gp);
		this.gp = gp;
		this.loot = loot;
		name = "Clay Pot";
		type = 3;
		collision = true;

		image = setup("/res/objects/img_clay_pot_four", gp.tileSize, gp.tileSize);

		setDialogue();
	}

	public void setDialogue() {
		dialogues[0][0] = new Dialoge("The pots contained potion ingredients, left :in the shed to save space. One pot was :completely filled with eyeballs.", 1);
		dialogues[0][1] = new Dialoge("There was something strange in one of them.", 2);
		dialogues[1][0] = new Dialoge("The pots contained potion ingredients, left :in the shed to save space. One pot was :completely filled with eyeballs.", 1);
	}

	public void choiceResponce() {
		if (gp.ui.choiceSlot == 0) {
			gp.player.inventory.add(loot);
			opened = true;
		}

	}

	public void interact() {
		if(loot != null) {
			if (!opened) {
				startDialogue(this, 0);
			} else {
				startDialogue(this, 1);
			}
		} else {
			startDialogue(this, 1);
		}
		

		gp.keyH.enterPressed = false;
	}
}