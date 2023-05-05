package wildwyrd.game.object;

import wildwyrd.game.Entity;
import wildwyrd.game.EntityType;
import wildwyrd.game.GamePanel;

public class Obj_Worktable extends Entity {
	GamePanel gp;
	Entity loot;
	boolean opened = false;

	public Obj_Worktable(GamePanel gp, Entity loot) {
		super(gp);
		this.gp = gp;
		this.loot = loot;
		name = "Worktable";
		type = EntityType.Object;
		collision = true;

		image = setup("/res/objects/img_worktable", gp.tileSize, gp.tileSize);

		setDialogue();
		solidArea.width = 60;
	}
	
	public void choiceResponce() {
		if (gp.ui.choiceSlot == 0) {
			gp.player.inventory.add(loot);
			opened = true;
		}

	}

	public void setDialogue() {
		dialogues[0][0] = new Dialoge(
				"On the table was a mortar and pestle.",
				1);
		dialogues[1][0] = new Dialoge(
				"In the draw of the worktable was a hunting knife.",
				1);
		dialogues[1][1] = new Dialoge("Take it?",2);
	}

	public void interact() {
		startDialogue(this, 1);
		gp.keyH.enterPressed = false;
	}
}